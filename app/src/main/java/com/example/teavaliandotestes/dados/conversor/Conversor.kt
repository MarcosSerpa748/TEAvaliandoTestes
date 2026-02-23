package com.example.teavaliandotestes.dados.conversor

import androidx.room.TypeConverter
import com.example.teavaliandotestes.enum.CategoriaQuestao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate


class Conversor(){

    val gson = Gson()

@TypeConverter
    fun deData(valor: LocalDate): String{
        return valor.toString()
    }
    @TypeConverter
    fun paraData(valor:String): LocalDate{
        return LocalDate.parse(valor)
    }
    @TypeConverter
    fun deCategoria(valor: CategoriaQuestao): String{
        return valor.name
    }
    @TypeConverter
    fun paraCategoria(valor:String): CategoriaQuestao{
        return CategoriaQuestao.valueOf(valor)
    }
    @TypeConverter
    fun paraJson(valor:List<String>):String{
        return gson.toJson(valor)
    }
    @TypeConverter
    fun deJson(valor:String):List<String>{
        return gson.fromJson(valor,object: TypeToken<List<String>>(){}.type)
    }

}