package com.android.githubprojectsdemo.presentation

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.githubprojectsdemo.R
import com.android.githubprojectsdemo.domain.model.RepoModel
import com.android.githubprojectsdemo.presentation.fragments.repo_detail.RepoDetailFragment
import com.android.githubprojectsdemo.presentation.fragments.repo_list.RepoListFragment
import com.android.githubprojectsdemo.presentation.fragments.repo_webview.RepoWebViewFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setInitialFragment()
    }

    private fun setInitialFragment() {
        navigateToFragment(RepoListFragment(), null)
    }

    fun onDetailClicked(repoModel: RepoModel) {
        hideKeyboard()
        navigateToFragment(RepoDetailFragment.newInstance(repoModel), "detail_${repoModel.id}")
    }

    fun onWebViewClicked(url: String) {
        navigateToFragment(RepoWebViewFragment.newInstance(url), "detail_${url}")
    }

    private fun navigateToFragment(fragment: Fragment, key: String?) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.flRoot, fragment)
        if (!key.isNullOrEmpty()) transaction.addToBackStack(key)
        transaction.commit()
    }

    private fun hideKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}