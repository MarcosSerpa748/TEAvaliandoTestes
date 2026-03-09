package com.example.teavaliandotestes.data.local.banco

import android.content.Context
import androidx.room.Room
import com.example.teavaliandotestes.data.local.daos.AlunoDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Database{

    @Provides
    @Singleton
    fun gerarBanco(@ApplicationContext contexto: Context): AppDatabase {
        return Room.databaseBuilder(
            context = contexto,
            klass = AppDatabase::class.java,
            name = "teavaliando_db"
        ).fallbackToDestructiveMigration().build()
    }
    @Provides
    fun gerarAlunoDao(appDatabase: AppDatabase): AlunoDAO {
        return appDatabase.gerarDaoAluno()
    }
}