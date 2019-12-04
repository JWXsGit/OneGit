package com.bw.movie.api;

import com.bw.movie.bean.ByDateBean;
import com.bw.movie.bean.Cinema_ListBean;
import com.bw.movie.bean.CinemainfoBean;
import com.bw.movie.bean.DateListBean;
import com.bw.movie.bean.DetailBean;
import com.bw.movie.bean.ExChanegBean;
import com.bw.movie.bean.Find_UserBean;
import com.bw.movie.bean.FollowBean;
import com.bw.movie.bean.Follow_CinemaBean;
import com.bw.movie.bean.Follow_MovieBean;
import com.bw.movie.bean.ImageBean;
import com.bw.movie.bean.KeyWorkBean;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.bean.Movie_CommentBean;
import com.bw.movie.bean.Movie_ListBean;
import com.bw.movie.bean.My_MovieBean;
import com.bw.movie.bean.OrderidBean;
import com.bw.movie.bean.RecordBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.Schedule_ListBean;
import com.bw.movie.bean.SeatInfoBean;
import com.bw.movie.bean.Seen_MovieBean;
import com.bw.movie.bean.TicketBean;
import com.bw.movie.bean.TicketsBean;
import com.bw.movie.bean.WXPayBean;
import com.bw.movie.bean.WX_LoginBean;
import com.bw.movie.bean.WX_ShearBean;
import com.bw.movie.bean.ZFBParBean;
import com.bw.movie.bean.fragment_bean.BannerBean;
import com.bw.movie.bean.CodeBean;
import com.bw.movie.bean.fragment_bean.Cinema_byBean;
import com.bw.movie.bean.fragment_bean.ComingBean;
import com.bw.movie.bean.fragment_bean.CommentBean;
import com.bw.movie.bean.fragment_bean.HotMovieBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.fragment_bean.NearbyBean;
import com.bw.movie.bean.fragment_bean.NewVersionBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.fragment_bean.RecommendBean;
import com.bw.movie.bean.fragment_bean.RegionBean;
import com.bw.movie.bean.fragment_bean.ReleaseBean;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * @name APP
 * @class name：com.bw.movie.api
 * @class describe
 * @anthor 24673
 * @time 2019/11/11 19:35
 * @change
 * @chang time
 * @class describe
 */
public interface HttpApi {
    @FormUrlEncoded
    @POST(Api.REGISTER_PATH)
    Observable<RegisterBean> onRegister(
            @Field("nickName") String nickName,
            @Field("pwd") String pwd,
            @Field("email") String email,
            @Field("code") String code);

    @FormUrlEncoded
    @POST(Api.LOGIN_PATH)
    Observable<LoginBean> onLogin(
            @Field("email") String email,
            @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST(Api.SEND_CODE)
    Observable<CodeBean> onCode(
            @Field("email") String email);

    @GET(Api.UPDATE_APK)
    Observable<NewVersionBean> onNetVersion(@Header("userId") int userId, @Header("sessionId") String sessionId, @Header("ak") String ak);

    @Streaming
    @GET(Api.APK)
    Observable<ResponseBody> onDownLoad(@Header("RANGE") String start);

    //Banner轮播图
    @GET(Api.BANNER_URL)
    Observable<BannerBean> onBannerImage();

    //正在上映
    @GET(Api.RELEASE_URL)
    Observable<ReleaseBean> onRelease(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);


    //即将上映
    @GET(Api.COMING_SOON_URL)
    Observable<ComingBean> onComing(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

    //热门电影
    @GET(Api.HOT_MOVIE_URL)
    Observable<HotMovieBean> onFindHot(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

    //推荐影院
    @GET(Api.RECOMMEND)
    Observable<RecommendBean> onRecommend(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

    //附近影院
    @GET(Api.NEARBY)
    Observable<NearbyBean> onNearby(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

    //地区区域
    @GET(Api.REGION)
    Observable<RegionBean> onRegion();

    //根据区域查询影院
    @GET(Api.CINEMA_BY)
    Observable<Cinema_byBean> onCinemaBy(@Query("regionId") int regionId);

    //查询电影信息明细
    @GET(Api.CINEMAINFO)
    Observable<CinemainfoBean> onCinemaInfo(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("cinemaId") int cinemaId);

    //查询电影详情
    @GET(Api.DETAIL)
    Observable<DetailBean> onDetail(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("movieId") int movieId);

    //根据电影的id查询电影评论
    @GET(Api.MOVIEID)
    Observable<MovieBean> onMovie(@Query("movieId") int movieId, @Query("page") int page, @Query("count") int count);

    //查询影院用户评论列表
    @GET(Api.COMMENT)
    Observable<CommentBean> onComment(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("cinemaId") int cinemaId, @Query("page") int page, @Query("count") int count);

    //查询一周排期的时间
    @GET(Api.DATE_LIST)
    Observable<DateListBean> onDateList();

    //根据影厅id 查询座位信息
    @GET(Api.SEATINFO)
    Observable<SeatInfoBean> onSeatInfo(@Query("hallId") int movieId);

    //根据电影ID和影院ID查询电影排期列表
    @GET(Api.MOVIESCHEDULE)
    Observable<ScheduleBean> onSchedule(@Query("movieId") int movieId, @Query("cinemaId") int cinemaId);

    //根据电影ID和影院ID查询电影排期列表
    @GET(Api.BYDATE)
    Observable<ByDateBean> onByDateBean(@Query("movieId") int movieId, @Query("date") String date, @Query("page") int page, @Query("count") int count);

    @FormUrlEncoded
    //购票下单
    @POST(Api.TICKETS)
    Observable<TicketsBean> onTickets(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("scheduleId") int scheduleId, @Field("seat") String seat, @Field("sign") String sign);

    //微信支付
    @FormUrlEncoded
    @POST(Api.WXPAY)
    Observable<WXPayBean> onWX(@Header("userId") int userId,
                               @Header("sessionId") String sessionId,
                               @Field("payType") int payType,
                               @Field("orderId") String orderId);

    @FormUrlEncoded
    //支付宝支付
    @POST(Api.ZFBPAY)
    Observable<ZFBParBean> onZFBPaY(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("payType") int payType, @Field("orderId") String orderId);

    //关注电影
    @GET(Api.FOLLOWMOVIE)
    Observable<FollowBean> onFollow(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("movieId") int movieId);

    //取消关注电影
    @GET(Api.CANCELFOLLOWMOVIE)
    Observable<FollowBean> onCancelFollow(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("movieId") int movieId);

    //关注影院
    @GET(Api.FOLLOWCINEMA)
    Observable<FollowBean> onCinema_Follow(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("movieId") int movieId);

    //取消关注影院
    @GET(Api.CANCELFOLLOWCINEMA)
    Observable<FollowBean> onCinema_CancelFollow(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("cinemaId") int movieId);

    //根据关键字查询电影信息
    @GET(Api.KEY_WROD)
    Observable<KeyWorkBean> onKeyWork(@Query("keyword") String keyword, @Query("page") int page, @Query("count") int count);

    //查询影院下的电影排期
    @GET(Api.SCHEDULE_LIST)
    Observable<Schedule_ListBean> onSchedule_List(@Query("cinemaId") int cinemaId, @Query("page") int page, @Query("count") int count);

    //上传头像
    @Multipart
    @POST(Api.HEAD_PIC)
    Observable<ImageBean> onImage(@Header("userId") int userId, @Header("sessionId") String sessionId, @Part("image") MultipartBody.Part image);

    //添加用户对影片的评论
    @FormUrlEncoded
    @POST(Api.MOVIE_COMMENT)
    Observable<Movie_CommentBean> onMovie_Comment(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("movieId") int movieId, @Field("commentContent") String commentContent, @Field("score") double score);

    //我的电影票
    @GET(Api.MY_MOVIE)
    Observable<My_MovieBean> onMy_Movie(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //查询用户预约电影信息
    @GET(Api.FIND_USER)
    Observable<Find_UserBean> onFine_User(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //查询看过的电影
    @GET(Api.SEEN_MOVIE)
    Observable<Seen_MovieBean> onSeen_Movie(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //购票记录
    @GET(Api.TICKET)
    Observable<TicketBean> onTicket(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count, @Query("status") int status);

    //查询用户关注电影列表
    @GET(Api.FOLLOW_MOVIE)
    Observable<Follow_MovieBean> onFollow_Movie(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

    //查询用户关注影院列表
    @GET(Api.FOLLOW_CINEMA)
    Observable<Follow_CinemaBean> onFollow_Cinema(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

    //查看订单详情
    @GET(Api.ORDERID)
    Observable<OrderidBean> onOrderId(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("orderId") String orderId);

    //查询取票码
    @GET(Api.FIND_EXCHANGE)
    Observable<ExChanegBean> onExChange(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("recordId") int recordId);

    //意见反馈
    @FormUrlEncoded
    @POST(Api.RECORD_FEED)
    Observable<RecordBean> onRecord(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("content") String content);

    //微信登录
    @FormUrlEncoded
    @POST(Api.WX_LOGIN)
    Observable<WX_LoginBean> onWx_Login(@Field("code") String code);

    //微信分享
    @GET(Api.WX_SHARE)
    Observable<WX_ShearBean> onWx_Shear(@Query("time") String time, @Query("sign") String sign);

    //查询我对影院评论列表
    @GET(Api.CINEMA_COMMENT_LIST)
    Observable<Cinema_ListBean> onCinema(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("longitude") String longitude, @Query("latitude") String latitude, @Query("page") int page, @Query("count") int count);

    //查询我对电影的评论列表
    @GET(Api.MOVIE_COMMENT_LIST)
    Observable<Movie_ListBean> onMovie(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);
}