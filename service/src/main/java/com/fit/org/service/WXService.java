package com.fit.org.service;

import com.fit.org.api.model.Result;
import com.fit.org.api.model.WxLoginResultDTO;
import com.fit.org.api.model.WxUserInfoDTO;

/**
 * @author:黑绝
 * @date:2018/5/19 下午10:19
 */
public interface WXService {

    /**
     * 根据微信前端传来的 encryptedData 和 iv ， 结合后台获取的的 sessionKey，对密文进行揭秘
     * @param encryptedData     密文
     * @param sessionKey        密钥
     * @param iv                加密算法的初始向量
     * @return
     * @throws Exception
     */
    Result<WxUserInfoDTO> getUserInfo(String encryptedData, String sessionKey, String iv) ;
}
