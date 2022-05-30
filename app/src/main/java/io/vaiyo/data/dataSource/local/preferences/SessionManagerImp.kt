package io.vaiyo.data.dataSource.local.preferences

import io.vaiyo.data.dataSource.local.preferences.PreferencesConstants.TOKEN_KEY
import io.vaiyo.data.dataSource.local.preferences.PreferencesConstants.USER_KEY
import io.vaiyo.data.entity.UserEntity
import io.vaiyo.domain.abstraction.SessionManager
import io.vaiyo.domain.model.User
import javax.inject.Inject

class SessionManagerImp @Inject constructor(
    private val session: Session
): SessionManager {

    override  fun saveToken(token: String?) {
        session.setPreferenceValue(TOKEN_KEY, token)
    }

    override fun getToken(): String = session.getPreferenceValue(TOKEN_KEY, "")

    override fun saveUser(user: UserEntity?) {
        session.setObject(USER_KEY, user)
    }

    override fun getUser(): UserEntity = session.getObject(USER_KEY, UserEntity::class.java)
}

