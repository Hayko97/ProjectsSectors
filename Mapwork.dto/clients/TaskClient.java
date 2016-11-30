package me.infomapwork.mapwork.dto.clients;

import me.infomapwork.mapwork.dto.MapworkRequestBuilder;
import me.infomapwork.mapwork.dto.enums.ContentType;
import me.infomapwork.mapwork.dto.enums.HttpMethod;
import me.infomapwork.mapwork.dto.models.Task;
import me.infomapwork.mapwork.dto.models.jsonObjectModels.task.TaskObjectModel;

/**
 * Created by H.Harutyunyan on 11/14/2016.
 */
public class TaskClient extends MapworkClient {

    private Task task = new Task();

    public TaskClient(String token){
        setToken(token);
    }
    public ResponseStatus addTask(TaskObjectModel model){

        MapworkRequestBuilder<TaskObjectModel> addTaskRequest = new MapworkRequestBuilder<TaskObjectModel>(model, HttpMethod.PUT,"/api/task", ContentType.JSON,getToken());
        execute(addTaskRequest);

        return getResponseResult(getResponse());

    }
}
