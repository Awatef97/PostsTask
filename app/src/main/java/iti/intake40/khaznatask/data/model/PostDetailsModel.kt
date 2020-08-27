package iti.intake40.khaznatask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class PostDetailsModel(
    var postId : Int,
    @PrimaryKey
    var id : Int,
    var name : String,
    var email : String,
    var body : String
)