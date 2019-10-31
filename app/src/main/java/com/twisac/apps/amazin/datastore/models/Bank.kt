package com.twisac.apps.amazin.datastore.models

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table


@Table(name = "Bank")
class Bank : Model {

    @Column(name = "bank")
    var bank: String = ""

    @Column(name = "number")
    var number: String = ""

    @Column(name = "name")
    var name: String = ""

    @Column(name = "date")
    var date: String = ""


    constructor() : super()
    constructor(bank: String, number: String, name: String, date: String) : super() {
        this.bank = bank
        this.number = number
        this.name = name
        this.date = date
    }


}