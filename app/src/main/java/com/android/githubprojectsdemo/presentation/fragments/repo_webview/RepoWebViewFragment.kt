package com.android.githubprojectsdemo.presentation.fragments.repo_webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.android.githubprojectsdemo.R
import com.android.githubprojectsdemo.databinding.FragmentWebViewBinding
import com.android.githubprojectsdemo.presentation.MainActivity

private const val PARAM_URL = "PARAM_URL"

class RepoWebViewFragment private constructor() : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            RepoWebViewFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PARAM_URL, url)
                }
            }
    }

    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = getString(R.string.loading)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { (requireActivity() as MainActivity).onBackPressed() }

        val url = arguments?.getString(PARAM_URL) ?: ""
        webView.loadUrl(url)
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                toolbar.title = (view?.title ?: "")
            }
        }
    }
}