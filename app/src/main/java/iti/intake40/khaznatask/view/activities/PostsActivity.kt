package iti.intake40.khaznatask.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import iti.intake40.khaznatask.R
import iti.intake40.khaznatask.data.model.PostsModel
import iti.intake40.khaznatask.view.adapters.PostsAdapter
import iti.intake40.khaznatask.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_posts.*
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        viewModel?.getAllPosts()?.observe(this, Observer {
            setUpViews(it)

        })
    }

    fun setUpViews(list: List<PostsModel>){
        val layoutManager = LinearLayoutManager(applicationContext)
        postsRecycle.layoutManager = layoutManager
        postsRecycle.adapter = PostsAdapter(list)
    }
}
