package com.example.mywebtoonapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.mywebtoonapp.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int) : Fragment() {
    private lateinit var binding: FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.apply {
            webViewClient = WebtoonWebViewClient(binding.progressBar) {url ->
                activity?.getSharedPreferences("WEB HISTORY", Context.MODE_PRIVATE)?.edit {
                    putString("tab$position", url)
                }

            }
            settings.javaScriptEnabled = true //webView 안에서도 자바스크립트 사용가능 기본값은 false (보여주기만하므로)
            loadUrl("https://comic.naver.com/webtoon/detail?titleId=769209&no=86&week=wed") // loadurl만 하면 웹브라우저만뜸
        }
        binding.backToLastButton.setOnClickListener{

            val sharedPreference = activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)
            val url = sharedPreference?.getString("tab$position","")
            if (url.isNullOrEmpty()){
                Toast.makeText(context,"마지막 저장 시점이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.webView.loadUrl(url)
            }


        }


    }

    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()

    }
}