package com.fit.org.service.manager;

import java.util.List;

import com.fit.org.api.model.dto.RequirementDTO;
import com.fit.org.api.model.query.RequirementQuery;
import com.fit.org.dao.model.RequirementDO;

/**
 * @author:黑绝
 * @date:2018/5/19 下午12:21
 */
public interface RequirementManager {

    Long insert(RequirementDO requirementDO) throws Exception;

    List<RequirementDO> select(RequirementQuery requirementQuery) throws Exception;

    Long update(RequirementDO requirementDO) throws Exception;

    Float calTargetNum(RequirementDTO requirementDTO) throws Exception;
}
