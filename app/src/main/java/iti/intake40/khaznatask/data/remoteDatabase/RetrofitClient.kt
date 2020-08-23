package iti.intake40.khaznatask.data.remoteDatabase

import javax.inject.Inject


class  RetrofitClient @Inject constructor(
    private val retrofitApi: RetrofitApi
){
    suspend fun getAllPosts() = retrofitApi.getPosts()
//    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
//
//    val getClint:RetrofitApi
//        get() {
//            val retrofit=Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(OkHttpClient())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//            return retrofit.create(RetrofitApi::class.java)
//
//        }
}