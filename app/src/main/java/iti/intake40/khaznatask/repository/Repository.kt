package iti.intake40.khaznatask.repository
import iti.intake40.khaznatask.data.localDatabase.PostsDao
import iti.intake40.khaznatask.data.model.PostDetailsModel
import iti.intake40.khaznatask.data.model.PostsModel
import iti.intake40.khaznatask.data.remoteDatabase.RetrofitClient
import javax.inject.Inject


class Repository @Inject constructor(
    val retrofitClient: RetrofitClient,
    val postsDao: PostsDao

){
    //remote
    suspend fun getAllPosts() = retrofitClient.getAllPosts()
    suspend fun getAllDetails(postId:Int) = retrofitClient.getAllDetails(postId)
    //local
    suspend fun insertPosts(postsModel: List<PostsModel>) = postsDao.insertPosts(postsModel)
     fun getCashedPosts() = postsDao.getCashedPosts()
    suspend fun insertAllDetails(postDetailsModel: List<PostDetailsModel>) = postsDao.insertPostsDetails(postDetailsModel)
    fun  getCashedDetails(postId:Int) = postsDao.getCashedPostsDetails(postId)
}