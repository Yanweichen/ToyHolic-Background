package com.ywc.common.qcloud;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.sign.Credentials;
import com.ywc.util.PropertyUtil;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云管理类
 *
 * @author yanwe
 *         createTime 2017-04-2017/4/26 15:24
 */
@Configuration
public class QCloudManager {

    private COSClient COS_CLIENT = null;

    /**
     * 获取对象COS存储客户端
     *
     * @return COS客户度
     */
    public COSClient getCosClient() {
        if(COS_CLIENT == null){
            synchronized (QCloudManager.class){
                if(COS_CLIENT == null){
                    initCosClient();
                }
            }
        }
        return COS_CLIENT;
    }

    /**
     * 初始化COS客户端
     */
    private void initCosClient(){
        String secretId = PropertyUtil.getProperty("qcloud_secretId");
        String secretKey = PropertyUtil.getProperty("qcloud_secretKey");
        String region = PropertyUtil.getProperty("qcloud_cos_region");
        long appId = Long.parseLong(PropertyUtil.getProperty("qcloud_cos_appId"));
        Credentials cred = new Credentials(appId, secretId, secretKey);
        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域
        clientConfig.setRegion(region);
        //初始化cosClient
        COS_CLIENT = new COSClient(clientConfig, cred);
    }
}
