package com.fit.org.dao.mapper;

import java.util.List;
import java.util.Map;

import com.fit.org.dao.model.RequirementDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author:黑绝
 * @date:2018/5/20 下午5:42
 */
@Mapper
public interface RequirementMapper {

    Long insert(RequirementDO requirementDO);

    List<RequirementDO> select(Map<String, Object> params);

    Long update(Map<String, Object> params);
}
