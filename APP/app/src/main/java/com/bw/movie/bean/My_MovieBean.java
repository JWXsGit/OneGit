package com.bw.movie.bean;

import java.util.List;

/**
 * @name APP
 * @class name：com.bw.movie.bean
 * @class describe
 * @anthor 24673
 * @time 2019/12/2 11:35
 * @change
 * @chang time
 * @class describe
 */
public class My_MovieBean {

    /**
     * result : [{"amount":0,"beginTime":"17:50:00","cinemaName":"东融国际影城西直河店","createTime":1575095571000,"endTime":"19:25:00","id":5416,"movieName":"中国机长","price":0,"screeningHall":"杜比厅","seat":"3-4","status":0,"userId":0},{"amount":0,"beginTime":"13:20:00","cinemaName":"星美国际影城","createTime":1571971217000,"endTime":"15:02:00","id":1978,"movieName":"找到你","price":0,"screeningHall":"1号厅","seat":"7-5","status":0,"userId":0},{"amount":0,"beginTime":"13:20:00","cinemaName":"星美国际影城","createTime":1571971175000,"endTime":"15:02:00","id":1977,"movieName":"找到你","price":0,"screeningHall":"1号厅","seat":"7-5","status":0,"userId":0},{"amount":0,"beginTime":"13:20:00","cinemaName":"北京耀莱成龙国际影城","createTime":1571970911000,"endTime":"15:02:00","id":1974,"movieName":"找到你","price":0,"screeningHall":"1号厅","seat":"7-5","status":0,"userId":0},{"amount":0,"beginTime":"13:20:00","cinemaName":"星美国际影城","createTime":1571905426000,"endTime":"15:02:00","id":1687,"movieName":"找到你","price":0,"screeningHall":"1号厅","seat":"7-5","status":0,"userId":0}]
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
         * amount : 0
         * beginTime : 17:50:00
         * cinemaName : 东融国际影城西直河店
         * createTime : 1575095571000
         * endTime : 19:25:00
         * id : 5416
         * movieName : 中国机长
         * price : 0
         * screeningHall : 杜比厅
         * seat : 3-4
         * status : 0
         * userId : 0
         */

        private int amount;
        private String beginTime;
        private String cinemaName;
        private long createTime;
        private String endTime;
        private int id;
        private String movieName;
        private int price;
        private String screeningHall;
        private String seat;
        private int status;
        private int userId;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getCinemaName() {
            return cinemaName;
        }

        public void setCinemaName(String cinemaName) {
            this.cinemaName = cinemaName;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getScreeningHall() {
            return screeningHall;
        }

        public void setScreeningHall(String screeningHall) {
            this.screeningHall = screeningHall;
        }

        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
