package com.bw.movie.api;

/**
 * @name APP
 * @class name：com.bw.movie.api
 * @class describe
 * @anthor 24673
 * @time 2019/11/11 19:37
 * @change
 * @chang time
 * @class describe
 */
public interface Api {
    //登录
    String LOGIN_PATH = "movieApi/user/v2/login";
    //注册
    String REGISTER_PATH = "movieApi/user/v2/register";
    //发送邮箱验证码
    String SEND_CODE = "movieApi/user/v2/sendOutEmailCode";
    //检测新版本
    String UPDATE_APK = "movieApi/tool/v1/findNewVersion";
    //下载
    String APK = "media/movie.apk";
    //banner轮播图
    String BANNER_URL = "movieApi/tool/v2/banner";
    //正在上映
    String RELEASE_URL = "movieApi/movie/v2/findReleaseMovieList";
    //即将上映
    String COMING_SOON_URL = "movieApi/movie/v2/findComingSoonMovieList";
    //热门电影
    String HOT_MOVIE_URL = "movieApi/movie/v2/findHotMovieList";
    //推荐影院
    String RECOMMEND = "movieApi/cinema/v1/findRecommendCinemas";
    //推荐影院
    String NEARBY = "movieApi/cinema/v1/findNearbyCinemas";
    //查询区域
    String REGION = "movieApi/tool/v2/findRegionList";
    //根据区域查询影院
    String CINEMA_BY = "movieApi/cinema/v2/findCinemaByRegion";
    //电影信息明细
    String CINEMAINFO = "movieApi/cinema/v1/findCinemaInfo";
    //查询电影详情
    String DETAIL = "movieApi/movie/v2/findMoviesDetail";
    //查询影院用户评论列表
    String COMMENT = "movieApi/cinema/v1/findAllCinemaComment";
    //根据电影的id查询电影评论
    String MOVIEID = "movieApi/movie/v2/findAllMovieComment";
    //查询一周排期的时间
    String DATE_LIST = "movieApi/tool/v2/findDateList";
    //根据影厅id 查询座位信息
    String SEATINFO = "movieApi/movie/v2/findSeatInfo";
    //根据电影ID和影院ID查询电影排期列表
    String MOVIESCHEDULE = "movieApi/movie/v2/findMovieSchedule";
    //根据电影ID和影院ID查询电影排期列表
    String BYDATE = "movieApi/movie/v2/findCinemasInfoByDate";
    //微信支付
    String WXPAY = "movieApi/movie/v2/verify/pay";
    //支付宝支付
    String ZFBPAY = "movieApi/movie/v2/verify/pay";
    //购票下单
    String TICKETS = "movieApi/movie/v2/verify/buyMovieTickets";
    //关注电影
    String FOLLOWMOVIE = "movieApi/movie/v1/verify/followMovie";
    //取消关注电影
    String CANCELFOLLOWMOVIE = "movieApi/movie/v1/verify/cancelFollowMovie";
    //关注影院
    String FOLLOWCINEMA = "movieApi/cinema/v1/verify/followCinema";
    //取消关注影院
    String CANCELFOLLOWCINEMA = "movieApi/cinema/v1/verify/cancelFollowCinema";
    //根据关键字查询电影信息
    String KEY_WROD = "movieApi/movie/v2/findMovieByKeyword";
    //查询影院下的电影排期
    String SCHEDULE_LIST = "movieApi/cinema/v2/findCinemaScheduleList";
    //上传头像
    String HEAD_PIC = "movieApi/user/v1/verify/uploadHeadPic";
    //添加用户对影片的评论
    String MOVIE_COMMENT = "movieApi/movie/v1/verify/movieComment";
    //我的电影票
    String MY_MOVIE = "movieApi/user/v2/verify/findMyMovieTicket";
    //查询用户预约电影信息
    String FIND_USER = "movieApi/user/v2/verify/findUserReserve";
    //查询看过的电影
    String SEEN_MOVIE = "movieApi/user/v2/verify/findSeenMovie";
    //购票记录
    String TICKET = "movieApi/user/v2/verify/findUserBuyTicketRecord";
    //查询用户关注电影列表
    String FOLLOW_MOVIE = "movieApi/user/v2/verify/findUserFollowMovieList";
    //查询用户关注影院列表
    String FOLLOW_CINEMA = "movieApi/user/v2/verify/findUserFollowCinemaList";
    //查看订单详情
    String ORDERID = "movieApi/user/v2/verify/findBuyTicketRecordByOrderId";
    //查询取票码
    String FIND_EXCHANGE = "movieApi/user/v2/verify/findExchangeCode";
    //意见反馈
    String RECORD_FEED = "movieApi/tool/v1/verify/recordFeedBack";
    //微信登录
    String WX_LOGIN = "movieApi/user/v1/weChatBindingLogin";
    //微信分享
    String WX_SHARE = "movieApi/tool/v1/wxShare";
    //查询我对电影的评论列表
    String MOVIE_COMMENT_LIST = "movieApi/user/v2/verify/findMyMovieCommentList";
    //查询我对影院评论列表
    String CINEMA_COMMENT_LIST = "movieApi/user/v2/verify/findMyMovieCommentList";
}
