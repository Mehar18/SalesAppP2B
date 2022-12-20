package com.example.salesappdemo.data

class CouponDataBase {
    var type = ""
    var code = ""
    var amount = 0
    var startDate = ""
    var expiryBY = ""
    var employee = ""
    var course = ""
    constructor(type:String,code:String,amount:Int,startDate:String
    ,expiryBy:String,employee:String,course:String){
        this.type =type
        this.code = code
        this.amount=amount
        this.startDate=startDate
        this.expiryBY=expiryBy
        this.employee=employee
        this.course=course

    }
    constructor()
}