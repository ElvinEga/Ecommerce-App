package com.twisac.apps.amazin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Product {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("image")
    @Expose
    var image: String? = null
    @SerializedName("size")
    @Expose
    var size: String? = null
    @SerializedName("price")
    @Expose
    var price: Double? = null
    @SerializedName("whishlist")
    @Expose
    var whishlist: Boolean? = null

}