package com.example.exam.Datatbase

import android.content.ClipDescription
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.exam.Activity.MainActivity

class SqliteDB(context: Context?) : SQLiteOpenHelper(context, "MyData.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val sql = "CREATE TABLE news(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,description TEXT,url TEXT,urlToImage TEXT,publishedAt TEXT,content TEXT)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


    fun addData(title: String, description: String, url: String, urlToImage: String,publishedAt : String,content : String) {

        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("description", description)
            put("url", url)
            put("urlToImage", urlToImage)
            put("publishedAt", publishedAt)
            put("content", content)
        }
        db.insert("news", null, values)
    }

    fun showData(): ArrayList<DBModel> {

        var newsList = ArrayList<DBModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM news"
        var cursor: Cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()

        for (i in 0 until cursor.count) {
            var id = cursor.getInt(0)
            var title = cursor.getString(1)
            var description = cursor.getString(2)
            var url = cursor.getString(3)
            var urlToImage = cursor.getString(4)
            var publishedAt = cursor.getString(5)
            var content = cursor.getString(6)
            var std = DBModel(id, title, description, url, urlToImage, publishedAt, content)
            newsList.add(std)
            cursor.moveToNext()

        }
        return newsList
    }

    fun updateData(title: String, description: String, url: String, urlToImage: String,publishedAt : String,content : String,id:Int) {

        var db = writableDatabase
        var values = ContentValues().apply {
            put("title", title)
            put("description", description)
            put("url", url)
            put("urlToImage", urlToImage)
            put("publishedAt", publishedAt)
            put("content", content)
        }
        db.update("news", values, "id=$id", null)

    }

    fun deleteData(id: Int) {

        var db = writableDatabase
        db.delete("news", "id=$id", null)
    }

    fun deleteAllData(){
        var db = writableDatabase
         var sql = "DELETE  FROM news"
        db.execSQL(sql)
    }
}