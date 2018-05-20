package com.fit.org.service.convertor;

import java.util.List;

import com.fit.org.api.enums.RequirementTypeEnum;
import com.fit.org.api.model.RequirementDTO;
import com.fit.org.api.model.UserDTO;
import com.fit.org.dao.model.RequirementDO;
import com.fit.org.dao.model.UserDO;
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
            if(requirementDTO.getDifficulty() == 1) {
                requirementDTO.setKgNum(2);
            }
            if(requirementDTO.getDifficulty() == 2) {
                requirementDTO.setKgNum(3);
            }
            if(requirementDTO.getDifficulty() == 3) {
                requirementDTO.setKgNum(4);
            }
            result.add(requirementDTO);
        });
        return result;
    }
}

