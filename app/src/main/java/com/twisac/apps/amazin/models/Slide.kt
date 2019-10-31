package com.twisac.apps.amazin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Slide {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("image")
    @Expose
    var image: String? = null

}