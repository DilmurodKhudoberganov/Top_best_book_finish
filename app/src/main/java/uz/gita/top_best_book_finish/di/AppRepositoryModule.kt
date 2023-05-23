package uz.gita.top_best_book_finish.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.top_best_book_finish.domain.repository.AppRepository
import uz.gita.top_best_book_finish.domain.repository.impl.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppRepositoryModule {
    @[Binds Singleton]
    fun bindAuthRepository(impl: AppRepositoryImpl): AppRepository

}