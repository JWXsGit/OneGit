package com.bw.movie.bean;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.bean
 * @class describe
 * @anthor 24673
 * @time 2019/11/19 18:23
 * @change
 * @chang time
 * @class describe
 */
public class MovieBean {
    /**
     * result : [{"commentContent":"","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":2167,"commentTime":1573823763000,"commentUserId":13764,"commentUserName":"鱼清","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-11-16/20191116105137.png","commentId":2163,"commentTime":1573818695000,"commentUserId":13627,"commentUserName":"吴浩然","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":9.9},{"commentContent":"依旧是那个冷枼璇","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-09-29/20190929211644.jpg","commentId":2157,"commentTime":1573782828000,"commentUserId":13701,"commentUserName":"冷枼璇","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":9.5},{"commentContent":"很好","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-11-15/20191115204235.unknown","commentId":2155,"commentTime":1573735554000,"commentUserId":13777,"commentUserName":"tian","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":10},{"commentContent":"电影还行","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-11-15/20191115201000.png","commentId":2154,"commentTime":1573729512000,"commentUserId":13810,"commentUserName":"棒打猪猪侠","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":6.6}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commentContent :
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * commentId : 2167
         * commentTime : 1573823763000
         * commentUserId : 13764
         * commentUserName : 鱼清
         * greatNum : 0
         * isGreat : 0
         * replyHeadPic : []
         * replyNum : 0
         * score : 4.5
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int isGreat;
        private int replyNum;
        private double score;
        private List<?> replyHeadPic;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public List<?> getReplyHeadPic() {
            return replyHeadPic;
        }

        public void setReplyHeadPic(List<?> replyHeadPic) {
            this.replyHeadPic = replyHeadPic;
        }
    }
}
