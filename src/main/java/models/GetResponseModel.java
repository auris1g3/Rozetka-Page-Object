package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResponseModel {
    String status;
    GetResponseDataModel data;

    public GetResponseModel() {
    }

    public GetResponseModel(String status, GetResponseDataModel data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" + "status='" + status + '\'' + ", data=" + data + '}';
    }
}
