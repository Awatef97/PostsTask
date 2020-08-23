package iti.intake40.khaznatask.data.remoteDatabase

import iti.intake40.khaznatask.data.model.PostsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostsModel>>
}