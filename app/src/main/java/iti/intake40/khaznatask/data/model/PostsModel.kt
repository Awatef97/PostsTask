package iti.intake40.khaznatask.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "posts")
data class PostsModel(
    var userId:Int,
    @PrimaryKey
    var id:Int,
    var title:String,
    var body:String
)