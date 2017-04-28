package com.ywc.common.qcloud.file;

import com.alibaba.fastjson.JSON;
import com.qcloud.cos.request.DelFileRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.ywc.common.qcloud.QCloudManager;
import com.ywc.common.qcloud.model.FileResult;
import com.ywc.util.PropertyUtil;

/**
 * 文件管理
 *
 * @author yanwe
 *         createTime 2017-04-2017/4/26 16:05
 */
public class FileManager {

    private static final QCloudManager Q_CLOUD_MANAGER = new QCloudManager();

    private static final String BUCKET_NAME = PropertyUtil.getCtxProp("qcloud_cos_bucketName");

    public static FileResult upload(byte[] file, String remotePath){
        UploadFileRequest uploadFileRequest = new UploadFileRequest(BUCKET_NAME,remotePath,file);
        String uploadFile = Q_CLOUD_MANAGER.getCosClient().uploadFile(uploadFileRequest);
        return JSON.parseObject(uploadFile,FileResult.class);
    }

    public static FileResult delete(String remotePath){
        DelFileRequest delFileRequest = new DelFileRequest(BUCKET_NAME, remotePath);
        String delFileRet = Q_CLOUD_MANAGER.getCosClient().delFile(delFileRequest);
        return JSON.parseObject(delFileRet,FileResult.class);
    }
}
