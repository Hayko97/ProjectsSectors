package me.infomapwork.mapwork.dto.clients;


import com.google.gson.Gson;
import me.infomapwork.mapwork.dto.MapworkRequestBuilder;
import okhttp3.Response;


public  class MapworkClient {

    private Gson gson = new Gson();
    private String token;
    private Response response;

    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public Response getResponse() {
        return response;
    }

    public enum ResponseStatus {
        Success,
        IternalError,
        AuthorizationError,
        IncorrectData,
        NoServerConnection
    }

    public <T> void  execute(MapworkRequestBuilder<T> request){

        try {
            response = request.execute().get();
        } catch (Exception e) {
            response = null;
        }

    }

    public ResponseStatus getResponseResult(Response response) {

        int code = response.code();
        ResponseStatus responseStatus = null;

        if(response == null)
            return ResponseStatus.NoServerConnection;
        if (code == 200)
            responseStatus = ResponseStatus.Success;
        if (code >= 400 && code < 500)
            responseStatus = ResponseStatus.IncorrectData;
        if (code > 500)
            responseStatus = ResponseStatus.IternalError;

        return responseStatus;

    }

    public <T> T getJsonModel(MapworkRequestBuilder<T> request, Class modelClass) {

        String resultString = request.getJsonResult();

        T jsonModel = (T) gson.fromJson(resultString, modelClass);

        return jsonModel;
    }

    public <T> String getToken(MapworkRequestBuilder<T> request) {

        try{
            String token = request.getToken();

            return token ;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }


    }



}
