package Models;

import java.util.Objects;

public class PostResponseModel {
    String status;
    PostResponseDataModel data;

    public PostResponseModel(String status, PostResponseDataModel data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostResponseModel that = (PostResponseModel) o;
        return Objects.equals(status, that.status) &&
                PostResponseDataModel.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data);
    }
}
