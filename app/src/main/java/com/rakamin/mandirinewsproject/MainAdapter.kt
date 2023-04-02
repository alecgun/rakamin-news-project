package com.rakamin.mandirinewsproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakamin.mandirinewsproject.databinding.ItemNewsBinding

class MainAdapter(private var articles: ArrayList<News.Articles>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    fun setData(data: ArrayList<News.Articles>) {
        articles.clear()
        articles.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: News.Articles) {
            binding.newsTitle.text = article.title
            Glide.with(binding.root)
                .load(article.urlToImage)
                .into(binding.newsImage)
            binding.authorText.text = article.author
            binding.dateText.text = article.publishedAt
        }
    }
}
