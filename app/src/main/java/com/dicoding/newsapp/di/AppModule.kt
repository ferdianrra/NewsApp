package com.dicoding.newsapp.di


import com.dicoding.newsapp.core.domain.usecase.NewsInteractor
import com.dicoding.newsapp.core.domain.usecase.NewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideNewsUseCase(newsInteractor: NewsInteractor): NewsUseCase

}