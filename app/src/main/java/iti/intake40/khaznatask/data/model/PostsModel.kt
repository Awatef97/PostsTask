package iti.intake40.khaznatask.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class PostsModel(
    var id:Int,
    var title:String,
    var body:String
)