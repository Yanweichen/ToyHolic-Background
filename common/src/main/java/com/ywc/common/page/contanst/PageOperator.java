package com.ywc.common.page.contanst;

/**
 * 分页通用操作
 *
 * @author yanwe
 *         createTime 2017-07-2017/7/27 17:23
 */
public enum PageOperator {

    /**
     * 相似
     */
    LIKE("LIKE"),
    /**
     * 等于
     */
    EQ("EQ"),
    /**
     * 不等于
     */
    NE("NE"),
    /**
     * 大于
     */
    GT("GT"),
    /**
     * 小于
     */
    LE("LE");

    private String operator;

    PageOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static PageOperator getOperator(String operator){
        switch (operator.toUpperCase()){
            case "LIKE":
                return LIKE;
            case "EQ":
                return EQ;
            case "NE":
                return NE;
            case "GT":
                return GT;
            case "LE":
                return LE;
            default:
                throw new IllegalArgumentException("未知的操作类型:"+operator);
        }
    }
}
