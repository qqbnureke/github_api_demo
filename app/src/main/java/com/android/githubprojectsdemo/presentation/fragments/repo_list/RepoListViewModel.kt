package com.android.githubprojectsdemo.presentation.fragments.repo_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.githubprojectsdemo.data.model.NetworkException
import com.android.githubprojectsdemo.domain.Repository
import com.android.githubprojectsdemo.domain.model.RepoModel
import kotlinx.coroutines.*
import javax.inject.Inject

class RepoListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val repoLiveData = MutableLiveData<List<RepoModel>>()
    val repoNextLiveData = MutableLiveData<List<RepoModel>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private var page = 1
    private var isLastPage = false
    private var job: Job? = null

    init {
        getCachedRepoList()
    }

    private fun getCachedRepoList() {
        CoroutineScope(Dispatchers.IO).launch {
            val repos = repository.getCachedRepos()
            repoLiveData.postValue(repos)
        }
    }

    fun onQueryChanged(query: String) {
        getRepoList(1, query)
    }

    fun getRepoList() {
        getRepoList(1)
    }

    fun getNextRepoList() {
        if (isLastPage) return
        getRepoList(page)
    }

    private fun getRepoList(currentPage: Int, query: String? = null) {
        if (job?.isActive == true) {
            job?.cancel()
        }
        job = CoroutineScope(Dispatchers.IO).launch {
            loadingLiveData.postValue(true)
            delay(300)
            try {
                val repos = repository.getRepos(currentPage, query ?: "")
                if (repos.isEmpty()) {
                    isLastPage = true
                } else {
                    if (currentPage == 1) repoLiveData.postValue(repos)
                    else repoNextLiveData.postValue(repos)
                }
                page++
            } catch (ex: NetworkException) {
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            loadingLiveData.postValue(false)
        }
    }
}

class RepoListViewModelFactory @Inject constructor(
    private val map: Map<Class<*>, @JvmSuppressWildcards ViewModel>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return map[modelClass] as T
    }
}
