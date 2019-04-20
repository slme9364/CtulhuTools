package com.example.gcf.cthulhutools.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.gcf.cthulhutools.view.CharacterListFragment
import com.example.gcf.cthulhutools.view.CreateCharacterFragment
import com.example.gcf.cthulhutools.view.DiceFragment

class PagerAdapter(fm: FragmentManager, context: Context)  : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return DiceFragment()
            }
            1 -> {
                return CharacterListFragment()
            }
            else -> {
                return CreateCharacterFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
}