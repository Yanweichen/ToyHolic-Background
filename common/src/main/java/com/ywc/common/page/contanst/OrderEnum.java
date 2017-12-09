package com.ywc.common.page.contanst;

/**
 * @author yanwe
 *         createTime 2017-07-2017/7/26 14:40
 */
public enum OrderEnum {

    ASC("ASC"),
    DESC("DESC");

    private String order;

    OrderEnum(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public OrderEnum setOrder(String order) {
        this.order = order;
        return this;
    }

    public static OrderEnum getOrder(String order){
        if (ASC.getOrder().equalsIgnoreCase(order)){
            return ASC;
        } else {
            return DESC;
        }
    }
}
