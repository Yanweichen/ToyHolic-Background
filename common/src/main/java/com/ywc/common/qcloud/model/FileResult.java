package com.ywc.common.qcloud.model;

/**
 * 文件返回结果
 *
 * @author yanwe
 *         createTime 2017-04-2017/4/26 20:47
 */
public class FileResult {

    private int code;

    private String message;

    private String request_id;

    private Data data;

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setRequest_id(String request_id){
        this.request_id = request_id;
    }
    public String getRequest_id(){
        return this.request_id;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }

    private class Data {
        private String access_url;

        private String resource_path;

        private String source_url;

        private String url;

        private String vid;

        public void setAccess_url(String access_url){
            this.access_url = access_url;
        }
        public String getAccess_url(){
            return this.access_url;
        }
        public void setResource_path(String resource_path){
            this.resource_path = resource_path;
        }
        public String getResource_path(){
            return this.resource_path;
        }
        public void setSource_url(String source_url){
            this.source_url = source_url;
        }
        public String getSource_url(){
            return this.source_url;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public String getUrl(){
            return this.url;
        }
        public void setVid(String vid){
            this.vid = vid;
        }
        public String getVid(){
            return this.vid;
        }

    }
}
