package com.example.recipe_capstone.dataFromApi;

import android.os.Parcel;
import android.os.Parcelable;

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

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "title",
            "version",
            "href",
            "results"
    })
    public class RecipeResponse implements Serializable/*, Parcelable*/
    {

        @JsonProperty("title")
        private String title;
        @JsonProperty("version")
        private Double version;
        @JsonProperty("href")
        private String href;
        @JsonProperty("results")
        private List<Result> results = null;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();
        public final static Parcelable.Creator<RecipeResponse
                > CREATOR = new Creator<RecipeResponse
                >() {


            @SuppressWarnings({
                    "unchecked"
            })
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

        }
                ;
        private final static long serialVersionUID = -5745271282243258296L;

        protected RecipeResponse
                (Parcel in) {
            this.title = ((String) in.readValue((String.class.getClassLoader())));
            this.version = ((Double) in.readValue((Double.class.getClassLoader())));
            this.href = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.results, (Result.class.getClassLoader()));
            this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
        }

        /**
         * No args constructor for use in serialization
         *
         */
        public RecipeResponse
        () {
        }

        /**
         *
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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(title);
            dest.writeValue(version);
            dest.writeValue(href);
            dest.writeList(results);
            dest.writeValue(additionalProperties);
        }

        public int describeContents() {
            return 0;
        }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "title",
            "href",
            "ingredients",
            "thumbnail"
    })
    public class Result implements Serializable
    {

        @JsonProperty("title")
        private String title;
        @JsonProperty("href")
        private String href;
        @JsonProperty("ingredients")
        private String ingredients;
        @JsonProperty("thumbnail")
        private String thumbnail;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();
        public final /*static*/ Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Result createFromParcel(Parcel in) {
                return new Result(in);
            }

            public Result[] newArray(int size) {
                return (new Result[size]);
            }

        }
                ;
        private final static long serialVersionUID = -4905860137045991008L;

        protected Result(Parcel in) {
            this.title = ((String) in.readValue((String.class.getClassLoader())));
            this.href = ((String) in.readValue((String.class.getClassLoader())));
            this.ingredients = ((String) in.readValue((String.class.getClassLoader())));
            this.thumbnail = ((String) in.readValue((String.class.getClassLoader())));
            this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
        }

        /**
         * No args constructor for use in serialization
         *
         */
        public Result() {
        }

        /**
         *
         * @param ingredients
         * @param title
         * @param thumbnail
         * @param href
         */
        public Result(String title, String href, String ingredients, String thumbnail) {
            super();
            this.title = title;
            this.href = href;
            this.ingredients = ingredients;
            this.thumbnail = thumbnail;
        }

        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
        }

        @JsonProperty("href")
        public String getHref() {
            return href;
        }

        @JsonProperty("href")
        public void setHref(String href) {
            this.href = href;
        }

        @JsonProperty("ingredients")
        public String getIngredients() {
            return ingredients;
        }

        @JsonProperty("ingredients")
        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        @JsonProperty("thumbnail")
        public String getThumbnail() {
            return thumbnail;
        }

        @JsonProperty("thumbnail")
        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(title);
            dest.writeValue(href);
            dest.writeValue(ingredients);
            dest.writeValue(thumbnail);
            dest.writeValue(additionalProperties);
        }

        public int describeContents() {
            return 0;
        }

    }
    }