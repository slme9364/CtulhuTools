package com.example.gcf.cthulhutools.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.gcf.cthulhutools.adapter.PagerAdapter
import com.example.gcf.cthulhutools.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vpViewPager01.adapter = PagerAdapter(supportFragmentManager, this)
        tabTab01.setupWithViewPager(vpViewPager01)

        val tabTitles = listOf("ダイス", "キャラ一覧", "キャラ作成")
        for (i: Int in 0..tabTab01.tabCount) {
            tabTab01.getTabAt(i)?.also {
                it.text = tabTitles[i]
            }
        }
    }
}
