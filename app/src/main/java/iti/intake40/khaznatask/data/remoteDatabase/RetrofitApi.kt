package iti.intake40.khaznatask.data.remoteDatabase

import iti.intake40.khaznatask.data.model.PostDetailsModel
import iti.intake40.khaznatask.data.model.PostsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostsModel>>

    @GET("comments")
    suspend fun getPostDetails(@Query("postId") postId: Int): Response<List<PostDetailsModel>>
}