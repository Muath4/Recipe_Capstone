package com.example.recipe_capstone.dataFromApi;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "version",
        "href",
        "results"
})
public class RecipeResponse implements Serializable/*, Parcelable*/ {

    public final static Parcelable.Creator<RecipeResponse
            > CREATOR = new Creator<RecipeResponse
            >() {


        public RecipeResponse
        createFromParcel(Parcel in) {
            return new RecipeResponse
                    (in);
        }

        public RecipeResponse
                [] newArray(int size) {
            return (new RecipeResponse
                    [size]);
        }

    };
    private final static long serialVersionUID = -5745271282243258296L;
    @JsonProperty("title")
    private String title;
    @JsonProperty("version")
    private Double version;
    @JsonProperty("href")
    private String href;
    @JsonProperty("results")
    private List<Result> results = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    private RecipeResponse
            (Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.version = ((Double) in.readValue((Double.class.getClassLoader())));
        this.href = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.results, (Result.class.getClassLoader()));
        this.additionalProperties = ((Map<String, Object>) in.readValue((Map.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public RecipeResponse
    () {
    }

    /**
     * @param title
     * @param results
     * @param href
     * @param version
     */
    public RecipeResponse
    (String title, Double version, String href, List<Result> results) {
        super();
        this.title = title;
        this.version = version;
        this.href = href;
        this.results = results;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("version")
    public Double getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Double version) {
        this.version = version;
    }

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("results")
    public List<Result> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<Result> results) {
        this.results = results;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest) {
        dest.writeValue(title);
        dest.writeValue(version);
        dest.writeValue(href);
        dest.writeList(results);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }


}
