package iti.intake40.khaznatask.data.localDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import iti.intake40.khaznatask.data.model.PostDetailsModel
import iti.intake40.khaznatask.data.model.PostsModel

@Database(entities = [PostsModel::class,PostDetailsModel::class],version = 3,exportSchema = false)
abstract class PostsDatabase : RoomDatabase(){
    abstract fun postsDao() : PostsDao

    companion object {
        @Volatile private var instance: PostsDatabase? = null

        fun getDatabase(context: Context): PostsDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, PostsDatabase::class.java, "posts")
                .fallbackToDestructiveMigration()
                .build()
    }

}