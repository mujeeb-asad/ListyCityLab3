package com.example.listycitylab3;

import java.io.Serializable;

public class City implements Serializable {
    private String City;
    private String Province;

    public String getProvinceName() {
        return Province;
    }

    public String getCityName() {
        return City;
    }

    public void setProvinceName(String provinceName) {
        Province = provinceName;
    }

    public void setCityName(String cityName) {
        City = cityName;
    }

    public City(String cityName , String provinceName) {
        Province = provinceName;
        City = cityName;
    }

//    public String toString() {
//        return City + "  " + Province;
//    }
}
