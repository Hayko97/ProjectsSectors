package me.infomapwork.mapwork.dto.models.jsonObjectModels.user;


public class AuthStatusObjectModel {
    private boolean success;

    private boolean authorized ;

    private String username;


    public boolean isSuccess() {
        return success;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public String getUsername() {
        return username;
    }
}
