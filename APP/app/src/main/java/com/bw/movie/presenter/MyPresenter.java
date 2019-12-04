package com.bw.movie.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.APP;
import com.bw.movie.api.Constant;
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
import com.bw.movie.mode.Mode;
import com.bw.movie.utils.EmailUtils;
import com.example.my_utils_library.base.BasePresenter;
import com.example.my_utils_library.contract.Contract;
import com.example.my_utils_library.utils.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
public class MyPresenter extends BasePresenter<Contract.IView> implements Contract.IPresenter {

    private Mode mode;
    private static final String TAG = "MyPresenter";
    private int movieId;

    @Override
    protected void initModel() {
        mode = new Mode();
    }

    @Override
    public void onCode(String email) {
        mode.onCode(email, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    CodeBean codeBean = (CodeBean) obj;
                    if (codeBean.getStatus().equals("0000")) {
                        getView().onSuccess(codeBean);
                    } else {
                        getView().onError(new Exception("验证码发送失败"));
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
    public void onRegister(String nickName, String pwd, String email, String code) {
        mode.onRegister(nickName, pwd, email, code, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    RegisterBean bean = (RegisterBean) obj;
                    if (bean.getStatus().equals("0000")) {
                        getView().onSuccess(bean);
                    } else {
                        getView().onError(new Exception("注册失败"));
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
    public void onLogin(String email, String pwd) {
        mode.onLogin(email, pwd, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    LoginBean bean = (LoginBean) obj;
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
    public void onNameInfo(int userId, String sessionId, int cinemaId) {
        mode.onNameInfo(userId, sessionId, cinemaId, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    CinemainfoBean bean = (CinemainfoBean) obj;
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
    public void onDetail(int movieId) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onDetail(userId, sessionId, movieId, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    DetailBean bean = (DetailBean) obj;
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
    public void onSeatInfo(int hallId) {
        mode.onSeatInfo(hallId, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    SeatInfoBean bean = (SeatInfoBean) obj;
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
    public void onSchedule(int cinemaId) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        movieId = sp.getInt(Constant.MOVIEID, 0);
        mode.onSchedule(movieId, cinemaId, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    ScheduleBean bean = (ScheduleBean) obj;
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
    public void onTickets(int scheduleId, String sea) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        String uId = Integer.toString(userId);
        String str = uId + scheduleId + "movie";
        String sign = EmailUtils.MD5(str);
        mode.onTickets(userId, sessionId, scheduleId, sea, sign, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    TicketsBean ticketsBean = (TicketsBean) obj;
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
    public void onFollowMovie(int movieId) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onFollowMovie(userId, sessionId, movieId, new Contract.IModer.IBallBask() {
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
    public void onCancelFollowMovie(int movieId) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onCancelFollowMovie(userId, sessionId, movieId, new Contract.IModer.IBallBask() {
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
    public void onCinema_FollowMovie(int cinemaId) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onCinema_FollowMovie(userId, sessionId, movieId, new Contract.IModer.IBallBask() {
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
        mode.onCinema_CancelFollowMovie(userId, sessionId, movieId, new Contract.IModer.IBallBask() {
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
    public void onKeyWord(String keyword, int page, int count) {
        mode.onKeyWord(keyword, page, count, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    KeyWorkBean keyWorkBean = (KeyWorkBean) obj;
                    if (keyWorkBean.getStatus().equals("0000")) {
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
    public void onMovie_Comment(int movieId, String commentContent, double score) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onMovie_Comment(userId, sessionId, movieId, commentContent, score, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    Movie_CommentBean movie_commentBean = (Movie_CommentBean) obj;
                    if (movie_commentBean.getStatus().equals("0000")) {
                        getView().onSuccess(obj);
                    } else {
                        getView().onError(new Exception("查询失败"));
                        Toast.makeText(APP.context, "请先登录", Toast.LENGTH_SHORT).show();
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
    public void onMy_Movie() {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onMy_Movie(userId, sessionId, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    My_MovieBean my_movieBean = (My_MovieBean) obj;
                    if (my_movieBean.getStatus().equals("0000")) {
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
    public void onFine_User() {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onFine_User(userId, sessionId, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    Find_UserBean find_userBean = (Find_UserBean) obj;
                    if (find_userBean.getStatus().equals("0000")) {
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
    public void onSeen_Movie() {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onSeen_Movie(userId, sessionId, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    Seen_MovieBean seen_movieBean = (Seen_MovieBean) obj;
                    if (seen_movieBean.getStatus().equals("0000")) {
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
    public void onOrderId(String orderId) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onOrderId(userId, sessionId, orderId, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    OrderidBean orderidBean = (OrderidBean) obj;
                    if (orderidBean.getStatus().equals("0000")) {
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
    public void onExChange(int recordId) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onExChange(userId, sessionId, recordId, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    ExChanegBean exChanegBean = (ExChanegBean) obj;
                    if (exChanegBean.getStatus().equals("0000")) {
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
    public void onRecord(String content) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onRecord(userId, sessionId, content, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    RecordBean recordBean = (RecordBean) obj;
                    if (recordBean.getStatus().equals("0000")) {
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
    public void onWX_Login(String code) {
        mode.onWX_Login(code, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    WX_LoginBean wx_loginBean = (WX_LoginBean) obj;
                    if (wx_loginBean.getStatus().equals("0000")) {
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
    public void onImage(MultipartBody.Part image) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onImage(userId, sessionId, image, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    ImageBean bean = (ImageBean) obj;
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
    public void onWXPay(int payType, String orderId) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onWXPay(userId, sessionId, payType, orderId, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    WXPayBean bean = (WXPayBean) obj;
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
    public void onZFBPay(int payType, String orderId) {
        SharedPreferences sp = APP.context.getSharedPreferences(Constant.SP, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");
        mode.onZFBPay(userId, sessionId, payType, orderId, new Contract.IModer.IBallBask() {
            @Override
            public void onHttpOK(Object obj) {
                if (isViewAttached()) {
                    ZFBParBean zfbParBean = (ZFBParBean) obj;
                    if (zfbParBean.getStatus().equals("0000")) {
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
