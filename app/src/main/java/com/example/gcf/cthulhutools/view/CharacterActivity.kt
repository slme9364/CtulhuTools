package com.example.gcf.cthulhutools.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.gcf.cthulhutools.R
import com.example.gcf.cthulhutools.model.Character
import com.example.gcf.cthulhutools.viewmodel.MainViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val characterStr = intent.getStringExtra("character")
        val character = Gson().fromJson(characterStr, Character::class.java)
        val status = character.status
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        Log.d("Activity", character.toString())
        name_text.text = character.name
        job_text.text = character.job
        sex_text.text = character.sex
        age_text.text = character.age.toString()
        str_text.text = status.str.toString()
        con_text.text = status.con.toString()
        pow_text.text = status.pow.toString()
        app_text.text = status.app.toString()
        int_text.text = status.int.toString()
        edu_text.text = status.edu.toString()
        dex_text.text = status.dex.toString()
        siz_text.text = status.size.toString()
        san_text.text = status.san.toString()
        lack_text.text = status.lack.toString()
        idea_text.text = status.idea.toString()
        know_text.text = status.knowledge.toString()
        life_text.text = status.life.toString()
        magic_text.text = status.mp.toString()
        background_text.text = character.background

        character.skills.toList().forEachIndexed { index, skill ->
            val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val itemView = inflater.inflate(R.layout.skill_view_layout, null)
            skill_view.addView(itemView)
            val layout = skill_view.getChildAt(index) as LinearLayout
            (layout.getChildAt(0) as TextView).text = skill.first
            (layout.getChildAt(1) as TextView).text = skill.second.toString()
        }

        delete_button.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("確認")
                    .setMessage("本当に削除しますか")
                    .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            vm.deleteCharacter(character.name)
                            Toast.makeText(this@CharacterActivity, "削除しました", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@CharacterActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show()
        }

        update_button.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("character", characterStr)
            startActivity(intent)
        }
    }
}