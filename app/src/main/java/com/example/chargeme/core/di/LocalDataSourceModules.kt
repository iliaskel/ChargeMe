package com.example.chargeme.core.di

import android.content.Context
import androidx.room.Room
import com.example.chargeme.data.databasedatasource.VehiclesDatabase
import com.example.chargeme.data.databasedatasource.datasource.VehiclesLocalDataSource
import com.example.chargeme.data.databasedatasource.datasource.VehiclesLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseModule(@ApplicationContext applicationContext: Context): VehiclesDatabase {
        return Room.databaseBuilder(applicationContext, VehiclesDatabase::class.java, "vehicles_db")
            .fallbackToDestructiveMigration().build()
    }
}


@InstallIn(SingletonComponent::class)
@Module
abstract class VehiclesLocalDataSourceModule {
    @Singleton
    @Binds
    abstract fun provideVehiclesLocalDataSourceModule(vehiclesLocalDataSource: VehiclesLocalDataSourceImpl): VehiclesLocalDataSource
}