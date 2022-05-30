package io.vaiyo.data.mapper

import io.vaiyo.data.entity.UserEntity
import io.vaiyo.data.entity.response.AuthenticationResponseEntity
import io.vaiyo.domain.abstraction.EntityMapper
import io.vaiyo.domain.model.User
import io.vaiyo.domain.model.response.AuthenticationResponse
import javax.inject.Inject

class MapperAuthenticationEntityDomainModel @Inject constructor() :
    EntityMapper<AuthenticationResponseEntity, AuthenticationResponse> {

    override fun mapFromEntity(entity: AuthenticationResponseEntity): AuthenticationResponse =
        AuthenticationResponse(
            jwt = entity.jwt,
            user = User(
                name = entity.user?.name,
                waddress = entity.user?.waddress,
                wtype = entity.user?.wtype,
                deleted = entity.user?.deleted,
                createdAt = entity.user?.createdAt,
                updatedAt = entity.user?.updatedAt,
                roomId = entity.user?.roomId,
                provider = entity.user?.provider,
                uid = entity.user?.uid,
                acceptedTerms = entity.user?.acceptedTerms,
                emailVerified = entity.user?.emailVerified,
                language = entity.user?.language,
                roleId = entity.user?.roleId,
            )
        )


    override fun mapToEntity(domainModel: AuthenticationResponse): AuthenticationResponseEntity =

        AuthenticationResponseEntity(
            jwt = domainModel.jwt,
            user = UserEntity(
                name = domainModel.user?.name,
                waddress = domainModel.user?.waddress,
                wtype = domainModel.user?.wtype,
                deleted = domainModel.user?.deleted,
                createdAt = domainModel.user?.createdAt,
                updatedAt = domainModel.user?.updatedAt,
                roomId = domainModel.user?.roomId,
                provider = domainModel.user?.provider,
                uid = domainModel.user?.uid,
                acceptedTerms = domainModel.user?.acceptedTerms,
                emailVerified = domainModel.user?.emailVerified,
                language = domainModel.user?.language,
                roleId = domainModel.user?.roleId,
            )
        )
}