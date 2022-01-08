package com.moralabs.hacooknew.domain.common

sealed class BaseResult <out T : Any, out U : Any>{
    data class Success <T : Any>(val data : T) : BaseResult<T, Nothing>()
    data class Error <T : Any>(val rawResponse : T) : BaseResult<T, Nothing>()
    data class UseCaseError <T : Any>(val rawResponse : T) : BaseResult<T, Nothing>()
}