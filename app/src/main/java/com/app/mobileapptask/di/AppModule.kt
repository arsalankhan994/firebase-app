package com.app.mobileapptask.di

import android.content.Context
import androidx.room.Room
import com.app.mobileapptask.constant.AppConstant
import com.app.mobileapptask.repository.local.TaskDao
import com.app.mobileapptask.repository.local.TaskRepository
import com.app.mobileapptask.repository.local.db.AppDatabase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuthObject(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppConstant.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepository(taskDao)
    }
}

