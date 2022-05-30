package io.vaiyo.domain.model.base;

import com.google.gson.annotations.SerializedName;


class ApiListResponse<T>(
    @SerializedName("response_value") var data: MutableList<T>
) : ApiBaseResponse() {


}