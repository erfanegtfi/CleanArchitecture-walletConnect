package io.vaiyo.domain.model

import com.google.gson.annotations.SerializedName
import io.vaiyo.data.dataSource.remote.NetworkConfig
import io.vaiyo.presentation.utils.Utils

data class Room(
    val id: Int,
    val userId: Int,
    val name: String? = null,
    val uid: String? = null,
    val bbbId: String? = null,
    val sessions: Int? = null,
    val lastSession: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val roomSettings: String? = null,
    val moderatorPpw: String? = null,
    val attendeePw: String? = null,
    val accessCode: String? = null,
    val deleted: Boolean? = null,
    val moderatorAccessCode: String? = null,
    val participates: List<Participate>? = null,
    val totalRecordings: Int? = null,
) {

    val totalParticipates: String
        get() = participates?.size?.toString() ?: "0"

    val lastSessionDateFormatter: String
        get() = Utils.formatDate(lastSession)

    val roomLink: String
        get() = NetworkConfig.BASE_URL_ROOM + uid
}