package com.bw.movie.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Movie;

import com.bw.movie.APP;
import com.bw.movie.api.Constant;
import com.bw.movie.bean.ByDateBean;
import com.bw.movie.bean.Cinema_ListBean;
import com.bw.movie.bean.DateListBean;
import com.bw.movie.bean.DetailBean;
import com.bw.movie.bean.FollowBean;
import com.bw.movie.bean.Follow_CinemaBean;
import com.bw.movie.bean.Follow_MovieBean;
import com.bw.movie.bean.ImageBean;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.bean.Movie_ListBean;
import com.bw.movie.bean.Schedule_ListBean;
import com.bw.movie.bean.TicketBean;
import com.bw.movie.bean.fragment_bean.BannerBean;
import com.bw.movie.bean.fragment_bean.Cinema_byBean;
import com.bw.movie.bean.fragment_bean.ComingBean;
import com.bw.movie.bean.fragment_bean.CommentBean;
import com.bw.movie.bean.fragment_bean.HotMovieBean;
import com.bw.movie.bean.fragment_bean.NearbyBean;
import com.bw.movie.bean.fragment_bean.NewVersionBean;
import com.bw.movie.bean.fragment_bean.RecommendBean;
import com.bw.movie.bean.fragment_bean.RegionBean;
import com.bw.movie.bean.fragment_bean.ReleaseBean;
import com.bw.movie.mode.Fragment_Mode;
import com.example.my_utils_library.app.App;
import com.example.my_utils_library.base.BasePresenter;
import com.example.my_utils_library.contract.Contract;
import com.example.my_utils_library.utils.Logger;

import okhttp3.MultipartBody;

/**
 * @name APP
 * @class name：com.bw.movie
 * @class describe
 * @anthor 24673
 * @time 2019/11/11 19:32
 * @change
 * @chang time
 * @class describe
 */
public class Fragment_Presenter extends BasePresenter<Contract.IView> implements Contract.FPresenter {

    private Fragment_Mode mode;
    private static final String TAG = "MyPresenter";
    SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
    int movieId = sp.getInt(Constant.MOVIEID, 0);

    @Override
    protected void initModel() {
        mode = new Fragment_Mode();
    }

    @Override
    public void onNetVersion(int userId, String sessionId, String ap) {
        mode.onNetVersion(userId, sessionId, ap, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    NewVersionBean bean = (NewVersionBean) obj;
                    if (bean != null && bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("请求失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                if (isViewAttached()) {
                    Logger.d(TAG, e.getMessage() + "");
                }
            }
        });
    }

    @Override
    public void onBannerImage() {
        mode.onBannerImage(new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    BannerBean bean = (BannerBean) obj;
                    if (bean != null && bean.status.equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("请求失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                if (isViewAttached()) {
                    Logger.d(TAG, e.getMessage() + "");
                }
            }
        });
    }

    @Override
    public void onReleaseResult(int userId, String sessionId, int page, int count) {
        mode.onReleaseResult(userId, sessionId, page, count, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    ReleaseBean bean = (ReleaseBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("登录失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onComingResult(int userId, String session, int page, int count) {
        mode.onComingResult(userId, session, page, count, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    ComingBean bean = (ComingBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("登录失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onHotMovieResult(int userId, String session, int page, int count) {
        mode.onHotMovieResult(userId, session, page, count, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    HotMovieBean bean = (HotMovieBean) obj;
                    if (bean.status.equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("登录失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onRecommend(int userId, String sessionId, int page, int count) {
        mode.onRecommend(userId, sessionId, page, count, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    RecommendBean bean = (RecommendBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("登录失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onNearby(int userId, String sessionId, String longitude, String latitude, int page, int count) {
        mode.onNearby(userId, sessionId, longitude, latitude, page, count, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    NearbyBean bean = (NearbyBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("登录失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onRegion() {
        mode.onRegion(new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    RegionBean bean = (RegionBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("登录失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onCinema_by(int regionId) {
        mode.onCinema_by(regionId, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    Cinema_byBean bean = (Cinema_byBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("登录失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onMovieId(int movieId, int page, int count) {
        mode.onMovieId(movieId, page, count, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    MovieBean bean = (MovieBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("登录失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onComment(int cinemaId, int page, int count) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onComment(userId, sessionId, cinemaId, page, count, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    CommentBean bean = (CommentBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("查询失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {

            }
        });
    }

    @Override
    public void onDateList() {
        mode.onDateList(new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    DateListBean bean = (DateListBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("查询失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onByDate(String date, int page, int count) {
        mode.onByDate(movieId, date, page, count, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    ByDateBean bean = (ByDateBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("查询失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onSchedule_List(int cinemaId, int page, int count) {
        mode.onSchedule_List(cinemaId, page, count, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    Schedule_ListBean bean = (Schedule_ListBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("查询失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }


    @Override
    public void onFollow_Movie(int page, int count) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onFollow_Movie(userId, sessionId, page, count, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    Follow_MovieBean follow_movieBean = (Follow_MovieBean) obj;
                    if (follow_movieBean.getStatus().equals("0000")) {
                        getView().onSuccess(obj);
                    } else {
                        getView().onError(new Exception("查询失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onFollow_Cinema(int page, int count) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onFollow_Cinema(userId, sessionId, page, count, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    Follow_CinemaBean follow_cinemaBean = (Follow_CinemaBean) obj;
                    if (follow_cinemaBean.getStatus().equals("0000")) {
                        getView().onSuccess(obj);
                    } else {
                        getView().onError(new Exception("查询失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onTicket(int page, int count, int status) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onTicket(userId, sessionId, page, count, status, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    TicketBean ticketBean = (TicketBean) obj;
                    if (ticketBean.getStatus().equals("0000")) {
                        getView().onSuccess(obj);
                    } else {
                        getView().onError(new Exception("查询失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onComment_List(int page, int count) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onMovie_List(userId, sessionId, page, count, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    Movie_ListBean ticketBean = (Movie_ListBean) obj;
                    if (ticketBean.getStatus().equals("0000")) {
                        getView().onSuccess(obj);
                    } else {
                        getView().onError(new Exception("查询失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onCinema_List(String longitude, String latitude, int page, int count) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onCinema_List(userId, sessionId, longitude, latitude, page, count, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    Cinema_ListBean ticketBean = (Cinema_ListBean) obj;
                    if (ticketBean.getStatus().equals("0000")) {
                        getView().onSuccess(obj);
                    } else {
                        getView().onError(new Exception("查询失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onCancelFollowMovie(int movieId) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onCancelFollowMovie(userId, sessionId, movieId, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    FollowBean ticketsBean = (FollowBean) obj;
                    if (ticketsBean.getStatus().equals("0000")) {
                        getView().onSuccess(obj);
                    } else {
                        getView().onError(new Exception("查询失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

    @Override
    public void onCinema_CancelFollowMovie(int cinemaId) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onCinema_CancelFollowMovie(userId, sessionId, cinemaId, new Contract.FModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    FollowBean ticketsBean = (FollowBean) obj;
                    if (ticketsBean.getStatus().equals("0000")) {
                        getView().onSuccess(obj);
                    } else {
                        getView().onError(new Exception("查询失败"));
                    }
                }
            }

            @Override
            public void onHttpNO(Throwable e) {
                Logger.d(TAG, e.getMessage() + "");
            }
        });
    }

}
