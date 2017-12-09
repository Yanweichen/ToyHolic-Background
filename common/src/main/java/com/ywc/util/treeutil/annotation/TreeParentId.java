package com.ywc.util.treeutil.annotation;

import java.lang.annotation.*;

/**
 * 父id注解
 * @author yanwe
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TreeParentId {
}
