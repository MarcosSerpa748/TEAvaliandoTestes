package com.example.teavaliandotestes.data.local.banco

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teavaliandotestes.data.dataclass.OpcaoQuestao
import com.example.teavaliandotestes.data.local.daos.QuestaoDao
import com.example.teavaliandotestes.data.local.entidades.QuestaoEntity
import com.example.teavaliandotestes.domain.enums.CategoriaQuestao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Provider
import kotlinx.coroutines.launch
import javax.inject.Inject

class MeuBancoCallback@Inject constructor(private val daoEscolhido:Provider<QuestaoDao>): RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase){
        super.onCreate(db)

        CoroutineScope(Dispatchers.IO).launch{
            val dao = daoEscolhido.get()

            val listaQuestoes = listOf(
                QuestaoEntity(
                    enunciado = "Enunciado generérico",
                    textApoio = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.LEITURA,
                    itens = listOf(
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                        ),
                    indicieItenCorreto = 1
                ),
                QuestaoEntity(
                    enunciado = "Enunciado generérico",
                    textApoio = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.LEITURA,
                    itens = listOf(
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                    ),
                    indicieItenCorreto = 1
                ),
                QuestaoEntity(
                    enunciado = "Enunciado generérico",
                    textApoio = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.LEITURA,
                    itens = listOf(
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                    ),
                    indicieItenCorreto = 1
                ),
                QuestaoEntity(
                    enunciado = "Enunciado generérico",
                    textApoio = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.LEITURA,
                    itens = listOf(
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                        OpcaoQuestao("Laranja","img_laranja"),
                    ),
                    indicieItenCorreto = 1
                )
            )
            dao.inserirQuestoes(listaQuestoes)
        }
    }
}