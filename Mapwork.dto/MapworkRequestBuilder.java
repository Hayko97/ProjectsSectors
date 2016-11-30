

package me.infomapwork.mapwork.dto;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.util.List;

import me.infomapwork.mapwork.dto.enums.ContentType;
import me.infomapwork.mapwork.dto.enums.HttpMethod;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by H.Harutyunyan on 11/2/2016.
 */
public class MapworkRequestBuilder<T> extends AsyncTask<Void, Void, Response> {

    public final static String host = "http://armleo-mapwork.cloudapp.net";
    private Gson gson = new Gson();
    private OkHttpClient client ;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private HttpMethod method;
    private ContentType contentType;
    private String uri;
    private String resource;
    private String token;

    private String jsonResult;
    private Response response;
    private RequestBody requestBody;
    private Request request;

    private T jsonSerializeModel;

    private String json;

    public MapworkRequestBuilder(HttpMethod method, String resource, String token) {

        uri = host + resource;
        this.resource = resource;
        this.method = method;
        this.token = token;

        client = new OkHttpClient.Builder()
                .build();
    }

    public MapworkRequestBuilder(T model, HttpMethod method, String resource, ContentType contentType, String token) {
        this(method, resource, token);

        this.contentType = contentType;
        this.jsonSerializeModel = model;
    }

    public String getJsonResult() {
        return jsonResult;

    }

    @Override
    protected void onPreExecute() {
        switch (method) {
            case GET:
                if(token != null && !token.isEmpty()) {
                    request = new Request.Builder()
                            .addHeader("Token", token)
                            .url(uri)
                            .build();
                }
                else{
                    request = new Request.Builder()
                            .url(uri)
                            .build();
                }


                break;
            case POST:
                if(token != null && !token.isEmpty()) {
                    addContent(contentType);
                    request = new Request.Builder()
                            .url(uri)
                            .post(requestBody)
                            .addHeader("Token", token)
                            .build();
                }
                else{
                    addContent(contentType);
                    request = new Request.Builder()
                            .url(uri)
                            .post(requestBody)
                            .build();
                }

                break;
            case PUT:
                if(token != null && !token.isEmpty()) {
                    addContent(contentType);
                    request = new Request.Builder()
                            .url(uri)
                            .put(requestBody)
                            .addHeader("Token", token)
                            .build();
                }
                else{
                    request = new Request.Builder()
                            .url(uri)
                            .put(requestBody)
                            .build();
                }

                break;
            case DELETE:
                addContent(contentType);
                request = new Request.Builder()
                        .url(uri)
                        .delete(requestBody)
                        .build();

                break;


        }
    }

    @Override
    protected Response doInBackground(Void... voids) {

        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        try {
            jsonResult = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = null;
        }


        return response;
    }


    private void addContent(ContentType contentType) {
        switch (contentType) {
            case JSON:

                json = gson.toJson(jsonSerializeModel);
                requestBody = RequestBody.create(JSON, json);

                break;
            case FORMDATA:

                break;

        }
    }

    public String getToken(){

        String token = response.header("Token");

        return token;

    }


}
