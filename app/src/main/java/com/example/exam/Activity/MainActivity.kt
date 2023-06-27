package com.example.exam.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam.Adapter.NewsAdapter
import com.example.exam.Api.ApiClient
import com.example.exam.Api.ApiInterface
import com.example.exam.Api.ArticlesItem
import com.example.exam.Api.NewsModel
import com.example.exam.Datatbase.SqliteDB
import com.example.exam.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var binding: ActivityMainBinding
        private val TAG = "MainActivity"
        lateinit var adapter: NewsAdapter
        lateinit var db: SqliteDB

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = SqliteDB(this)
        loadNews()

        var list = db.showData()

        adapter = NewsAdapter(this)

        adapter.addList(list)
        binding.rcvNews.layoutManager = LinearLayoutManager(this)
        binding.rcvNews.adapter = adapter


    }

    fun loadNews() {

        var apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        apiInterface.getData("in", "general", "a5a506de67624ea9b09b1ebea7e547b4")
            .enqueue(object : Callback<NewsModel> {
                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {


                    db.deleteAllData()
                    var data = response.body()

                    Log.e(TAG, "onResponse: ========" + data?.articles)

                    var list = data?.articles as ArrayList<ArticlesItem>

                    db = SqliteDB(this@MainActivity)

                    for (i in 0..list.size - 1) {

                        var title = list[i].title
                        var descrption = list[i].description
                        var url = list[i].url
                        var urlToImage = list[i].urlToImage
                        var publishedAt = list[i].publishedAt
                        var content = list[i].content

                        db.addData(
                            title.toString(),
                            descrption.toString(),
                            url.toString(),
                            urlToImage.toString(),
                            publishedAt.toString(),
                            content.toString()
                        )
                    }

                }

                override fun onFailure(call: Call<NewsModel>, t: Throwable) {

                    Log.e(TAG, "onFailure: ===" + t.message)
                }

            })
    }

}