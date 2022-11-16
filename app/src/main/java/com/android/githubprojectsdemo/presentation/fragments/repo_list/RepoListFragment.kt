package com.android.githubprojectsdemo.presentation.fragments.repo_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.githubprojectsdemo.databinding.FragmentRepoListBinding
import com.android.githubprojectsdemo.domain.model.RepoModel
import com.android.githubprojectsdemo.presentation.AppApplication
import com.android.githubprojectsdemo.presentation.MainActivity
import com.android.githubprojectsdemo.presentation.fragments.repo_list.adapter.RepoRecyclerAdapter
import javax.inject.Inject

class RepoListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: RepoListViewModelFactory

    private lateinit var binding: FragmentRepoListBinding
    private lateinit var viewModel: RepoListViewModel
    private lateinit var repoAdapter: RepoRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ((requireActivity() as MainActivity).application as AppApplication).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RepoListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "Github Projects Demo"
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.getRepoList() }

        setupRecyclerView()
        setupSearchEditText()
        viewModel.getRepoList()
        observeLiveData()
    }

    private fun onRepoClicked(repoModel: RepoModel) {
        (requireActivity() as MainActivity).onDetailClicked(repoModel)
    }

    private fun observeLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = it
        }
        viewModel.repoLiveData.observe(viewLifecycleOwner) {
            repoAdapter.setItems(it)
        }
        viewModel.repoNextLiveData.observe(viewLifecycleOwner) {
            repoAdapter.addItems(it)
        }
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        repoAdapter = RepoRecyclerAdapter(::onRepoClicked)
        val linearLayoutManager = LinearLayoutManager(requireContext())

        layoutManager = linearLayoutManager
        adapter = repoAdapter
        addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val firstVisibleItemPosition =
                    linearLayoutManager.findFirstVisibleItemPosition()
                if (!binding.swipeRefreshLayout.isRefreshing) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        viewModel.getNextRepoList()
                    }
                }

            }
        })
    }

    private fun setupSearchEditText() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.onQueryChanged(s?.toString() ?: "")
            }

        })
    }
}