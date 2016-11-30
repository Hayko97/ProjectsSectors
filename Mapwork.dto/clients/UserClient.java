package me.infomapwork.mapwork.dto.clients;


import me.infomapwork.mapwork.dto.MapworkRequestBuilder;
import me.infomapwork.mapwork.dto.models.jsonObjectModels.user.AuthStatusObjectModel;
import me.infomapwork.mapwork.dto.models.jsonObjectModels.user.LoginModel;
import me.infomapwork.mapwork.dto.models.jsonObjectModels.user.RegisterModel;
import me.infomapwork.mapwork.dto.models.User;
import me.infomapwork.mapwork.dto.enums.ContentType;
import me.infomapwork.mapwork.dto.enums.HttpMethod;
import me.infomapwork.mapwork.dto.models.jsonObjectModels.user.UserObjectModel;
import okhttp3.Response;


public class UserClient extends MapworkClient {


    private User user = new User();
    private String token;
    private String username;

    public String getUsername() {
        return username;
    }


    public UserClient(String token) {
        token = token;
    }

    public String getToken() {
        return token;
    }

    public ResponseStatus Login(String username, String password) {

        LoginModel model = new LoginModel();
        model.setUsername(username);
        model.setPassword(password);

        MapworkRequestBuilder<LoginModel> loginRequest = new MapworkRequestBuilder(
                model,
                HttpMethod.POST,
                "/auth/login",
                ContentType.JSON,
                null
        );

        execute(loginRequest);

        token = getToken(loginRequest);
        return getResponseResult(getResponse());

    }

    public ResponseStatus Register(RegisterModel registerModel) {

        MapworkRequestBuilder<RegisterModel> registerRequest = new MapworkRequestBuilder(
                registerModel,
                HttpMethod.PUT,
                "/api/user",
                ContentType.JSON,
                token
        );
        execute(registerRequest);


        return getResponseResult(getResponse());
    }

    public UserObjectModel getUser(String username) {

        MapworkRequestBuilder getUserRequest = new MapworkRequestBuilder(HttpMethod.GET, "/api/user/" + username + "", token);
        execute(getUserRequest);
        Response response = getResponse();

        if (response == null)
            return null;

        UserObjectModel m = (UserObjectModel) getJsonModel(getUserRequest,UserObjectModel.class);

        return m;
    }

    public ResponseStatus logout() {
        MapworkRequestBuilder logout = new MapworkRequestBuilder(HttpMethod.POST, "/auth/logout", token);

        execute(logout);


        return getResponseResult(getResponse());
    }


    public ResponseStatus userStatus() {
        MapworkRequestBuilder userStatus = new MapworkRequestBuilder(HttpMethod.GET, "/auth/status", token);
        execute(userStatus);

        ResponseStatus responseStatus = getResponseResult(getResponse());


        AuthStatusObjectModel authStatus = (AuthStatusObjectModel) getJsonModel(userStatus, AuthStatusObjectModel.class);


        if (authStatus.isSuccess() && authStatus.isAuthorized()) {
            username = authStatus.getUsername();
            responseStatus = ResponseStatus.Success;
        } else
            responseStatus = ResponseStatus.AuthorizationError;

        return responseStatus;
    }


}
