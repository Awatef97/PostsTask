package iti.intake40.khaznatask.view.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

import iti.intake40.khaznatask.R
import iti.intake40.khaznatask.data.model.PostDetailsModel
import iti.intake40.khaznatask.utils.NetworkConnection
import iti.intake40.khaznatask.view.adapters.PostsDetailsAdapter
import iti.intake40.khaznatask.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_post_details.*
import kotlinx.android.synthetic.main.fragment_posts.*

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class PostDetailsFragment : Fragment() {
    val model : MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        refreshPosts()

    }
    fun setUpRecycle(list: List<PostDetailsModel>){
        val layoutManager = LinearLayoutManager(requireContext())
        postDetailRecycle.layoutManager = layoutManager
        postDetailRecycle.adapter = PostsDetailsAdapter(list)

    }

    fun setUpObservers(){
        model.postId.observe(viewLifecycleOwner, Observer { postId ->
            model.getCachedDetail(postId).observe(viewLifecycleOwner, Observer {
                if(it.isNullOrEmpty()&&!NetworkConnection.checkConnection(requireContext()))
                {
                    detailsProgressBar.visibility = View.GONE
                    no_detail_txt.visibility = View.VISIBLE
                    no_detail_img.visibility = View.VISIBLE

                }else if(!it.isNullOrEmpty()){
                    detailsProgressBar.visibility = View.GONE
                    setUpRecycle(it)
                }else{
                    model.getPostDetails(postId)
                }

            })

        })


    }

    fun refreshPosts(){
        detailswipetorefresh.setColorSchemeColors(Color.WHITE)
        detailswipetorefresh.setOnRefreshListener {
            if(!NetworkConnection.checkConnection(requireContext())){
                detailswipetorefresh.isRefreshing = false
            }else {
                model.postId.observe(viewLifecycleOwner, Observer { postId ->

                    model.getPostDetails(postId)
                    model.getPostDetails(postId).observe(viewLifecycleOwner, Observer {
                        setUpRecycle(it)
                    })
                })
            }
            detailswipetorefresh.isRefreshing = false
        }
    }
}
