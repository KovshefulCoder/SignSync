package com.kovsheful.signsync.feature_signsync.di

import android.content.Context
import androidx.room.Room
import com.kovsheful.signsync.feature_signsync.data.local.SignAndSyncDatabase
import com.kovsheful.signsync.feature_signsync.data.repository.SignAndSyncRepositoryImpl
import com.kovsheful.signsync.feature_signsync.domain.repository.SignAndSyncRepository
import com.kovsheful.signsync.feature_signsync.domain.use_cases.AddOrUpdateUser
import com.kovsheful.signsync.feature_signsync.domain.use_cases.GetUser
import com.kovsheful.signsync.feature_signsync.domain.use_cases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignAndSyncModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): SignAndSyncDatabase {
        return Room.databaseBuilder(
            context,
            SignAndSyncDatabase::class.java,
            "database"
        ).fallbackToDestructiveMigration().build()
    }

//    @Provides
//    @Singleton
//    fun provideCocktailsDao(database: SignAndSyncDatabase): UserDao {
//        return database.UserDao()
//    }

    @Provides
    @Singleton
    fun provideSignAndSyncRepository(
        database: SignAndSyncDatabase
    ): SignAndSyncRepository {
        return SignAndSyncRepositoryImpl(database.UserDao())
    }

    @Provides
    @Singleton
    fun provideUserUseCases(
        repository: SignAndSyncRepository
    ): UserUseCases {
        return UserUseCases(
            getUser = GetUser(repository),
            addUser = AddOrUpdateUser(repository)
        )
    }

}
