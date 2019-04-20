package com.example.gcf.cthulhutools.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.gcf.cthulhutools.viewmodel.MainViewModel
import com.example.gcf.cthulhutools.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_character_list.*

class CharacterListFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_character_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val vm = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        vm.getAllCharacterName().observe(this, Observer { nameList ->
            if(nameList == null) Log.d("error","Null")
            else {
                val arrayAdapter = ArrayAdapter(activity!!.baseContext, android.R.layout.simple_list_item_1, nameList)
                character_list_view.adapter = arrayAdapter
            }
        })

        character_list_view.setOnItemClickListener { parent, view, position, id ->
            val list = parent as ListView
            val name = list.getItemAtPosition(position) as String
            vm.getCharacter(name).observe(this, Observer {
                if (it == null) return@Observer
                val gson = Gson()
                val intent = Intent(context, CharacterActivity::class.java)
                intent.putExtra("character", gson.toJson(it))
                startActivity(intent)
            })
        }
    }
}