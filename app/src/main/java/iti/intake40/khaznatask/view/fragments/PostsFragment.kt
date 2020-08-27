package iti.intake40.khaznatask.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
                    Toast.makeText(requireContext(), R.string.no_connection, Toast.LENGTH_SHORT)
                        .show()
                    noData.visibility = View.VISIBLE
                }else{
                    model.getAllPosts()
                    setUpRecycle(it)
                }

            })


    }
    fun refreshPosts(){
        itemsswipetorefresh.setColorSchemeColors(Color.WHITE)
        itemsswipetorefresh.setOnRefreshListener {
            if(!NetworkConnection.checkConnection(requireContext())){
                itemsswipetorefresh.isRefreshing = false
            }else {
                model.getAllPosts().observe(viewLifecycleOwner, Observer {
                    setUpRecycle(it)
                })

            }
            itemsswipetorefresh.isRefreshing = false
        }
    }

    override fun onClickedPost(postId: Int) {
        model.setMsgCommunicator(postId)
        model.getPostDetails(postId)
        val nextFrag = PostDetailsFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_layout, nextFrag)
            .addToBackStack(null)
            .commit()
    }
    
}