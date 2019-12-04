package com.bw.movie.bean.eventbus_bean;

/**
 * @name APP
 * @class name：com.bw.movie.bean.eventbus_bean
 * @class describe
 * @anthor 24673
 * @time 2019/11/19 7:52
 * @change
 * @chang time
 * @class describe
 */
public class Detali_EventBus {
    private String name;
    private String phone;
    private String vehicleRoute;
    private int cinemaId;

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicleRoute() {
        return vehicleRoute;
    }

    public void setVehicleRoute(String vehicleRoute) {
        this.vehicleRoute = vehicleRoute;
    }
}
