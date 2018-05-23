package com.fit.org.service.convertor;

import java.util.*;
import com.fit.org.api.model.dto.UserDTO;
import com.fit.org.dao.model.UserDO;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author:黑绝
 * @date:2018/5/20 下午10:03
 */
public class UserConvertor {

    public static List<UserDTO> converte2DTO(List<UserDO> userDOList) {
        if(CollectionUtils.isEmpty(userDOList)) {
            return null;
        }
        List<UserDTO> result = Lists.newArrayList();
        userDOList.forEach(userDO -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userDO , userDTO);
            result.add(userDTO);
        });
        return result;
    }

    public static UserDTO converte2DTO(UserDO userDO) {
        if(userDO == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO , userDTO);
        return userDTO;
    }
}
