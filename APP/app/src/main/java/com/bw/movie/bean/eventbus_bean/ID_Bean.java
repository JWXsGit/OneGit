package com.bw.movie.bean.eventbus_bean;

/**
 * @name APP
 * @class name：com.bw.movie.bean.eventbus_bean
 * @class describe
 * @anthor 24673
 * @time 2019/11/12 19:34
 * @change
 * @chang time
 * @class describe
 */
public class ID_Bean {
    private int userId;
    private String sessionId;

    @Override
    public String toString() {
        return "ID_Bean{" +
                "userId=" + userId +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
