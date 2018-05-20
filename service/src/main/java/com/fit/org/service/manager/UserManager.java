package com.fit.org.service.manager;

import java.util.List;

import com.fit.org.api.model.UserDTO;
import com.fit.org.api.model.UserQuery;
import com.fit.org.dao.model.UserDO;

/**
 * @author:黑绝
 * @date:2018/5/19 下午12:21
 */
public interface UserManager {

    String insert(UserDO userDO) throws Exception;

    List<UserDO> select(UserQuery userQuery) throws Exception;

    UserDO getByOpenId(String openId) throws Exception;

    UserDO getByCode(String code) throws Exception;

    Long update(UserDO userDO) throws Exception;
}
