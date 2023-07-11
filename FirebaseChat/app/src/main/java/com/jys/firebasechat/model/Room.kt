package com.jys.firebasechat.model

class Room {
    var id: String = ""
    var title: String = ""
    var users: String = ""

    constructor()

    constructor(title: String, creatorName: String){
        this.title = title
        users = creatorName
    }
}