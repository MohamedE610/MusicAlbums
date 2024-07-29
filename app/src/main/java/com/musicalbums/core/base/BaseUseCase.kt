package com.musicalbums.core.base

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<Output> {
    abstract operator fun invoke(): Flow<Output>
}

abstract class BaseUseCaseWithParam<Param, Output> {
    abstract operator fun invoke(param: Param): Flow<Output>
}