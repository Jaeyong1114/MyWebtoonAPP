package com.example.mywebtoonapp

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class WebtoonWebViewClient(private val progressBar : ProgressBar,
                           private val saveData: (String) -> Unit,

) : WebViewClient(){

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

        if( request != null && request.url.toString().contains("comic.naver.com/webtoon/detail")){
            saveData(request.url.toString())

        }
        //
        // "https://comic.naver.com/webtoon/detail?titleId=769209&no=86&week=wed"
        //

        return super.shouldOverrideUrlLoading(view, request)
    }


   /* override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if(request != null && request.url.toString().contains("comic.naver.com"))
            return false
        return true
    }
*/
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        progressBar.visibility = View.GONE
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        progressBar.visibility = View.VISIBLE
    }




}