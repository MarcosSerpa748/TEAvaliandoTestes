package com.example.teavaliandotestes.dados.conversor

import com.google.gson.Gson
import java.time.LocalDate


class Conversores{

    val gson = Gson()

    fun deData(valor: LocalDate): String{
        return valor.toString()
    }

    fun paraData(valor:String): LocalDate{
        return LocalDate.parse(valor)
    }

}