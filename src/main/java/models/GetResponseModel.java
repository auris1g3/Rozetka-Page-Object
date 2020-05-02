package models;

public class GetResponseModel {
    String status;
    GetResponseDataModel data;

    public GetResponseModel(String status, GetResponseDataModel data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" + "status='" + status + '\'' + ", data=" + data + '}';
    }
}
