package com.ywc.common.log;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 日志操作类
 *
 * @author johnmyiqn.
 */
@Component
public class LogTemplate {

    private static final String LOG_FORMAT = "log:{} >> context:{}";
    /**
     * 日志对象
     */
    private static Logger logger;

    /**
     * 打印日志debug级别，开发调试阶段打印，上线后不需要打印
     * @param desc 日志描述信息
     * @param debugData 日志debug级别数据
     * @param clazz 日志类
     */
    public static void debug(Class<?> clazz, String desc, String debugData) {
        if (logger == null) {
            logger = LogBase.get(clazz);
        }
        if (debugData == null) {
            debugData = "";
        }
        //打印日志信息
        LogBase.debug(logger, LOG_FORMAT, desc, debugData);
    }

    /**
     * 打印日志info级别，开发和上线为了定位问题打印日志
     * @param clazz 日志类
     * @param desc 日志描述信息
     * @param infoData 日志debug级别数据
     */
    public static void info(Class<?> clazz, String desc, String infoData) {
        if (logger == null) {
            logger = LogBase.get(clazz);
        }
        if (infoData == null) {
            infoData = "";
        }
        //打印日志信息
        LogBase.info(logger, LOG_FORMAT, desc, infoData);
    }

    /**
     * 打印日志warn级别，开发和上线为了定位出现错误或者异常，但是不至于导致程序报错或者程序崩溃日志
     *
     * @param clazz 日志类
     * @param e 异常信息
     * @param desc 日志描述信息
     * @param warnData 日志debug级别数据
     */
    public static void warn(Class<?> clazz, Throwable e, String desc, String warnData) {
        if (logger == null) {
            logger = LogBase.get(clazz);
        }
        if (warnData == null) {
            warnData = "";
        }
        //打印日志信息
        LogBase.warn(logger, e, LOG_FORMAT, desc, warnData);
    }

    /**
     * 打印日志warn级别，开发和上线为了定位出现错误或者异常，但是不至于导致程序报错或者程序崩溃日志
     *
     * @param desc     日志描述信息
     * @param e 异常信息
     * @param clazz 日志类
     * @param warnData 日志debug级别数据
     */
    public static void error(Class<?> clazz, Throwable e, String desc, String warnData) {
        if (logger == null) {
            logger = LogBase.get(clazz);
        }
        if (warnData == null) {
            warnData = "";
        }
        //打印日志信息
        LogBase.error(logger, e, LOG_FORMAT, desc, warnData);
    }

    /**
     * 打印日志debug级别，开发调试阶段打印，上线后不需要打印
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param desc      日志描述信息
     * @param debugData 日志debug级别数据
     */
    public static void debug(String desc, String debugData) {
        if (logger == null) {
            logger = LogBase.get();
        }
        if (debugData == null) {
            debugData = "";
        }
        //打印日志信息
        LogBase.debug(logger, LOG_FORMAT, desc, debugData);
    }

    /**
     * 打印日志info级别，开发和上线为了定位问题打印日志
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param desc      日志描述信息
     * @param inforData 日志debug级别数据
     */
    public static void info(String desc, String inforData) {
        if (logger == null) {
            logger = LogBase.get();
        }
        if (inforData == null) {
            inforData = "";
        }
        //打印日志信息
        LogBase.info(logger, LOG_FORMAT, desc, inforData);
    }

    /**
     * 打印日志warn级别，开发和上线为了定位出现错误或者异常，但是不至于导致程序报错或者程序崩溃日志
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param e 异常信息
     * @param desc 日志描述信息
     * @param warnData 日志debug级别数据
     */
    public static void warn(Throwable e, String desc, String warnData) {
        if (logger == null) {
            logger = LogBase.get();
        }
        //打印日志信息
        LogBase.warn(logger, e, LOG_FORMAT, desc, warnData);
    }

    /**
     *   * 打印日志warn级别，开发和上线为了定位出现错误或者异常，但是不至于导致程序报错或者程序崩溃日志
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     *
     * @param desc      日志描述信息
     * @param errorData 日志debug级别数据
     * @param e 异常信息
     */
    public static void error(Throwable e, String desc, String errorData) {
        if (logger == null) {
            logger = LogBase.get();
        }
        if (errorData == null) {
            errorData = "";
        }
        //打印日志信息
        LogBase.error(logger, e, LOG_FORMAT, desc, errorData);
    }
}
