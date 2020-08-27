package iti.intake40.khaznatask.data.remoteDatabase

import javax.inject.Inject


class  RetrofitClient @Inject constructor(
    private val retrofitApi: RetrofitApi
){
    suspend fun getAllPosts() = retrofitApi.getPosts()

    suspend fun getAllDetails(postId:Int) = retrofitApi.getPostDetails(postId)
}