package com.fit.org.service;

import com.fit.org.api.model.RequirementDTO;
import com.fit.org.api.model.RequirementQuery;
import com.fit.org.api.model.Result;
import com.fit.org.api.model.UserDTO;
import com.fit.org.dao.model.UserDO;

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
