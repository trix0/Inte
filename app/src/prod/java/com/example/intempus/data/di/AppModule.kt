package com.example.intempus.data.di

import android.app.Application
import com.example.intempus.data.remote.ApiRepositoryImplementation
import com.example.intempus.data.remote.IntempusApi
import com.example.intempus.domain.repository.ApiRepository
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
    fun providesIntempusApi():IntempusApi{
        return Retrofit.Builder().baseUrl("https://localhost:3000/")
            .build()
            .create(IntempusApi::class.java)
    }

    @Provides
    @Singleton
    fun providesApiRepository(api:IntempusApi):ApiRepository{
        return ApiRepositoryImplementation(api)
    }
}