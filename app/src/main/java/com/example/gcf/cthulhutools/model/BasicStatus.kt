package com.example.gcf.cthulhutools.model

data class BasicStatus(
        val str: Int,
        val con: Int,
        val pow: Int,
        val dex: Int,
        val app: Int,
        val size: Int,
        val int: Int,
        val edu: Int,
        val san: Int = pow*5,
        val lack: Int = pow*5,
        val idea: Int = int*5,
        val knowledge: Int = edu*5,
        val life: Int = (con+size)/2,
        val mp: Int = pow)