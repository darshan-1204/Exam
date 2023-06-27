package com.example.exam.Adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.exam.Activity.MainActivity
import com.example.exam.Datatbase.DBModel
import com.example.exam.Datatbase.SqliteDB
import com.example.exam.R
import com.example.exam.databinding.NewsItemBinding

class NewsAdapter(mainActivity: MainActivity) : Adapter<NewsAdapter.NewsHolder>() {

    lateinit var list: ArrayList<DBModel>
    var context = mainActivity
    var db = SqliteDB(context)

    class NewsHolder(itemView: NewsItemBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        var binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(list[position].urlToImage).into(rcvImg)
            rcvTitle.text = list[position].title
            publishedAt.text = list[position].publishedAt
        }


        holder.binding.imgDelete.setOnClickListener {
            db.deleteData(list[position].id)
        }
        holder.itemView.setOnClickListener {


            var dialog = Dialog(holder.itemView.context)
            dialog.setContentView(R.layout.data_update)


            var edtTitle = dialog.findViewById<EditText>(R.id.edtTitle)
            var desc = dialog.findViewById<EditText>(R.id.edtDesc)
            var url = dialog.findViewById<EditText>(R.id.edtUrl)
            var urlToImage = dialog.findViewById<EditText>(R.id.edtUrlToImage)
            var publishedAt = dialog.findViewById<EditText>(R.id.edtPublishTime)
            var content = dialog.findViewById<EditText>(R.id.edtContent)


            var btnUpdate = dialog.findViewById<CardView>(R.id.btnUpdate)

            edtTitle.setText(list[position].title)
            desc.setText(list[position].description)
            url.setText(list[position].url)
            urlToImage.setText(list[position].urlToImage)
            publishedAt.setText(list[position].publishedAt)
            content.setText(list[position].content)

            dialog.show()

            btnUpdate.setOnClickListener {

                db.updateData(
                    edtTitle.text.toString(),
                    desc.text.toString(),
                    url.text.toString(),
                    urlToImage.text.toString(),
                    publishedAt.text.toString(),
                    content.text.toString(),list[position].id)

                dialog.dismiss()
            }

        }

    }

    fun addList(list: ArrayList<DBModel>) {
        this.list = list
    }
}