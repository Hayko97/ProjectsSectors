package me.infomapwork.mapwork.dto.models.jsonObjectModels.user;

import me.infomapwork.mapwork.dto.models.Task;
import me.infomapwork.mapwork.dto.models.User;

/**
 * Created by H.Harutyunyan on 11/24/2016.
 */

public class UserObjectModel {
    private String captcha = "testing";
    private User user;

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
