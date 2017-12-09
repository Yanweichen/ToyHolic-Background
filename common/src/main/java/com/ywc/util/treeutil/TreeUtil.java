package com.ywc.util.treeutil;

import com.ywc.common.log.LogTemplate;
import com.ywc.util.ListSortUtil;
import com.ywc.util.ReflectUtil;
import com.ywc.util.treeutil.annotation.TreeChild;
import com.ywc.util.treeutil.annotation.TreeId;
import com.ywc.util.treeutil.annotation.TreeParentId;
import com.ywc.util.treeutil.exception.TreeInitializeException;

import java.util.ArrayList;
import java.util.List;

/**
 * 组装树型数据工具
 * 使用方法：在Bean的Id,父Id,子节点属性上分别添加@TreeId,@TreeParentId,@TreeChild
 *
 * @param <T>  需要组装的Bean类型
 * @author yanwe
 */
public class TreeUtil<T> {

    private List<T> returnList = new ArrayList<>();

    private boolean order;

    /**
     * 需要排序的属性名
     */
    private String orderKey;

    /**
     * 正序还是倒序 0正序 1倒序
     */
    private int sortMode;

    public static final int ASC = 0;//正序

    public static final int DESC = 1;//倒序

    public TreeUtil() {
    }

    public TreeUtil(String orderKey, int sortMode) {
        this.order = true;
        this.orderKey = orderKey;
        this.sortMode = sortMode;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param sourceList 数据源
     * @param parentId   父id
     * @return 层级结果
     */
    public List<T> getChildNodesByParentId(List<T> sourceList, Object parentId) throws TreeInitializeException {
        if (sourceList == null || sourceList.isEmpty() || parentId == null) {
            LogTemplate.info("sourceList=" + sourceList + ",parentId=" + parentId, "菜单获取子节点");
            throw new TreeInitializeException("数据源为 Null 或 为空 或 parentId 为 Null");
        }
        if (order && sourceList != null && !sourceList.isEmpty()) {
            ListSortUtil.sortMap(sourceList, orderKey, sortMode);
        }
        for (T node : sourceList) {
            //遍历所有的父节点下的所有子节点
            if (getParentId(node).equals(parentId.toString())) {
                recursion(sourceList, node);
                returnList.add(node);
            }
        }
        return returnList;
    }

    /**
     * 根据节点的ID获取所有子节点
     *
     * @param sourceList 数据源
     * @param topId      根Id
     * @param count
     * @return 层级结果
     */
    public List<T> getChildNodesByTopId(List<T> sourceList, Object topId, Integer count) throws TreeInitializeException {
        if (sourceList == null || sourceList.isEmpty() || topId == null) {
            throw new TreeInitializeException("数据源为 Null 或 为空 或 topId 为 Null");
        }
        if (order && sourceList != null && !sourceList.isEmpty()) {
            ListSortUtil.sortMap(sourceList, orderKey, sortMode);
        }
        for (T node : sourceList) {
            //遍历所有的父节点下的所有子节点
            if (getId(node).equals(topId.toString())) {
                recursion(sourceList, node);
                returnList.add(node);
            }
        }
        return returnList;
    }

    private void recursion(List<T> sourceList, T node) {
        List<T> childList = getChildList(sourceList, node);//得到子节点列表
        if (order && childList != null && !childList.isEmpty()) {
            ListSortUtil.sortMap(childList, orderKey, sortMode);
        }
        if (hasChild(sourceList, node)) {//判断是否有子节点
            setChild(node, childList);
            for (T childNode : childList) {
                recursion(sourceList, childNode);
            }
        } else {
            setChild(node, childList);
        }
    }

    //得到子节点列表
    private List<T> getChildList(List<T> list, T node) {
        List<T> nodeList = new ArrayList<>();
        for (T childNode : list) {
            if (getParentId(childNode).equals(getId(node))) {
                nodeList.add(childNode);
            }
        }
        return nodeList;
    }

    // 判断是否有子节点
    private boolean hasChild(List<T> list, T node) {
        return getChildList(list, node).size() > 0;
    }

    private String getParentId(T node) {
        return ReflectUtil.getFiledValueByAnnotation(node, TreeParentId.class);
    }

    private String getId(T node) {
        return ReflectUtil.getFiledValueByAnnotation(node, TreeId.class);
    }

    private void setChild(T node, List<T> nodeList) {
        ReflectUtil.setFiledValueByAnnotation(node, TreeChild.class, nodeList);
    }

}