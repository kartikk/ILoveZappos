package com.kartikk.zappos.ilovezappos.models;

/**
 * Created by Kartikk on 1/31/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZapposModel implements Parcelable {


    @SerializedName("originalTerm")
    @Expose
    private Object originalTerm;
    @SerializedName("currentResultCount")
    @Expose
    private String currentResultCount;
    @SerializedName("totalResultCount")
    @Expose
    private String totalResultCount;
    @SerializedName("term")
    @Expose
    private String term;
    @SerializedName("results")
    @Expose
    private List<ZapposResult> results = null;
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    public final static Parcelable.Creator<ZapposModel> CREATOR = new Creator<ZapposModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ZapposModel createFromParcel(Parcel in) {
            ZapposModel instance = new ZapposModel();
            instance.originalTerm = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.currentResultCount = ((String) in.readValue((String.class.getClassLoader())));
            instance.totalResultCount = ((String) in.readValue((String.class.getClassLoader())));
            instance.term = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.results, (ZapposResult.class.getClassLoader()));
            instance.statusCode = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ZapposModel[] newArray(int size) {
            return (new ZapposModel[size]);
        }

    };

    public Object getOriginalTerm() {
        return originalTerm;
    }

    public void setOriginalTerm(Object originalTerm) {
        this.originalTerm = originalTerm;
    }

    public String getCurrentResultCount() {
        return currentResultCount;
    }

    public void setCurrentResultCount(String currentResultCount) {
        this.currentResultCount = currentResultCount;
    }

    public String getTotalResultCount() {
        return totalResultCount;
    }

    public void setTotalResultCount(String totalResultCount) {
        this.totalResultCount = totalResultCount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<ZapposResult> getResults() {
        return results;
    }

    public void setResults(List<ZapposResult> results) {
        this.results = results;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(originalTerm);
        dest.writeValue(currentResultCount);
        dest.writeValue(totalResultCount);
        dest.writeValue(term);
        dest.writeList(results);
        dest.writeValue(statusCode);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "ZapposModel{" +
                "originalTerm=" + originalTerm +
                ", currentResultCount='" + currentResultCount + '\'' +
                ", totalResultCount='" + totalResultCount + '\'' +
                ", term='" + term + '\'' +
                ", results=" + results +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}


