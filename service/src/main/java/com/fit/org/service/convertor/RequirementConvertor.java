package com.fit.org.service.convertor;

import java.util.List;

import com.fit.org.api.enums.RequirementTypeEnum;
import com.fit.org.api.model.dto.RequirementDTO;
import com.fit.org.dao.model.RequirementDO;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author:黑绝
 * @date:2018/5/20 下午10:11
 */
public class RequirementConvertor {

    public static List<RequirementDTO> converte2DTO(List<RequirementDO> requirementDOList) {
        if(CollectionUtils.isEmpty(requirementDOList)) {
            return null;
        }
        List<RequirementDTO> result = Lists.newArrayList();
        requirementDOList.forEach(requirementDO -> {
            RequirementDTO requirementDTO = new RequirementDTO();
            BeanUtils.copyProperties(requirementDO , requirementDTO);
            requirementDTO.setRequirementTypeName(RequirementTypeEnum.getDescByType(requirementDTO.getRequirementType()));
            result.add(requirementDTO);
        });
        return result;
    }
}

