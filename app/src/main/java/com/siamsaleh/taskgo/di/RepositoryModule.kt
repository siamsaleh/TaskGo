package com.siamsaleh.taskgo.di

import com.siamsaleh.taskgo.data.remote.ApiService
import com.siamsaleh.taskgo.data.repository.MainRepository
import com.siamsaleh.taskgo.domain.usecase.MainUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(apiService: ApiService): MainUseCase {
        return MainRepository(apiService)
    }
}