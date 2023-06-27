package com.example.exam.Datatbase

class DBModel {

    var id = 0
    lateinit var title: String
    lateinit var description: String
    lateinit var url: String
    lateinit var urlToImage: String
    lateinit var publishedAt: String
    lateinit var content: String

    constructor(
        id: Int,
        title: String,
        description: String,
        url: String,
        urlToImage: String,
        publishedAt: String,
        content: String
    ) {
        this.id = id
        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage
        this.publishedAt = publishedAt
        this.content = content
    }

    constructor()


}