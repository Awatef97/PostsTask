package iti.intake40.khaznatask.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import iti.intake40.khaznatask.R
import iti.intake40.khaznatask.data.model.PostsModel
import iti.intake40.khaznatask.utils.NetworkConnection
import iti.intake40.khaznatask.view.adapters.PostsAdapter
import iti.intake40.khaznatask.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_posts.*


@AndroidEntryPoint
class PostsFragment : Fragment(),PostsAdapter.OnClickListener {
    val model : MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       setUpObservers()
        refreshPosts()

    }

    fun setUpRecycle(list: List<PostsModel>){
        val layoutManager = LinearLayoutManager(requireContext())
        postsRecycle.layoutManager = layoutManager
        postsRecycle.adapter = PostsAdapter(list,this)
    }

    fun setUpObservers(){

            model.getCachedPosts().observe(viewLifecycleOwner, Observer {
                if(it.isNullOrEmpty()&&!NetworkConnection.checkConnection(requireContext()))
                {
                    postsProgressBar.visibility = View.GONE
                    no_date_img.visibility = View.VISIBLE
                    no_data_txt.visibility = View.VISIBLE
                }else if(!it.isNullOrEmpty()){
                    postsProgressBar.visibility = View.GONE
                    setUpRecycle(it)
                }else{
                    model.getAllPosts()
                }

            })


    }
    fun refreshPosts(){
        posts_referesh.setColorSchemeColors(Color.WHITE)
        posts_referesh.setOnRefreshListener {
            if(!NetworkConnection.checkConnection(requireContext())){
                posts_referesh.isRefreshing = false
            }else {
                model.getAllPosts()
                model.getCachedPosts().observe(viewLifecycleOwner, Observer {
                    setUpRecycle(it)
                })

            }
            posts_referesh.isRefreshing = false
        }
    }

    override fun onClickedPost(postId: Int) {
        model.setMsgCommunicator(postId)
        val nextFrag = PostDetailsFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_layout, nextFrag)
            .addToBackStack(null)
            .commit()
    }
    
}