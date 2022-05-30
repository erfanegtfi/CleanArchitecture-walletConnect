package io.vaiyo.data.mapper

import com.google.common.truth.Truth
import io.vaiyo.data.entity.UserEntity
import io.vaiyo.data.entity.response.AuthenticationResponseEntity
import io.vaiyo.domain.model.User
import io.vaiyo.domain.model.response.AuthenticationResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MapperAuthenticationEntityDomainModelTest {


    @Test
    fun `map AuthenticationResponseEntity to AuthenticationResponse`() {
        val mapperAuthenticationEntityDomainModel = MapperAuthenticationEntityDomainModel()
        val authenticationResponseEntity = AuthenticationResponseEntity(jwt = "jwt", user = UserEntity(name = "name"))
        val authenticationResponse =
            mapperAuthenticationEntityDomainModel.mapFromEntity(authenticationResponseEntity)
        Truth.assertThat(authenticationResponse).isInstanceOf(AuthenticationResponse::class.java)
        Truth.assertThat(authenticationResponse.jwt).isEqualTo(authenticationResponseEntity.jwt)
        Truth.assertThat(authenticationResponse.user?.name).isEqualTo(authenticationResponseEntity.user?.name)
    }

    @Test
    fun `map AuthenticationResponse to AuthenticationResponseEntity`() {
        val mapperAuthenticationEntityDomainModel = MapperAuthenticationEntityDomainModel()
       val authenticationResponse =  AuthenticationResponse(jwt = "jwt", user = User(name = "name"))
        val authenticationResponseEntity =
            mapperAuthenticationEntityDomainModel.mapToEntity(authenticationResponse)
        Truth.assertThat(authenticationResponseEntity).isInstanceOf(AuthenticationResponseEntity::class.java)
        Truth.assertThat(authenticationResponseEntity.jwt).isEqualTo(authenticationResponse.jwt)
        Truth.assertThat(authenticationResponseEntity.user?.name).isEqualTo(authenticationResponse.user?.name)
    }

}