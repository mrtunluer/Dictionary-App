package com.mertdev.dictionaryapp.di

import android.app.Application
import androidx.room.Room
import com.mertdev.dictionaryapp.data.local.data_source.SavedWordsDatabase
import com.mertdev.dictionaryapp.data.local.data_source.SavedWordsDatabase.Companion.DB_NAME
import com.mertdev.dictionaryapp.data.local.repo.SavedWordsRepoImpl
import com.mertdev.dictionaryapp.data.remote.DictionaryApiService
import com.mertdev.dictionaryapp.data.repo.WordDataRepoImpl
import com.mertdev.dictionaryapp.data.local.data_source.SavedWordsDao
import com.mertdev.dictionaryapp.domain.repo.SavedWordsRepo
import com.mertdev.dictionaryapp.domain.repo.WordDataRepo
import com.mertdev.dictionaryapp.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApiService {
        return Retrofit.Builder().baseUrl("https://api.dictionaryapi.dev/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(DictionaryApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSavedWordsDatabase(app: Application): SavedWordsDatabase {
        return Room.databaseBuilder(app, SavedWordsDatabase::class.java, DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideSavedWordsDao(db: SavedWordsDatabase): SavedWordsDao {
        return db.savedWordsDao
    }

    @Provides
    @Singleton
    fun provideWordDataRepo(
        dictionaryApi: DictionaryApiService, dao: SavedWordsDao
    ): WordDataRepo {
        return WordDataRepoImpl(dictionaryApi, dao)
    }

    @Provides
    @Singleton
    fun provideSavedRepo(db: SavedWordsDatabase): SavedWordsRepo {
        return SavedWordsRepoImpl(db.savedWordsDao)
    }

    @Provides
    @Singleton
    fun provideGetWordDataUseCase(wordDataRepo: WordDataRepo): GetWordData {
        return GetWordData(wordDataRepo)
    }

    @Provides
    @Singleton
    fun provideGetSavedWordsUseCase(savedWordsRepo: SavedWordsRepo): GetSavedWords {
        return GetSavedWords(savedWordsRepo)
    }

    @Provides
    @Singleton
    fun provideAddIntoSavedUseCase(savedWordsRepo: SavedWordsRepo): AddIntoSaved {
        return AddIntoSaved(savedWordsRepo)
    }

    @Provides
    @Singleton
    fun provideRemoveFromSavedUseCase(savedWordsRepo: SavedWordsRepo): RemoveFromSaved {
        return RemoveFromSaved(savedWordsRepo)
    }

    @Provides
    @Singleton
    fun provideIsExistWordUseCase(savedWordsRepo: SavedWordsRepo): IsExistWord {
        return IsExistWord(savedWordsRepo)
    }

}