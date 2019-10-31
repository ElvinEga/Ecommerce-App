package com.twisac.apps.amazin.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Home {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("icon")
    @Expose
    var icon: String? = null

}