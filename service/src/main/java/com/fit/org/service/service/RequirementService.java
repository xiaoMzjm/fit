package com.fit.org.service.service;

import com.fit.org.api.model.dto.RequirementDTO;
import com.fit.org.api.model.query.RequirementQuery;
import com.fit.org.api.model.Result;
import com.fit.org.api.model.dto.UserDTO;

import java.util.*;

/**
 * @author:黑绝
 * @date:2018/5/20 下午9:55
 */
public interface RequirementService {

    Result<Void> changeRequirement(UserDTO userDTO , RequirementDTO requirementDTO);

    Result<Long> insert(RequirementDTO requirementDTO);

    Result<List<RequirementDTO>> select(RequirementQuery requirementQuery);

    Result<RequirementDTO> getByUserCode(String userCode);

    Result<Long> update(RequirementDTO requirementDTO);
}
