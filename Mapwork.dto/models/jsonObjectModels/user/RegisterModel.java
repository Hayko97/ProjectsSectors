package me.infomapwork.mapwork.dto.models.jsonObjectModels.user;

import com.google.gson.annotations.SerializedName;

import me.infomapwork.mapwork.dto.models.User;

/**
 * Created by H.Harutyunyan on 11/24/2016.
 */

public class RegisterModel {
    @SerializedName("captcha")
    private String captcha = "testing";

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
    @SerializedName("user")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
}
