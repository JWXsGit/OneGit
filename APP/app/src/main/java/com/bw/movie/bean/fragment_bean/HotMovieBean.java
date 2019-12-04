package com.bw.movie.bean.fragment_bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：86157<p>
 * <p>创建时间：2019/10/14<p>
 * <p>更改时间：2019/10/14<p>
 */
public class HotMovieBean {

    /**
     * result : [{"director":"庄文强","horizontalImage":"http://172.17.8.100/images/movie/stills/ws/ws1_h.jpg","imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws1.jpg","movieId":20,"name":"无双","score":8.6,"starring":"周润发,郭富城,张静初,冯文娟,廖启智"},{"director":"贾樟柯","horizontalImage":"http://172.17.8.100/images/movie/stills/jhen/jhen1_h.jpg","imageUrl":"http://172.17.8.100/images/movie/stills/jhen/jhen1.jpg","movieId":19,"name":"江湖儿女","score":9.7,"starring":"赵涛,廖凡,徐峥,梁嘉艳"},{"director":"钱嘉乐","horizontalImage":"http://172.17.8.100/images/movie/stills/hjxd/hjxd1_h.jpg","imageUrl":"http://172.17.8.100/images/movie/stills/hjxd/hjxd1.jpg","movieId":18,"name":"黄金兄弟","score":9.3,"starring":"郑伊健,陈小春,谢天华,林晓峰"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;


    public static class ResultBean {
        /**
         * director : 庄文强
         * horizontalImage : http://172.17.8.100/images/movie/stills/ws/ws1_h.jpg
         * imageUrl : http://172.17.8.100/images/movie/stills/ws/ws1.jpg
         * movieId : 20
         * name : 无双
         * score : 8.6
         * starring : 周润发,郭富城,张静初,冯文娟,廖启智
         */

        public String director;
        public String horizontalImage;
        public String imageUrl;
        public int movieId;
        public String name;
        public double score;
        public String starring;
    }
}
