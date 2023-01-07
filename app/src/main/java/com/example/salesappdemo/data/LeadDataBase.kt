package com.example.salesappdemo.data

class LeadDataBase {
    var name = ""
    var email = ""
    var mobile = ""
    var leadSource = ""
    var status = ""
    var createdAt = ""
    var updatedAt = ""
    var leadOwner = ""
    constructor(name:String,email:String,mobile:String,leadSource:String
    ,status:String,createdAt:String,updatedAt:String,leadOwner:String){
        this.name =name
        this.email = email
        this.mobile=mobile
        this.leadSource=leadSource
        this.status=status
        this.createdAt=createdAt
        this.updatedAt=updatedAt
        this.leadOwner=leadOwner

    }
    constructor()
}