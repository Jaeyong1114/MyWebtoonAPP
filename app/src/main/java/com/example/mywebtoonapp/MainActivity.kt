package com.example.mywebtoonapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.Gravity
import android.webkit.WebViewClient
import android.widget.TextView
import com.example.mywebtoonapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference =
            getSharedPreferences(WebViewFragment.Companion.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        val tab0 = sharedPreference.getString("tab0_name", "웹툰1")
        val tab1 = sharedPreference.getString("tab1_name", "웹툰2")
        val tab2 = sharedPreference.getString("tab2_name", "웹툰3")


        binding.viewPager.adapter = ViewPagerAdapter(this)


        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
               /* val textView = TextView(this)
                textView.text = when (position) {
                    0 -> tab0
                    1 -> tab1
                    else -> tab2

                }
                textView.gravity = Gravity.CENTER
                tab.customView = textView*/
                tab.text = when(position){
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
            }

        }.attach() //tabLayout 과 viewPager가 결합됨


    }

    override fun onBackPressed() {

        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
        if (currentFragment is WebViewFragment) {
            if (currentFragment.canGoBack()) {

                currentFragment.goBack()

            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }


    }

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name

    }
}
