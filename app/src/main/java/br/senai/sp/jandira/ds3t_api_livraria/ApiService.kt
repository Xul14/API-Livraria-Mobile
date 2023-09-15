package br.senai.sp.jandira.ds3t_api_livraria

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    //chamada GET
    @GET("api/users/{id}")
    suspend fun getUserByID(@Path("id") id: String): Response<BaseResponse<CategoryResponse>>

    //chamada post
    @POST("/categoria/cadastrarCategoria")
    suspend fun createCategory(@Body body: JsonObject): Response<JsonObject>

    //chamada put precisa passar o id e o body da requisicao
    @PUT("api/users/{id}")
    suspend fun updateUser(
        @Path("id") id: String,
        @Body body: JsonObject
    ): Response<JsonObject>

    @DELETE("api/users/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<JsonObject>
}
