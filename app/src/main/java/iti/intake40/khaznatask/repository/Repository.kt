package iti.intake40.khaznatask.repository
import iti.intake40.khaznatask.data.remoteDatabase.RetrofitClient
import javax.inject.Inject


class Repository @Inject constructor(
    val retrofitClient: RetrofitClient

){
    suspend fun getAllPosts() = retrofitClient.getAllPosts()


}