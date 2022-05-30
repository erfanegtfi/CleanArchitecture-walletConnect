package io.vaiyo.presentation.authentication

import android.content.Context
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.komputing.khex.extensions.toNoPrefixHexString
import org.walletconnect.Session
import org.walletconnect.impls.*
import org.walletconnect.nullOnThrow
import java.io.File
import java.util.*


object WalletConnect {

    var config: Session.Config? = null
    var session: Session? = null


    private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()


    private fun provideMoshi(): Moshi = Moshi.Builder().build()

    private fun provideLocalPort():Int = 5000 + Random().nextInt(60000)

    private fun provideFileWCSessionStore(context: Context): FileWCSessionStore =
        FileWCSessionStore(File(context.cacheDir, "session_store.json").apply { createNewFile() }, provideMoshi())

    fun resetSession(context: Context) {
        nullOnThrow { session }?.clearCallbacks()
        val key = ByteArray(32).also { Random().nextBytes(it) }.toNoPrefixHexString()
        config = Session.Config(UUID.randomUUID().toString(), "https://bridge.walletconnect.org", key)//""http://localhost:${provideLocalPort()}""
        session = WCSession(
            config!!.toFullyQualifiedConfig(),
            MoshiPayloadAdapter(provideMoshi()),
            provideFileWCSessionStore(context),
            OkHttpTransport.Builder(provideOkHttpClient(), provideMoshi()),
            Session.PeerMeta(name = "Vaiyo")
        )
        session!!.offer()
    }
}