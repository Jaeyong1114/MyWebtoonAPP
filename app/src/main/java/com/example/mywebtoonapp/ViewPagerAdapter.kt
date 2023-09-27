package com.example.mywebtoonapp

import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val mainActivity: MainActivity) : FragmentStateAdapter(mainActivity){

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                return WebViewFragment(position,"https://comic.naver.com/webtoon/detail?titleId=747269&no=172&week=wed").apply{
                    listener = mainActivity
                }

            }
            1 -> {
                return WebViewFragment(position,"https://comic.naver.com/webtoon/detail?titleId=807356&no=28&week=thu").apply{
                    listener = mainActivity
                }

            }
            else -> {
                return WebViewFragment(position,"https://comic.naver.com/webtoon/detail?titleId=811721&no=29&week=sun").apply{
                    listener = mainActivity
                }

            }

        }
    }

}