package com.example.chargeme.core.di

import com.example.chargeme.core.mapper.SourceToTargetModelMapper
import com.example.chargeme.data.databasedatasource.entity.VehicleEntity
import com.example.chargeme.data.domainmodel.VehicleDomain
import com.example.chargeme.data.mapper.VehicleDTOToEntityMapper
import com.example.chargeme.data.mapper.VehicleEntityToDomainMapper
import com.example.chargeme.data.remotedatasource.response.Current
import com.example.chargeme.features.vehicles.mapper.VehicleDomainToUIModelMapper
import com.example.chargeme.features.vehicles.model.VehicleUI
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class VehicleEntityToDomainModelMapperModule {
    @ViewModelScoped
    @Binds
    abstract fun provideVehicleEntityToDomainModelMapperModule(vehicleEntityModelMapper: VehicleEntityToDomainMapper)
            : SourceToTargetModelMapper<VehicleEntity, VehicleDomain>
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class VehicleDTOToEntityModelMapperModule {
    @ViewModelScoped
    @Binds
    abstract fun provideVehicleDTOToEntityModelMapperModule(vehicleDTOToEntityModelMapper: VehicleDTOToEntityMapper): SourceToTargetModelMapper<Current, VehicleEntity>
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class VehicleDomainToUIModelMapperModule {
    @ViewModelScoped
    @Binds
    abstract fun provideVehicleDomainToUIModelMapperModule(vehicleDomainToUIModelMapper: VehicleDomainToUIModelMapper): SourceToTargetModelMapper<VehicleDomain, VehicleUI>
}