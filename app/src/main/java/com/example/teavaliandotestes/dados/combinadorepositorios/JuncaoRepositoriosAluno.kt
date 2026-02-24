package com.example.teavaliandotestes.dados.combinadorepositorios

import com.example.teavaliandotestes.dados.repositorio.AlunoRepositorio
import com.example.teavaliandotestes.dados.repositorio.AlunoRepositorioimpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class JuncaoRepositoriosAluno{

    @Binds
    abstract fun recebaImplementacao(repositorio: AlunoRepositorioimpl): AlunoRepositorio
}