package com.android.githubprojectsdemo.presentation.fragments.repo_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.githubprojectsdemo.databinding.ItemTopicsBinding

class TopicRecyclerAdapter(private val items: List<String>) :
    RecyclerView.Adapter<TopicRecyclerAdapter.TopicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding = ItemTopicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class TopicViewHolder(private val binding: ItemTopicsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(topic: String) = with(binding) {
            tvTopic.text = topic
        }
    }
}