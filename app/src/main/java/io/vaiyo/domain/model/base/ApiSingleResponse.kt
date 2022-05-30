package io.vaiyo.domain.model.base;


import com.google.gson.annotations.SerializedName;


class ApiSingleResponse<T>(
    @SerializedName("response_value") var data: T
) : ApiBaseResponse() {


}
