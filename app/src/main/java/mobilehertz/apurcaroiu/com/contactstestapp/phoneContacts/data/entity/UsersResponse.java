package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by apurcaroiu on 9/20/2017.
 */

public class UsersResponse {
    @SerializedName("results")
    @Expose
    private List<UserEntity> results = null;
    @SerializedName("info")
    @Expose
    private Info info;

    public List<UserEntity> getResults() {
        return results;
    }

    public void setResults(List<UserEntity> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
