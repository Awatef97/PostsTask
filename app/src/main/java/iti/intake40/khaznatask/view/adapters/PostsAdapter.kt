package iti.intake40.khaznatask.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import iti.intake40.khaznatask.R
import iti.intake40.khaznatask.data.model.PostsModel
import iti.intake40.khaznatask.view.fragments.PostsFragment
import kotlinx.android.synthetic.main.posts_item.view.*


class PostsAdapter (private val postsList: List<PostsModel>, fragment:PostsFragment):RecyclerView.Adapter<PostsAdapter.VH>() {

    val listener=fragment as OnClickListener
    private lateinit var postsModel: PostsModel
    var  selectedPosition:Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.posts_item, parent, false))
    }

    override fun getItemCount()= postsList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
         postsModel = postsList.get(position)
        if(selectedPosition!=position) {
            holder.itemView.setOnClickListener {
                listener.onClickedPost(postsList.get(position).id)
            }
        }
        holder.itemView.post_title_txt.text = postsModel.title
        holder.itemView.post_body_txt.text = postsModel.body
    }

    interface OnClickListener {
        fun onClickedPost(postId: Int)
    }

    class VH(itemView:View) : RecyclerView.ViewHolder(itemView)
}