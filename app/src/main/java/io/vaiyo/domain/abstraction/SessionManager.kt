package io.vaiyo.domain.abstraction

import io.vaiyo.data.entity.UserEntity


interface SessionManager {
    fun saveToken(token: String?)
    fun getToken(): String
    fun saveUser(user: UserEntity?)
    fun getUser(): UserEntity
}