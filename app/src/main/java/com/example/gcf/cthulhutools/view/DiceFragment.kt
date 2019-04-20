package com.example.gcf.cthulhutools.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.gcf.cthulhutools.model.Dice
import com.example.gcf.cthulhutools.R
import kotlinx.android.synthetic.main.fragment_dice.*

class DiceFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_dice, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAllSpinner()
        setAllClickListener()
    }

    private fun setAllSpinner() {
        val first = listOf("1", "2", "3", "4", "5", "6", "8", "10", "12", "15", "18", "24")
        val second = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "12", "20", "100")

        spinner.adapter = ArrayAdapter(activity!!.baseContext, android.R.layout.simple_spinner_item, first).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinner2.adapter = ArrayAdapter(activity!!.baseContext, android.R.layout.simple_spinner_item, second).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private fun setAllClickListener() {
        dice_1D100.setOnClickListener {
            val result = Dice(100).rollNTimesSum(1).second
            result_1D100.text = result.toString()
        }

        dice_1D20.setOnClickListener {
            val result = Dice(20).rollNTimesSum(1).second
            result_1D20.text = result.toString()
        }

        dice_1D10.setOnClickListener {
            val result = Dice(10).rollNTimesSum(1).second
            result_1D10.text = result.toString()
        }

        dice_1D6.setOnClickListener {
            val result = Dice(6).rollNTimesSum(1).second
            result_1D6.text = result.toString()
        }

        dice_1D4.setOnClickListener {
            val result = Dice(4).rollNTimesSum(1).second
            result_1D4.text = result.toString()
        }

        dice_1D3.setOnClickListener {
            val result = Dice(3).rollNTimesSum(1).second
            result_1D3.text = result.toString()
        }

        dice_3D6.setOnClickListener {
            val result = Dice(6).rollNTimesSum(3).second
            result_3D6.text = result.toString()
        }

        dice_2D3.setOnClickListener {
            val result = Dice(3).rollNTimesSum(2).second
            result_2D3.text = result.toString()
        }

        dice_nDm.setOnClickListener {
            val n = spinner.selectedItem.toString().toInt()
            val m = spinner2.selectedItem.toString().toInt()
            val result = Dice(m).rollNTimesSum(n).first
            result_nDm.text = result
        }
    }
}