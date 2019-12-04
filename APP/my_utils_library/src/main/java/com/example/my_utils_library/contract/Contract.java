package com.example.my_utils_library.contract;

import com.example.my_utils_library.base.IBaseView;

import okhttp3.MultipartBody;

public interface Contract {

    interface IView extends IBaseView {
        void onSuccess(Object obj);

        void onError(Throwable e);
    }


    interface IPresenter {
        //契约类验证码方法
        void onCode(String email);

        //契约类注册方法
        void onRegister(String nickName, String pwd, String email, String code);

        //契约类登录方法
        void onLogin(String email, String pwd);

        //查询电影信息明细
        void onNameInfo(int userId, String sessionId, int cinemaId);

        //查询电影详情
        void onDetail(int movieId);

        //根据影厅id 查询座位信息
        void onSeatInfo(int hallId);

        //根据电影ID和影院ID查询电影排期列表
        void onSchedule(int cinemaId);

        //微信支付
        void onWXPay(int payType, String orderId);

        //支付宝支付
        void onZFBPay(int payType, String orderId);

        //购票下单
        void onTickets(int scheduleId, String sea);

        //关注电影
        void onFollowMovie(int movieId);

        //取消关注电影
        void onCancelFollowMovie(int movieId);

        //关注电影
        void onCinema_FollowMovie(int cinemaId);

        //取消关注电影
        void onCinema_CancelFollowMovie(int cinemaId);

        //根据关键字查询电影信息
        void onKeyWord(String keyword, int page, int count);

        //添加用户对影片的评论
        void onMovie_Comment(int movieId, String commentContent, double score);

        //我的电影票
        void onMy_Movie();

        //查询用户预约电影信息
        void onFine_User();

        //查询看过的电影
        void onSeen_Movie();

        //查看订单详情
        void onOrderId(String orderId);

        //查询取票码
        void onExChange(int recordId);

        //意见反馈
        void onRecord(String content);

        //微信登录
        void onWX_Login(String code);

        //上传图片
        void onImage(MultipartBody.Part image);
    }

    interface FPresenter {
        //检测新版本
        void onNetVersion(int userId, String sessionId, String ap);

        //Banner轮播图
        void onBannerImage();

        //契约类正在上映方法
        void onReleaseResult(int userId, String sessionId, int page, int count);

        //契约类即将上映方法
        void onComingResult(int userId, String sessionId, int page, int count);

        //契约类热门电影方法
        void onHotMovieResult(int userId, String sessionId, int page, int count);

        //推荐影院
        void onRecommend(int userId, String sessionId, int page, int count);

        //附近影院
        void onNearby(int userId, String sessionId, String longitude, String latitude, int page, int count);

        //查询地区区域
        void onRegion();

        //根据地区查询影院
        void onCinema_by(int regionId);

        //根据电影的id查询电影评论
        void onMovieId(int movieId, int page, int count);

        //查询影院用户评论列表
        void onComment(int cinemaId, int page, int count);

        //查询一周排期的时间
        void onDateList();

        //根据电影id，时间 查询播放影院信息
        void onByDate(String date, int page, int count);

        //查询影院下的电影排期
        void onSchedule_List(int cinemaId, int page, int count);

        //查询用户关注电影列表
        void onFollow_Movie(int page, int count);

        //查询用户关注影院列表
        void onFollow_Cinema(int page, int count);

        //购票记录
        void onTicket(int page, int count, int status);

        //查询我对电影的评论列表
        void onComment_List(int page, int count);

        //查询我对影院评论列表
        void onCinema_List(String longitude, String latitude, int page, int count);

        //取消关注电影
        void onCancelFollowMovie(int movieId);

        //取消关注影院
        void onCinema_CancelFollowMovie(int cinemaId);

    }


    interface IModer {
        //契约类验证码方法
        void onCode(String email, IBallBask iBallBask);

        //契约类注册方法
        void onRegister(String nickName, String pwd, String email, String code, IBallBask iBallBask);

        //契约类登录方法
        void onLogin(String email, String pwd, IBallBask iBallBask);

        //查询电影信息明细
        void onNameInfo(int userId, String sessionId, int cinemaId, IBallBask iBallBask);

        //查询电影详情
        void onDetail(int userId, String sessionId, int movieId, IBallBask iBallBask);

        //根据影厅id 查询座位信息
        void onSeatInfo(int hallId, IBallBask iBallBask);

        //根据电影ID和影院ID查询电影排期列表
        void onSchedule(int movieId, int cinemaId, IBallBask iBallBask);

        //微信支付
        void onWXPay(int userId, String sessionId, int payType, String orderId, IBallBask iBallBask);

        //支付宝支付
        void onZFBPay(int userId, String sessionId, int payType, String orderId, IBallBask iBallBask);

        //购票下单
        void onTickets(int userId, String sessionId, int scheduleId, String seat, String sign, IBallBask iBallBask);

        //关注电影
        void onFollowMovie(int userId, String sessionId, int movieId, IBallBask iBallBask);

        //取消关注电影
        void onCancelFollowMovie(int userId, String sessionId, int movieId, IBallBask iBallBask);

        //关注影院
        void onCinema_FollowMovie(int userId, String sessionId, int cinemaId, IBallBask iBallBask);

        //取消关注影院
        void onCinema_CancelFollowMovie(int userId, String sessionId, int cinemaId, IBallBask iBallBask);

        //根据关键字查询电影信息
        void onKeyWord(String keyword, int page, int count, IBallBask iBallBask);

        //添加用户对影片的评论
        void onMovie_Comment(int userId, String sessionId, int movieId, String commentContent, double score, IBallBask iBallBask);

        //我的电影票
        void onMy_Movie(int userId, String sessionId, IBallBask iBallBask);

        //查询用户预约电影信息
        void onFine_User(int userId, String sessionId, IBallBask iBallBask);

        //查询看过的电影
        void onSeen_Movie(int userId, String sessionId, IBallBask iBallBask);

        //查看订单详情
        void onOrderId(int userId, String sessionId, String orderId, IBallBask iBallBask);

        //查询取票码
        void onExChange(int userId, String sessionId, int recordId, IBallBask iBallBask);

        //意见反馈
        void onRecord(int userId, String sessionId, String content, IBallBask iBallBask);

        //微信登录
        void onWX_Login(String code, IBallBask iBallBask);

        //上传头像
        void onImage(int userId, String sessionId, MultipartBody.Part image, IBallBask iBallBask);

        interface IBallBask {
            void onHttpOK(Object obj);

            void onHttpNO(Throwable e);
        }
    }

    interface FModer {
        //检测新版本
        void onNetVersion(int userId, String sessionId, String ap, IBallBask iBallBask);

        //Banner轮播图
        void onBannerImage(IBallBask iBallBask);

        //契约类正在上映方法
        void onReleaseResult(int userId, String session, int page, int count, IModer.IBallBask iBallBask);

        //契约类即将上映方法
        void onComingResult(int userId, String session, int page, int count, IModer.IBallBask iBallBask);

        //契约类热门电影方法
        void onHotMovieResult(int userId, String session, int page, int count, IModer.IBallBask iBallBask);

        //推荐影院
        void onRecommend(int userId, String sessionId, int page, int count, IBallBask iBallBask);

        //附近影院
        void onNearby(int userId, String sessionId, String longitude, String latitude, int page, int count, IBallBask iBallBask);

        //查询地区区域
        void onRegion(IBallBask iBallBask);

        //根据地区查询影院
        void onCinema_by(int regionId, IBallBask iBallBask);

        //根据电影的id查询电影评论
        void onMovieId(int movieId, int page, int count, IBallBask iBallBask);

        //查询影院用户评论列表
        void onComment(int userId, String sessionId, int cinemaId, int page, int count, IBallBask iBallBask);

        //查询一周排期的时间
        void onDateList(IBallBask iBallBask);

        //根据电影id，时间 查询播放影院信息
        void onByDate(int movieId, String date, int page, int count, IBallBask iBallBask);

        //查询影院下的电影排期
        void onSchedule_List(int cinemaId, int page, int count, IBallBask iBallBask);

        //查询用户关注电影列表
        void onFollow_Movie(int userId, String sessionId, int page, int count, IBallBask iBallBask);

        //查询用户关注影院列表
        void onFollow_Cinema(int userId, String sessionId, int page, int count, IBallBask iBallBask);

        //购票记录
        void onTicket(int userId, String sessionId, int page, int count, int status, IModer.IBallBask iBallBask);

        //查询我对电影的评论列表
        void onMovie_List(int userId, String sessionId, int page, int count, IBallBask iBallBask);

        //查询我对影院评论列表
        void onCinema_List(int userId, String sessionId, String longitude, String latitude, int page, int count, IBallBask iBallBask);

        //取消关注电影
        void onCancelFollowMovie(int userId, String sessionId, int movieId, IBallBask iBallBask);

        //取消关注影院
        void onCinema_CancelFollowMovie(int userId, String sessionId, int cinemaId, IBallBask iBallBask);

        interface IBallBask {
            void onHttpOK(Object obj);

            void onHttpNO(Throwable e);
        }
    }
}
