package com.ywc.common.page.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ywc.common.page.contanst.OrderEnum;
import com.ywc.common.page.contanst.PageConstant;
import com.ywc.util.Underline2Camel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yanwe
 *         createTime 2017-07-2017/7/26 14:19
 */
public class PageParam {

    private Integer pageStart;

    private Integer pageSize;

    private Map<String,Object> pageSearch;

    private List<PageOrder> pageOrders;

    public PageParam(HttpServletRequest request) {
        pageStart = Integer.valueOf(request.getParameter(PageConstant.PAGE_START));
        pageSize = Integer.valueOf(request.getParameter(PageConstant.PAGE_SIZE));
        pageSearch = JSON.parseObject(request.getParameter(PageConstant.PAGE_SEARCH)
                ,new TypeReference<Map<String,Object>>(){});
        pageOrders = JSON.parseArray(request.getParameter(PageConstant.PAGE_ORDER),PageOrder.class);
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Map<String, Object> getPageSearch() {
        return pageSearch;
    }

    public List<PageOrder> getPageOrders() {
        return pageOrders;
    }

    public String getPageOrdersToString() {
        return pageOrders.stream().map(PageOrder::toString).collect(Collectors.joining(","));
    }

    class PageOrder {
        private String orderKey;
        private OrderEnum order;

        public String getOrderKey() {
            return orderKey;
        }

        public PageOrder setOrderKey(String orderKey) {
            this.orderKey = orderKey;
            return this;
        }

        public OrderEnum getOrder() {
            return order;
        }

        public PageOrder setOrder(String order) {
            this.order = OrderEnum.getOrder(order);
            return this;
        }

        @Override
        public String toString() {
            return Underline2Camel.camel2Underline(orderKey)+" "+order.getOrder();
        }
    }
}
