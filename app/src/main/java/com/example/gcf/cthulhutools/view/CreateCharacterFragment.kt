package com.example.gcf.cthulhutools.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import com.example.gcf.cthulhutools.viewmodel.MainViewModel
import com.example.gcf.cthulhutools.R
import com.example.gcf.cthulhutools.model.BasicStatus
import com.example.gcf.cthulhutools.model.Character
import kotlinx.android.synthetic.main.fragment_create_character.*
import java.lang.Exception

class CreateCharacterFragment : Fragment() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_create_character, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val vm = ViewModelProviders.of(activity!!)
                .get(MainViewModel::class.java)
        add_skill_button.setOnClickListener {
            count += 1
            val inflater = activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val itemView = inflater.inflate(R.layout.skill_layout, null)

            skill_page.addView(itemView)
        }

        save_button.setOnClickListener {

            val name = name.text.toString()
            val sex = sex.selectedItem.toString()
            val background = background.text.toString()
            val job = job.text.toString()
            val status = try {
                val str = str.text.toString().toInt()
                val con = con.text.toString().toInt()
                val pow = pow.text.toString().toInt()
                val dex = dex.text.toString().toInt()
                val app = app.text.toString().toInt()
                val siz = siz.text.toString().toInt()
                val edu = edu.text.toString().toInt()
                val int = int_value.text.toString().toInt()
                BasicStatus(
                        str = str,
                        con = con,
                        pow = pow,
                        dex = dex,
                        app = app,
                        size = siz,
                        edu = edu,
                        int = int)
            } catch (e: Exception) {
                Toast.makeText(context,"数字を入力してください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("Status", status.toString())

            val skills = mutableMapOf<String, Int>()
            for (i in 0..(skill_page.childCount-1)) {
                val skill_layout = skill_page.getChildAt(i) as LinearLayout
                val skill_name = (skill_layout.getChildAt(0) as Spinner).selectedItem.toString()
                val skill_value = (skill_layout.getChildAt(1) as EditText).text.toString().toInt()
                if (skills[skill_name] != null) {
                    Toast.makeText(context, "スキルが重複しています", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                skills.put(skill_name, skill_value)
            }
            Log.d("Skill", skills.toString())

            val age = try {
                age.text.toString().toInt()
            } catch (e: Exception) {
                Toast.makeText(context,"数字を入力してください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val character = Character(name, job, age, sex, status, skills, background)
            Log.d("Character", character.toString())
            Log.d("Character", character.toEntity().toString())
            vm.addCharacter(character)
            Toast.makeText(context, "保存しました", Toast.LENGTH_SHORT).show()
            activity!!.finish()
            startActivity(activity!!.intent)
        }
    }
}