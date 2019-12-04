package com.bw.movie.mode;

import com.bw.movie.api.HttpApi;
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
public class Fragment_Mode implements Contract.FModer {

    @Override
    public void onNetVersion(int userId, String sessionId, String ap, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onNetVersion(userId, sessionId, ap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewVersionBean>() {
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
                    public void onNext(NewVersionBean newVersionBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(newVersionBean);
                        }
                    }
                });
    }

    @Override
    public void onBannerImage(final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onBannerImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
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
                    public void onNext(BannerBean bannerBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(bannerBean);
                        }
                    }
                });
    }

    @Override
    public void onReleaseResult(int userId, String session, int page, int count, final Contract.IModer.IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onRelease(userId, session, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReleaseBean>() {
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
                    public void onNext(ReleaseBean releaseBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(releaseBean);
                        }
                    }
                });
    }

    @Override
    public void onComingResult(int userId, String session, int page, int count, final Contract.IModer.IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onComing(userId, session, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComingBean>() {
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
                    public void onNext(ComingBean comingBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(comingBean);
                        }
                    }
                });
    }

    @Override
    public void onHotMovieResult(int userId, String session, int page, int count, final Contract.IModer.IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onFindHot(userId, session, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotMovieBean>() {
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
                    public void onNext(HotMovieBean hotMovieBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(hotMovieBean);
                        }
                    }
                });
    }

    @Override
    public void onRecommend(int userId, String sessionId, int page, int count, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onRecommend(userId, sessionId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendBean>() {
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
                    public void onNext(RecommendBean recommendBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(recommendBean);
                        }
                    }
                });
    }

    @Override
    public void onNearby(int userId, String sessionId, String longitude, String String, int page, int count, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onNearby(userId, sessionId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NearbyBean>() {
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
                    public void onNext(NearbyBean nearbyBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(nearbyBean);
                        }
                    }
                });
    }

    @Override
    public void onRegion(final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onRegion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegionBean>() {
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
                    public void onNext(RegionBean regionBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(regionBean);
                        }
                    }
                });
    }

    @Override
    public void onCinema_by(int regionId, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onCinemaBy(regionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Cinema_byBean>() {
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
                    public void onNext(Cinema_byBean cinema_byBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(cinema_byBean);
                        }
                    }
                });
    }

    @Override
    public void onMovieId(int movieId, int page, int count, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onMovie(movieId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieBean>() {
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
                    public void onNext(MovieBean movieBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(movieBean);
                        }
                    }
                });
    }

    @Override
    public void onComment(int userId, String sessionId, int cinemaId, int page, int count, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onComment(userId, sessionId, cinemaId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentBean>() {
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
                    public void onNext(CommentBean commentBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(commentBean);
                        }
                    }
                });
    }

    @Override
    public void onDateList(final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onDateList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DateListBean>() {
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
                    public void onNext(DateListBean detailBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(detailBean);
                        }
                    }
                });
    }

    @Override
    public void onByDate(int movieId, String date, int page, int count, final Contract.FModer.IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onByDateBean(movieId, date, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ByDateBean>() {
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
                    public void onNext(ByDateBean byDateBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(byDateBean);
                        }
                    }
                });
    }

    @Override
    public void onSchedule_List(int cinemaId, int page, int count, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onSchedule_List(cinemaId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Schedule_ListBean>() {
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
                    public void onNext(Schedule_ListBean schedule_listBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(schedule_listBean);
                        }
                    }
                });
    }


    @Override
    public void onFollow_Movie(int userId, String sessionId, int page, int count, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onFollow_Movie(userId, sessionId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Follow_MovieBean>() {
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
                    public void onNext(Follow_MovieBean follow_movieBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(follow_movieBean);
                        }
                    }
                });
    }

    @Override
    public void onFollow_Cinema(int userId, String sessionId, int page, int count, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onFollow_Cinema(userId, sessionId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Follow_CinemaBean>() {
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
                    public void onNext(Follow_CinemaBean follow_cinemaBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(follow_cinemaBean);
                        }
                    }
                });
    }

    @Override
    public void onTicket(int userId, String sessionId, int page, int count, int status, final Contract.IModer.IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onTicket(userId, sessionId, page, count, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TicketBean>() {
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
                    public void onNext(TicketBean ticketBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(ticketBean);
                        }
                    }
                });
    }

    @Override
    public void onMovie_List(int userId, String sessionId, int page, int count, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onMovie(userId, sessionId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie_ListBean>() {
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
                    public void onNext(Movie_ListBean movie_listBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(movie_listBean);
                        }
                    }
                });
    }

    @Override
    public void onCinema_List(int userId, String sessionId, String longitude, String latitude, int page, int count, final IBallBask iBallBask) {
        HttpUtils.getHttpUtils().getRetrofit().create(HttpApi.class)
                .onCinema(userId, sessionId, longitude, latitude, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Cinema_ListBean>() {
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
                    public void onNext(Cinema_ListBean cinema_listBean) {
                        if (iBallBask != null) {
                            iBallBask.onHttpOK(cinema_listBean);
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

}
