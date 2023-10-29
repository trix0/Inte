package com.example.intempus.data.di

import android.app.Application
import androidx.room.Room
import com.example.intempus.data.local.DataRepositoryImplementation
import com.example.intempus.data.local.IntempusDatabase
import com.example.intempus.data.remote.ApiRepositoryImplementation
import com.example.intempus.data.remote.FakeApiRepositoryImplementation
import com.example.intempus.data.remote.IntempusApi
import com.example.intempus.domain.repository.ApiRepository
import com.example.intempus.domain.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): IntempusDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = IntempusDatabase::class.java,
            name = "intempus_db").build()
    }

    @Provides
    @Singleton
    fun providesEmployeeDao(db:IntempusDatabase):DataRepository{
        return DataRepositoryImplementation(db.employeeDao)
    }


    @Provides
    @Singleton
    fun providesApiRepository(): ApiRepository {
        return FakeApiRepositoryImplementation()
    }
}