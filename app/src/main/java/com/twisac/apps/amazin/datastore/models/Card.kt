package com.twisac.apps.amazin.datastore.models

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table


@Table(name = "Card")
class Card : Model {

    @Column(name = "number")
    var number: String  =""

    @Column(name = "expiration")
    var expiration: String  =""

    @Column(name = "cvv")
    var cvv: String = ""

    @Column(name = "holder")
    var holder: String = ""

    @Column(name = "date")
    var date: String = ""

    @Column(name = "provider")
    var provider: String = ""

    constructor() : super()

    constructor(number: String, expiration: String, cvv: String, holder: String, provider: String,date: String) : super() {
        this.number = number
        this.expiration = expiration
        this.cvv = cvv
        this.holder = holder
        this.provider = provider
        this.date = date
    }


}
