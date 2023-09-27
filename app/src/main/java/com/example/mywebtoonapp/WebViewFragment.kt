package com.example.mywebtoonapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.mywebtoonapp.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int, private val webViewUrl: String) : Fragment() {

    var listener: OnTabLayoutNameChanged? = null

    private lateinit var binding: FragmentWebviewBinding

    companion object {

        const val SHARED_PREFERENCE = "WEB_HISTORY"
    }

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
            webViewClient = WebtoonWebViewClient(binding.progressBar) { url ->
                activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)?.edit {
                    putString("tab$position", url)
                }

            }
            settings.javaScriptEnabled = true //webView 안에서도 자바스크립트 사용가능 기본값은 false (보여주기만하므로)
            loadUrl(webViewUrl) // loadurl만 하면 웹브라우저만뜸
        }
        binding.backToLastButton.setOnClickListener {

            val sharedPreference =
                activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
            val url = sharedPreference?.getString("tab$position", "")
            if (url.isNullOrEmpty()) {
                Toast.makeText(context, "마지막 저장 시점이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.webView.loadUrl(url)
            }


        }
        binding.changeTabnameButton.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val editText = EditText(context)

            dialog.setView(editText)
            dialog.setPositiveButton("저장") { _, _ ->
                activity?.getSharedPreferences(("WEB_HISTORY"), Context.MODE_PRIVATE)?.edit {
                    putString("tab$position}_name", editText.text.toString())
                    listener?.nameChanged(position, editText.text.toString())
                }
            }
            dialog.setNegativeButton("취소") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            dialog.show()
        }


    }

    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()

    }
}

interface OnTabLayoutNameChanged {
    fun nameChanged(position: Int, name: String)
}