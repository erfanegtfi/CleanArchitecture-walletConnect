package io.vaiyo.domain.utils

enum class ErrorType {
    NETWORK,
    TIMEOUT,
    AUTHORIZED,
    SERVER_ERROR,
    CACHE_ERROR,
    NOT_FOUND,
    BAD_REQUEST,
    UNKNOWN_REMOTE,
    UNKNOWN
}