package com.example.gcf.cthulhutools.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import com.example.gcf.cthulhutools.R
import com.example.gcf.cthulhutools.model.BasicStatus
import com.example.gcf.cthulhutools.model.Character
import com.example.gcf.cthulhutools.viewmodel.MainViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_update_character.*
import java.lang.Exception

class UpdateActivity : AppCompatActivity() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_character)
        initializeView()
        add_skill_button.setOnClickListener {
            val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val itemView = inflater.inflate(R.layout.skill_layout, null)
            skill_view.addView(itemView)
        }
        update_button.setOnClickListener {
            updateCharacter()
        }
    }

    private fun initializeView() {
        val characterStr = intent.getStringExtra("character")
        val character = Gson().fromJson(characterStr, Character::class.java)
        val status = character.status
        Log.d("Activity", character.toString())
        name_text.text = character.name
        job_text.setText(character.job, TextView.BufferType.EDITABLE)
        age_text.setText(character.age.toString(), TextView.BufferType.EDITABLE)
        str_text.setText(status.str.toString(), TextView.BufferType.EDITABLE)
        con_text.setText(status.con.toString(), TextView.BufferType.EDITABLE)
        pow_text.setText(status.pow.toString(), TextView.BufferType.EDITABLE)
        app_text.setText(status.app.toString(), TextView.BufferType.EDITABLE)
        int_text.setText(status.int.toString(), TextView.BufferType.EDITABLE)
        edu_text.setText(status.edu.toString(), TextView.BufferType.EDITABLE)
        dex_text.setText(status.dex.toString(), TextView.BufferType.EDITABLE)
        siz_text.setText(status.size.toString(), TextView.BufferType.EDITABLE)
        san_text.setText(status.san.toString(), TextView.BufferType.EDITABLE)
        lack_text.setText(status.lack.toString(), TextView.BufferType.EDITABLE)
        idea_text.setText(status.idea.toString(), TextView.BufferType.EDITABLE)
        know_text.setText(status.knowledge.toString(), TextView.BufferType.EDITABLE)
        life_text.setText(status.life.toString(), TextView.BufferType.EDITABLE)
        magic_text.setText(status.mp.toString(), TextView.BufferType.EDITABLE)
        background_text.setText(character.background, TextView.BufferType.EDITABLE)

        val sexId = resources.getStringArray(R.array.sex_list).indexOf(character.sex)
        sex.setSelection(sexId)

        character.skills.toList().forEachIndexed { index, skill ->
            count++
            val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val itemView = inflater.inflate(R.layout.skill_layout, null)
            skill_view.addView(itemView)
            val layout = skill_view.getChildAt(index) as LinearLayout

            val skillIndex = resources.getStringArray(R.array.skill_list).indexOf(skill.first)
            (layout.getChildAt(0) as Spinner).setSelection(skillIndex)
            (layout.getChildAt(1) as EditText).setText(skill.second.toString(),TextView.BufferType.EDITABLE)
        }
    }

    private fun updateCharacter() {
        val vm = ViewModelProviders.of(this)
                .get(MainViewModel::class.java)

        val name = name_text.text.toString()
        val sex = sex.selectedItem.toString()
        val background = background_text.text.toString()
        val job = job_text.text.toString()
        val status = try {
            val str = str_text.text.toString().toInt()
            val con = con_text.text.toString().toInt()
            val pow = pow_text.text.toString().toInt()
            val dex = dex_text.text.toString().toInt()
            val app = app_text.text.toString().toInt()
            val siz = siz_text.text.toString().toInt()
            val edu = edu_text.text.toString().toInt()
            val int = int_text.text.toString().toInt()
            val san = san_text.text.toString().toInt()
            val lack = lack_text.text.toString().toInt()
            val idea = idea_text.text.toString().toInt()
            val know = know_text.text.toString().toInt()
            val life = life_text.text.toString().toInt()
            val magic = magic_text.text.toString().toInt()
            BasicStatus(
                    str = str,
                    con = con,
                    pow = pow,
                    dex = dex,
                    app = app,
                    size = siz,
                    edu = edu,
                    int = int,
                    san = san,
                    idea = idea,
                    lack = lack,
                    knowledge = know,
                    life = life,
                    mp = magic)
        } catch (e: Exception) {
            Toast.makeText(this,"数字を入力してください", Toast.LENGTH_SHORT).show()
            return
        }
        val skills = mutableMapOf<String, Int>()
        for (i in 0..(skill_view.childCount-1)) {
            val skill_layout = skill_view.getChildAt(i) as LinearLayout
            val skill_name = (skill_layout.getChildAt(0) as Spinner).selectedItem.toString()
            val skill_value = (skill_layout.getChildAt(1) as EditText).text.toString().toInt()
            if (skills[skill_name] != null) {
                Toast.makeText(this, "スキルが重複しています", Toast.LENGTH_SHORT).show()
                return
            }
            skills.put(skill_name, skill_value)
        }
        val age = try {
            age_text.text.toString().toInt()
        } catch (e: Exception) {
            Toast.makeText(this,"数字を入力してください", Toast.LENGTH_SHORT).show()
            return
        }
        val character = Character(name, job, age, sex, status, skills, background)
        vm.updateCharacter(character)
        Toast.makeText(this," 保存しました", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

}