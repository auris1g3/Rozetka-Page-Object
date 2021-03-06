package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PutResponseModel {
    String status;
    PutResponseDataModel data;

    public PutResponseModel() {
    }

    public PutResponseModel(String status, PutResponseDataModel data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PutResponseModel that = (PutResponseModel) o;
        return Objects.equals(status, that.status) &&
                PutResponseDataModel.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data);
    }
}
