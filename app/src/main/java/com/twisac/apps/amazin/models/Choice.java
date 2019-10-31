package com.twisac.apps.amazin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Choice {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("drawable")
    @Expose
    private String drawable;
    @SerializedName("isChecked")
    @Expose
    private Boolean isChecked ;

    public Choice(Integer id, String name, String drawable, Boolean isChecked) {
        this.id = id;
        this.name = name;
        this.drawable = drawable;
        this.isChecked = isChecked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
