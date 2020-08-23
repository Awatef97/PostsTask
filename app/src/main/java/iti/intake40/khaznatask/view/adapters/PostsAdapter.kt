package iti.intake40.khaznatask.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import iti.intake40.khaznatask.R
import iti.intake40.khaznatask.data.model.PostsModel
import kotlinx.android.synthetic.main.posts_item.view.*

class PostsAdapter (private val list: List<PostsModel>):RecyclerView.Adapter<PostsAdapter.VH>(){
    class VH(itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.posts_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val postsModel = list.get(position)
        holder.itemView.post_title_txt.text = postsModel.title
        holder.itemView.post_body_txt.text = postsModel.body
    }
}