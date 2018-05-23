package com.fit.org.service.service;

import java.util.List;

import com.fit.org.api.model.Result;
import com.fit.org.api.model.dto.UserDTO;
import com.fit.org.api.model.query.UserQuery;

/**
 * @author:黑绝
 * @date:2018/5/19 下午12:20
 */
public interface UserService {

    Result<String> insert(UserDTO userDTO);

    Result<List<UserDTO>> select(UserQuery userQuery);

    Result<UserDTO> getByCode(String code);

    Result<Long> update(UserDTO userDTO);
}
