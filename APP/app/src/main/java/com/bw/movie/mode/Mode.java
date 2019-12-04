package com.bw.movie.mode;

import android.util.Log;

import com.bw.movie.api.HttpApi;
import com.bw.movie.bean.CinemainfoBean;
import com.bw.movie.bean.CodeBean;
import com.bw.movie.bean.DetailBean;
import com.bw.movie.bean.ExChanegBean;
import com.bw.movie.bean.Find_UserBean;
import com.bw.movie.bean.FollowBean;
import com.bw.movie.bean.Follow_CinemaBean;
import com.bw.movie.bean.Follow_MovieBean;
import com.bw.movie.bean.ImageBean;
import com.bw.movie.bean.KeyWorkBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.Movie_CommentBean;
import com.bw.movie.bean.My_MovieBean;
import com.bw.movie.bean.OrderidBean;
import com.bw.movie.bean.RecordBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.bean.SeatInfoBean;
import com.bw.movie.bean.Seen_MovieBean;
import com.bw.movie.bean.TicketBean;
import com.bw.movie.bean.TicketsBean;
import com.bw.movie.bean.WXPayBean;
import com.bw.movie.bean.WX_LoginBean;
import com.bw.movie.bean.ZFBParBean;
import com.example.my_utils_library.contract.Contract;
import com.example.my_utils_library.utils.HttpUtils;

import okhttp3.MultipartBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @name APP
 * @class name：com.bw.movie.mode
 * @class describe
 * @anthor 24673
 * @time 2019/11/11 19:34
 * @change
 * @chang time
 * @class describe
 */
public class Mode implements Contract.IModer {
    private static final String TAG = "Mode";

    @Override
    public void onCode(String email, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onCode(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CodeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(CodeBean codeBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(codeBean);
                        }
                    }
                });
    }

    @Override
    public void onRegister(String nickName, String pwd, String email, String code, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onRegister(nickName, pwd, email, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(registerBean);
                        }
                    }
                });
    }

    @Override
    public void onLogin(String email, String pwd, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onLogin(email, pwd)
                .subscribeOn(rx.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(loginBean);
                        }
                    }
                });
    }

    @Override
    public void onNameInfo(int userId, String sessionId, int cinemaId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onCinemaInfo(userId, sessionId, cinemaId)
                .subscribeOn(rx.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemainfoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(CinemainfoBean cinemainfoBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(cinemainfoBean);
                        }
                    }
                });
    }

    @Override
    public void onDetail(int userId, String sessionId, int movieId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onDetail(userId, sessionId, movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(DetailBean detailBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(detailBean);
                        }
                    }
                });
    }

    @Override
    public void onSeatInfo(int hallId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onSeatInfo(hallId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SeatInfoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(SeatInfoBean seatInfoBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(seatInfoBean);
                        }
                    }
                });
    }

    @Override
    public void onSchedule(int movieId, int cinemaId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onSchedule(movieId, cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScheduleBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(ScheduleBean scheduleBean) {
                        if (iBallBask != null) {
                            Log.i(TAG, "onNext: " + scheduleBean.getMessage());
                            iBallBask.onHttpOK(scheduleBean);
                        }
                    }
                });
    }

    @Override
    public void onWXPay(int userId, String sessionId, int payType, String orderId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onWX(userId, sessionId, payType, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WXPayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(WXPayBean wxPayBean) {
                        if (wxPayBean != null) {
                            iBallBask.onHttpOK(wxPayBean);
                        }
                    }
                });
    }

    @Override
    public void onZFBPay(int userId, String sessionId, int payType, String orderId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onZFBPaY(userId, sessionId, payType, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZFBParBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(ZFBParBean zfbParBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(zfbParBean);
                        }
                    }
                });
    }

    @Override
    public void onTickets(int userId, String sessionId, int scheduleId, String seat, String sign, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onTickets(userId, sessionId, scheduleId, seat, sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TicketsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(TicketsBean ticketsBean) {
                        if (iBallBask != null) {
                            Log.i(TAG, "onNext: " + ticketsBean.getMessage());
                            Log.i(TAG, "onNext: " + ticketsBean.getOrderId());
                            iBallBask.onHttpOK(ticketsBean);
                        }
                    }
                });
    }

    @Override
    public void onFollowMovie(int userId, String sessionId, int movieId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onFollow(userId, sessionId, movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(FollowBean followBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(followBean);
                        }
                    }
                });
    }

    @Override
    public void onCancelFollowMovie(int userId, String sessionId, int movieId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onCancelFollow(userId, sessionId, movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(FollowBean followBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(followBean);
                        }
                    }
                });
    }

    @Override
    public void onCinema_FollowMovie(int userId, String sessionId, int cinemaId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onCinema_Follow(userId, sessionId, cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(FollowBean followBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(followBean);
                        }
                    }
                });
    }

    @Override
    public void onCinema_CancelFollowMovie(int userId, String sessionId, int cinemaId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onCinema_CancelFollow(userId, sessionId, cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(FollowBean followBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(followBean);
                        }
                    }
                });
    }

    @Override
    public void onKeyWord(String keyword, int page, int count, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onKeyWork(keyword, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeyWorkBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(KeyWorkBean workBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(workBean);
                        }
                    }
                });
    }

    @Override
    public void onMovie_Comment(int userId, String sessionId, int movieId, String commentContent, double score, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onMovie_Comment(userId, sessionId, movieId, commentContent, score)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie_CommentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(Movie_CommentBean movie_commentBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(movie_commentBean);
                        }
                    }
                });
    }

    @Override
    public void onMy_Movie(int userId, String sessionId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onMy_Movie(userId, sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<My_MovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(My_MovieBean my_movieBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(my_movieBean);
                        }
                    }
                });
    }

    @Override
    public void onFine_User(int userId, String sessionId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onFine_User(userId, sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Find_UserBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(Find_UserBean find_userBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(find_userBean);
                        }
                    }
                });
    }

    @Override
    public void onSeen_Movie(int userId, String sessionId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onSeen_Movie(userId, sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Seen_MovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(Seen_MovieBean seen_movieBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(seen_movieBean);
                        }
                    }
                });
    }


    @Override
    public void onOrderId(int userId, String sessionId, String orderId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onOrderId(userId, sessionId, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderidBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(OrderidBean orderidBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(orderidBean);
                        }
                    }
                });
    }

    @Override
    public void onExChange(int userId, String sessionId, int recordId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onExChange(userId, sessionId, recordId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ExChanegBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(ExChanegBean exChanegBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(exChanegBean);
                        }
                    }
                });
    }

    @Override
    public void onRecord(int userId, String sessionId, String content, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onRecord(userId, sessionId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecordBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(RecordBean recordBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(recordBean);
                        }
                    }
                });
    }

    @Override
    public void onWX_Login(String code, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onWx_Login(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WX_LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(WX_LoginBean wx_loginBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(wx_loginBean);
                        }
                    }
                });
    }

    @Override
    public void onImage(int userId, String sessionId, MultipartBody.Part image, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onImage(userId, sessionId, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iBallBask != null) {
                            iBallBask.onHttpNO(e);
                        }
                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(imageBean);
                        }
                    }
                });
    }
}
