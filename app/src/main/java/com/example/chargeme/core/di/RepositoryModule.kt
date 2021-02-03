package com.example.chargeme.core.di

import com.example.chargeme.features.vehicles.repository.VehiclesRepository
import com.example.chargeme.features.vehicles.repository.VehiclesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideRepositoryModule(vehiclesRepositoryImpl: VehiclesRepositoryImpl): VehiclesRepository
}