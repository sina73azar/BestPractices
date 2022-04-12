package com.example.hiltimpl.di.modules

import android.content.Context
import androidx.room.Room
import com.example.hiltimpl.data.MyDatabase
import com.example.hiltimpl.data.api.NoteDao
import com.example.hiltimpl.data.api.StudentDao
import com.example.hiltimpl.repo.NoteRepo
import com.example.hiltimpl.repo.StudentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideMyDatabase(@ApplicationContext appContext: Context): MyDatabase {
        return Room.databaseBuilder(
            appContext,
            MyDatabase::class.java,
            MyDatabase.DATABASE_NAME
        ).build()
    }
    @Provides

    fun provideStudentDao(myDatabase: MyDatabase): StudentDao {
        return myDatabase.studentDao()
    }

    @Provides

    fun provideNoteDao(myDatabase: MyDatabase): NoteDao {
        return myDatabase.noteDao()
    }
    @Provides

    fun provideNoteRepository(noteDao: NoteDao): NoteRepo {
        return NoteRepo( noteDao)
    }

    @Provides
    @Singleton
    fun provideStudentRepository(studentDao: StudentDao): StudentRepositoryImpl {
        return StudentRepositoryImpl(studentDao)
    }
}