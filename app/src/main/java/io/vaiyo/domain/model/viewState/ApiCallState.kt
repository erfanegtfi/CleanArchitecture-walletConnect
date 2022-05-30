package io.vaiyo.domain.model.viewState

import io.vaiyo.domain.utils.GeneralError

sealed class ApiCallState<out T> {
    data class Success<out T>(val data: T): ApiCallState<T>()
    data class Failure(val error: GeneralError): ApiCallState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[exception=]"
        }
    }
}