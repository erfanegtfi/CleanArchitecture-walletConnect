package io.vaiyo.domain.model.response

import io.vaiyo.domain.model.User
import io.vaiyo.domain.model.base.ApiBaseResponse

data class AuthenticationResponse(
    val jwt: String? = null,
    val user: User? = null,
) : ApiBaseResponse()