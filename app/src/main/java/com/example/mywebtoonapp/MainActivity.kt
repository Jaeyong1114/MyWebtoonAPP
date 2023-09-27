package com.example.mywebtoonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.webkit.WebViewClient
import com.example.mywebtoonapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val container = binding.fragmentContatiner
        binding.button1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContatiner, WebViewFragment())
                commit()
            }  //  supportFragmnetManager 액티비티 내부에있는 프래그먼트를 관리해주는 기능 , 트랜잭션을 열면 commit 해야함
               // 트랜잭션이 실행되면서 fragmentContainer 라는 뷰에 WebViewFragment 를 replace 해라


        }

        binding.button2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContatiner,BFragmnet())
                commit()
            }

        }



    }
}
