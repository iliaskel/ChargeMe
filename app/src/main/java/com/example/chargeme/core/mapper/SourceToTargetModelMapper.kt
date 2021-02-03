package com.example.chargeme.core.mapper

interface SourceToTargetModelMapper<Source, Target> {
    fun mapSourceToTarget(sourceObject: Source): Target
    fun mapSourceToTargetList(sourceObjectList: List<Source>): List<Target>
}