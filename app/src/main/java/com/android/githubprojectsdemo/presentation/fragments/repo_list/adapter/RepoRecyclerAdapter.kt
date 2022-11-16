package com.android.githubprojectsdemo.presentation.fragments.repo_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.githubprojectsdemo.databinding.ItemRepoBinding
import com.android.githubprojectsdemo.domain.model.RepoModel

class RepoRecyclerAdapter(private val onItemClick: (RepoModel) -> Unit) :
    RecyclerView.Adapter<RepoRecyclerAdapter.RepoViewHolder>() {

    private var items: ArrayList<RepoModel> = ArrayList()

    fun setItems(items: List<RepoModel>) {
        this.items = ArrayList(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<RepoModel>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class RepoViewHolder(
        private val binding: ItemRepoBinding,
        private val onItemClick: (RepoModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: RepoModel) = with(binding) {
            root.setOnClickListener { onItemClick(model) }
            tvTitle.text = model.repoName
            tvOwner.text = model.owner
            tvStarCount.text = model.starCount.toString()
        }
    }
}