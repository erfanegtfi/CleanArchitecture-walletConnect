package io.vaiyo.data.mapper.room

import com.google.common.truth.Truth
import io.vaiyo.data.entity.RoomEntity
import io.vaiyo.data.entity.UserEntity
import io.vaiyo.data.entity.response.AuthenticationResponseEntity
import io.vaiyo.data.entity.response.RoomListResponseEntity
import io.vaiyo.data.mapper.MapperAuthenticationEntityDomainModel
import io.vaiyo.domain.model.Room
import io.vaiyo.domain.model.User
import io.vaiyo.domain.model.response.AuthenticationResponse
import io.vaiyo.domain.model.response.RoomListResponse
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MapperRoomListResponseEntityDomainModelTest {

    @Test
    fun `map AuthenticationResponseEntity to AuthenticationResponse`() {
        val mapperRoomListResponseEntityDomainModel = MapperRoomListResponseEntityDomainModel()
        val roomEntity = RoomEntity(id = 1, userId = 2)
        val roomList = mutableListOf<RoomEntity>()
        roomList.add(roomEntity)
        val roomListResponseEntity = RoomListResponseEntity(roomList)
        val roomListResponse = mapperRoomListResponseEntityDomainModel.mapFromEntity(roomListResponseEntity)
        Truth.assertThat(roomListResponse).isInstanceOf(RoomListResponse::class.java)
        Truth.assertThat(roomListResponse.rooms?.get(0)).isInstanceOf(Room::class.java)

        Truth.assertThat(roomListResponse.rooms?.get(0)?.id).isEqualTo(roomListResponseEntity.rooms?.get(0)?.id)
    }

    @Test
    fun `map AuthenticationResponse to AuthenticationResponseEntity`() {
        val mapperRoomListResponseEntityDomainModel = MapperRoomListResponseEntityDomainModel()
        val room = Room(id = 1, userId = 2)
        val roomList = mutableListOf<Room>()
        roomList.add(room)
        val roomListResponse = RoomListResponse(roomList)
        val roomListResponseEntity : RoomListResponseEntity = mapperRoomListResponseEntityDomainModel.mapToEntity(roomListResponse)
        Truth.assertThat(roomListResponseEntity).isInstanceOf(RoomListResponseEntity::class.java)
        Truth.assertThat(roomListResponseEntity.rooms?.get(0)).isInstanceOf(RoomEntity::class.java)
        Truth.assertThat(roomListResponseEntity.rooms?.get(0)?.id).isEqualTo(roomListResponseEntity.rooms?.get(0)?.id)
    }


}