package iti.intake40.khaznatask.data.localDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iti.intake40.khaznatask.data.model.PostDetailsModel
import iti.intake40.khaznatask.data.model.PostsModel

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(postsModel: List<PostsModel>)

    @Query("SELECT * FROM posts  ")
     fun getCashedPosts():LiveData<List<PostsModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostsDetails(postDetails: List<PostDetailsModel>)

    @Query("SELECT * FROM comments WHERE postId = :postId ")
    fun getCashedPostsDetails(postId:Int):LiveData<List<PostDetailsModel>>

}