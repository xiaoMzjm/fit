package com.fit.org.dao.mapper;

import java.util.List;

import com.fit.org.api.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author:黑绝
 * @date:2018/3/4 17:56
 */
@Mapper
public interface UserMapper {

    public List<User> selectAll();
}
