package com.fit.org.service.wapper.impl;

import java.nio.charset.Charset;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSON;

import com.fir.org.common.context.FitContext;
import com.fit.org.api.model.dto.WxLoginResultDTO;
import com.fit.org.api.model.dto.WxUserInfoDTO;
import com.fit.org.service.wapper.WxWapper;
import net.iharder.Base64;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author:黑绝
 * @date:2018/4/29 23:03
 */
@Component
public class WxWapperImpl implements WxWapper {

    private final static Logger logger = LoggerFactory.getLogger(WxWapperImpl.class);

    @Override
    public WxLoginResultDTO getSessionKeyOropenid(String code) throws Exception{
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        Request request = Request
            .Post(url)
            .bodyForm(
                Form.form().add("appid", FitContext.WEIXIN_APP_ID).
                    add("secret", FitContext.WEIXIN_APP_SECRET).
                    add("js_code", code).
                    add("grant_type", FitContext.WEXIN_AUTHORIZATION_CODE).
                    build() ,
                Charset.forName("UTF-8")
            );
        String postResult = null;
        for(int i = 0 ; i < 2 ; i++) {
            try {
                postResult = request
                    .execute()
                    .returnContent()
                    .asString();
                break;
            }catch (Exception e) {
                logger.error("请求微信登陆失败，" + e.getMessage() , e);
                if(i == 1) {
                    throw new RuntimeException("请求微信登陆失败，" + e.getMessage());
                }
            }
        }

        String strResult = new String(postResult);
        System.out.println(strResult);
        WxLoginResultDTO wxLoginResultDTO = JSON.parseObject(strResult , WxLoginResultDTO.class);
        return wxLoginResultDTO;
    }

    @Override
    public WxUserInfoDTO getUserInfo(String encryptedData, String sessionKey, String iv) throws Exception{
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);

        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyByte.length % base != 0) {
            int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
            keyByte = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
        SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
        parameters.init(new IvParameterSpec(ivByte));
        cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
        byte[] resultByte = cipher.doFinal(dataByte);
        if (null != resultByte && resultByte.length > 0) {
            String result = new String(resultByte, "UTF-8");
            System.out.println(result);
            return JSON.parseObject(result , WxUserInfoDTO.class);
        }
        return null;
    }
}
