package com.example.mywebtoonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.Gravity
import android.webkit.WebViewClient
import android.widget.TextView
import com.example.mywebtoonapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)


        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position ->
            run{
                val textView = TextView(this)
                textView.text = "position $position"
                textView.gravity = Gravity.CENTER
                tab.customView = textView
                //tab.text = "position $position"
            }

        }.attach() //tabLayout 과 viewPager가 결합됨




    }

    override fun onBackPressed() {

        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
        if( currentFragment is WebViewFragment){
            if(currentFragment.canGoBack()){

                currentFragment.goBack()

            }
            else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }



    }
}
