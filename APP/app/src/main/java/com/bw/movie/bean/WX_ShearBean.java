package com.bw.movie.bean;

/**
 * @name APP
 * @class name：com.bw.movie.bean
 * @class describe
 * @anthor 24673
 * @time 2019/12/3 14:48
 * @change
 * @chang time
 * @class describe
 */
public class WX_ShearBean {
    /**
     * appId : wxb3852e6a6b7d9516
     * appSecret : uocIMXshVdbH8IUDdGJ9tpXDE7DOc1AAFswe9Jcxiwe4G/TyWyB8Kz/gvNGg/fFb
     * message : 分享成功
     * status : 0000
     */

    private String appId;
    private String appSecret;
    private String message;
    private String status;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
