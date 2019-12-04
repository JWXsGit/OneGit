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
public class Movie_ListBean {
    /**
     * result : [{"commentTime":1575256444000,"director":"吕乐","imageUrl":"http://172.17.8.100/images/movie/stills/zdn/zdn1.jpg","movieId":21,"movieName":"找到你","movieScore":0,"myCommentContent":"321","myCommentScore":4.5,"starring":"姚晨,马伊琍,袁文康,吴昊宸"},{"commentTime":1575255791000,"director":"\r\n李仁港","imageUrl":"http://172.17.8.100/images/movie/stills/pdz/pdz1.jpg","movieId":25,"movieName":"攀登者","movieScore":0,"myCommentContent":"55","myCommentScore":4.5,"starring":"吴京,章子怡,井柏然,胡歌"},{"commentTime":1575204518000,"director":"\r\n刘伟强","imageUrl":"http://172.17.8.100/images/movie/stills/zgjz/zgjz1.jpg","movieId":24,"movieName":"中国机长","movieScore":0,"myCommentContent":"77788999","myCommentScore":4.5,"starring":"张涵予,欧豪,袁泉,张天爱,李沁"},{"commentTime":1575204411000,"director":"韩延","imageUrl":"http://172.17.8.100/images/movie/stills/dwsj/dwsj1.jpg","movieId":10,"movieName":"动物世界","movieScore":0,"myCommentContent":"哈哈哈","myCommentScore":4.5,"starring":"李易峰,迈克尔·道格拉斯,周冬雨"},{"commentTime":1575204061000,"director":"钱嘉乐","imageUrl":"http://172.17.8.100/images/movie/stills/hjxd/hjxd1.jpg","movieId":18,"movieName":"黄金兄弟","movieScore":0,"myCommentContent":"555","myCommentScore":4.5,"starring":"郑伊健,陈小春,谢天华,林晓峰"}]
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
         * commentTime : 1575256444000
         * director : 吕乐
         * imageUrl : http://172.17.8.100/images/movie/stills/zdn/zdn1.jpg
         * movieId : 21
         * movieName : 找到你
         * movieScore : 0
         * myCommentContent : 321
         * myCommentScore : 4.5
         * starring : 姚晨,马伊琍,袁文康,吴昊宸
         */

        private long commentTime;
        private String director;
        private String imageUrl;
        private int movieId;
        private String movieName;
        private int movieScore;
        private String myCommentContent;
        private double myCommentScore;
        private String starring;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public int getMovieScore() {
            return movieScore;
        }

        public void setMovieScore(int movieScore) {
            this.movieScore = movieScore;
        }

        public String getMyCommentContent() {
            return myCommentContent;
        }

        public void setMyCommentContent(String myCommentContent) {
            this.myCommentContent = myCommentContent;
        }

        public double getMyCommentScore() {
            return myCommentScore;
        }

        public void setMyCommentScore(double myCommentScore) {
            this.myCommentScore = myCommentScore;
        }

        public String getStarring() {
            return starring;
        }

        public void setStarring(String starring) {
            this.starring = starring;
        }
    }
}
