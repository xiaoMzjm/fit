package com.fit.org.api.enums;

/**
 * @author:黑绝
 * @date:2018/5/20 下午9:37
 */
public enum RequirementTypeEnum {

    LOOSE_WEIGHT(1 , "减肥"),
    PLASTIC_SHAPE(2 , "塑形"),
    MUSCULUS_MUSCLE(3 , "增肌"),

    ;

    private Integer type;

    private String desc;

    public static String getDescByType(Integer type) {
        if(type == null) {
            return null;
        }
        RequirementTypeEnum[] values = RequirementTypeEnum.values();
        for(RequirementTypeEnum item : values) {
            if(item.type == type) {
                return item.desc;
            }
        }
        return null;
    }

    RequirementTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
