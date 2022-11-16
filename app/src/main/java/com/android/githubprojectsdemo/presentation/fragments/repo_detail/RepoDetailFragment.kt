package com.android.githubprojectsdemo.presentation.fragments.repo_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.githubprojectsdemo.R
import com.android.githubprojectsdemo.databinding.FragmentRepoDetailBinding
import com.android.githubprojectsdemo.domain.model.RepoModel
import com.android.githubprojectsdemo.presentation.MainActivity
import com.android.githubprojectsdemo.presentation.fragments.repo_detail.adapter.TopicRecyclerAdapter
import com.google.android.flexbox.FlexboxLayoutManager
import java.util.concurrent.TimeUnit

private const val PARAM_REPO_MODEL = "PARAM_REPO_MODEL"

class RepoDetailFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(repoModel: RepoModel?) =
            RepoDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PARAM_REPO_MODEL, repoModel)
                }
            }
    }

    private lateinit var binding: FragmentRepoDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        val repoModel = arguments?.getSerializable(PARAM_REPO_MODEL) as? RepoModel
        repoModel?.let {
            tvRepositoryName.text = repoModel.repoName
            tvDescription.text = repoModel.description
            tvOwner.text = repoModel.owner
            tvStarCount.text = repoModel.starCount.toString()
            tvDate.text = dateDifference(repoModel)
            tvUrl.setOnClickListener {
                (requireActivity() as MainActivity).onWebViewClicked(
                    repoModel.url
                )
            }
            rvTopics.layoutManager = FlexboxLayoutManager(requireContext())
            rvTopics.adapter = TopicRecyclerAdapter(repoModel.topicList)
        }
        toolbar.title = getString(R.string.detailed)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { (requireActivity() as MainActivity).onBackPressed() }
    }

    private fun dateDifference(repoModel: RepoModel): String {
        val time1 = repoModel.updatedDate.time
        val time2 = repoModel.createdDate.time
        val days = TimeUnit.MILLISECONDS.toDays(time1 - time2)

        return "${if (days == 0L) 1 else days} days"
    }
}