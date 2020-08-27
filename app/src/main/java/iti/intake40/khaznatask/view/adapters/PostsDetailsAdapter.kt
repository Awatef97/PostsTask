package iti.intake40.khaznatask.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import iti.intake40.khaznatask.R
import iti.intake40.khaznatask.data.model.PostDetailsModel
import kotlinx.android.synthetic.main.posts_details_item.view.*

class PostsDetailsAdapter (private val postDetailList: List<PostDetailsModel>): RecyclerView.Adapter<PostsDetailsAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.posts_details_item, parent, false))
    }

    override fun getItemCount(): Int {
        return postDetailList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        var postDetailsModel: PostDetailsModel = postDetailList.get(position)
        holder.itemView.comment_body_txt.text = postDetailsModel.body
        holder.itemView.name_txt.text = postDetailsModel.name
        holder.itemView.email_txt.text = postDetailsModel.email

    }
    class VH(itemView: View): RecyclerView.ViewHolder(itemView)

}