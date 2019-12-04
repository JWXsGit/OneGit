package com.bw.movie.bean;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.bean
 * @class describe
 * @anthor 24673
 * @time 2019/12/3 20:29
 * @change
 * @chang time
 * @class describe
 */
public class Cinema_ListBean {
    /**
     * result : [{"address":"远大路1号B座5层魔影国际影城","cinemaId":4,"cinemaName":"魔影国际影城","commentTime":1575285688000,"distance":12228111,"logo":"http://172.17.8.100/images/movie/logo/mygj.jpg","myCommentContent":"牛"},{"address":"建国门外大街1号国贸商城区域三地下一层3B120","cinemaId":7,"cinemaName":"北京百丽宫影城","commentTime":1575285680000,"distance":12243741,"logo":"http://172.17.8.100/images/movie/logo/blg.jpg","myCommentContent":"牛牪犇逼"},{"address":"前门大街大栅栏街36号","cinemaId":2,"cinemaName":"大观楼电影院","commentTime":1575285677000,"distance":12239130,"logo":"http://172.17.8.100/images/movie/logo/dgl.jpg","myCommentContent":"牛牪犇逼"},{"address":"滨河路乙1号雍和航星园74-76号楼","cinemaId":1,"cinemaName":"青春光线电影院","commentTime":1575285672000,"distance":12239585,"logo":"http://172.17.8.100/images/movie/logo/qcgx.jpg","myCommentContent":"牛牪犇逼"}]
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
         * address : 远大路1号B座5层魔影国际影城
         * cinemaId : 4
         * cinemaName : 魔影国际影城
         * commentTime : 1575285688000
         * distance : 12228111
         * logo : http://172.17.8.100/images/movie/logo/mygj.jpg
         * myCommentContent : 牛
         */

        private String address;
        private int cinemaId;
        private String cinemaName;
        private long commentTime;
        private int distance;
        private String logo;
        private String myCommentContent;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

        public String getCinemaName() {
            return cinemaName;
        }

        public void setCinemaName(String cinemaName) {
            this.cinemaName = cinemaName;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getMyCommentContent() {
            return myCommentContent;
        }

        public void setMyCommentContent(String myCommentContent) {
            this.myCommentContent = myCommentContent;
        }
    }
}
