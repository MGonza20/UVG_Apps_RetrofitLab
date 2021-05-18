package com.example.retrofitlab_uvg

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.example.retrofitlab_uvg.databinding.ItemNewBinding

class ArticleAdapter(private val articles: List<Articles>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context)
        return ViewHolder(v.inflate(R.layout.item_new, p0, false))
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item:Articles = articles[position]
        holder.bindItems(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemNewBinding.bind(itemView)
        @SuppressLint("SetTextI18n")
        fun bindItems(item: Articles){
            Picasso.get().load(item.urlToImage).into(binding.imgArticle)
            binding.txtDescription.text = item.description
        }
    }

}