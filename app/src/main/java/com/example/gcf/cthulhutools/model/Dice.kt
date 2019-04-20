package com.example.gcf.cthulhutools.model

import java.lang.Exception

class Dice(val planesNum: Int) {
    fun rollNTimesSum(n: Int): Pair<String, Int> {
        var text = ""
        var sum = 0
        if (n <= 0) throw Exception()
        for (i in 1..n) {
            val num = (Math.random() * planesNum).toInt() + 1
            //val rand = Random(System.currentTimeMillis())
            //val num = rand.nextInt(planesNum) + 1
            text += "$num "
            sum += num
        }
        text += ": $sum"
        return text to sum
    }

}