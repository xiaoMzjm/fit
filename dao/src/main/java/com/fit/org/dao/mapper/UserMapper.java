package com.fit.org.dao.mapper;

import java.util.List;
import java.util.Map;

import com.fit.org.dao.model.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * @author:黑绝
 * @date:2018/3/4 17:56
 */
@Mapper
public interface UserMapper {

    Long insert(UserDO userDO);

    List<UserDO> select(Map<String, Object> params);

    Long update(Map<String, Object> params);
}
