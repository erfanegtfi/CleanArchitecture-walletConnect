package io.vaiyo.data.dataSource.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.vaiyo.data.dataSource.local.preferences.Session
import io.vaiyo.data.dataSource.local.preferences.SessionManagerImp
import io.vaiyo.domain.abstraction.SessionManager
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideSessionManager(session: Session): SessionManager =
        SessionManagerImp(session)

    @Provides
    @Singleton
    fun provideAppSession(context: Context): Session {
        return Session(context)
    }


}