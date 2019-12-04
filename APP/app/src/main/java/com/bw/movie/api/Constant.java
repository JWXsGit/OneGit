package com.bw.movie.api;

/**
 * @name APP
 * @class name：com.bw.movie.api
 * @class describe
 * @anthor 24673
 * @time 2019/11/12 9:52
 * @change
 * @chang time
 * @class describe
 */
public interface Constant {
    //邮箱验证
    String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    //SP
    String SP = "SP";
    String SP_EMAIL = "SP_EMAIL";
    String SP_PWD = "SP_PWD";
    //Intent
    String USER_ID = "USER_ID";
    String SESSION_ID = "SESSION_ID";
    String CINEMAID = "CINEMAID";
    String MOVIEID = "MOVIEID";
    //微信头像
    String WX_PIC = "IMAGE_PIC";
    //微信昵称
    String WX_NAME = "WX_NAME";
    //性别
    String WX_SEX = "WX_SEX";
    //出生日期
    String WX_BIRTHDAY = "WX_BIRTHDAY";
}
