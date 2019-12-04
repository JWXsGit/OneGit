package com.bw.movie.bean;

/**
 * @name APP
 * @class name：com.bw.movie.bean
 * @class describe
 * @anthor 24673
 * @time 2019/11/21 14:49
 * @change
 * @chang time
 * @class describe
 */
public class ZFBParBean {
    private String status;
    private String message;
    private String result;

    @Override
    public String toString() {
        return "ZFBParBean{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
