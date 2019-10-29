package com.quintype.qtauth.fragments


import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.quintype.qtauth.R
import kotlinx.android.synthetic.main.fragment_webview.*

class WebViewFragment : BaseFragment() {

    companion object {
        private const val ARGS_URL = "WebViewFragment.ARG_URL"
        fun get(url: String): WebViewFragment {
            val fragment = WebViewFragment()
            val bundle = Bundle()
            bundle.putString(ARGS_URL, url)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //add a backpress support.
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.allowFileAccess = true
        webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                webViewProgress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                webViewProgress.visibility = View.GONE
            }
        }
        webView.loadUrl(arguments?.getString(ARGS_URL))

        //You need to add the following line for this solution to work; thanks skayred
        webView?.isFocusableInTouchMode = true
        webView?.requestFocus()
        webView?.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                if (webView.canGoBack()) {
                    webView.goBack()
                    true
                } else {
                    false
                }
            } else {
                return@setOnKeyListener false
            }
        }
    }

    override fun onDestroyView() {
        webView.webViewClient = null
        webView.removeAllViews()
        super.onDestroyView()
    }

}
