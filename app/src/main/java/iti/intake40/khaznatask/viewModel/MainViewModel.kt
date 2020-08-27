package iti.intake40.khaznatask.viewModel

import android.util.Log
import iti.intake40.khaznatask.data.model.PostsModel
import iti.intake40.khaznatask.repository.Repository
import kotlinx.coroutines.*
import retrofit2.HttpException
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import iti.intake40.khaznatask.data.model.PostDetailsModel

class MainViewModel @ViewModelInject constructor(
    private var repo :Repository
) :  ViewModel(){

    var  postsList = MutableLiveData<List<PostsModel>>()
    var  postDetailList = MutableLiveData<List<PostDetailsModel>>()
    var postId =MutableLiveData<Int>()
    //val postId:LiveData<Int>
      //  get() =message

    fun getAllPosts(): LiveData<List<PostsModel>> {

        CoroutineScope(Dispatchers.IO).launch {
            val response = repo.getAllPosts()
            withContext(Dispatchers.Main){
                try {
                    if(response.isSuccessful){
                        if(response.body()!=null)
                            postsList.postValue(response.body())
                        response.body()?.let { insertPosts(it) }
                    }else{
                        Log.i("llllllllll",response.message())
                    }
                }catch (e: HttpException){
                    Log.i("llllllllll",e.message())
                }

            }
        }
        return postsList
    }
    fun getCachedPosts() = repo.getCashedPosts()

    fun insertPosts(postsModel: List<PostsModel>) = viewModelScope.launch(Dispatchers.IO) {
        repo.insertPosts(postsModel)
    }
    /////////////////////


    fun setMsgCommunicator(msg:Int) {
        postId.postValue(msg)



    }

    fun getPostDetails(postId:Int): LiveData<List<PostDetailsModel>> {

        CoroutineScope(Dispatchers.IO).launch {
            val response = repo.getAllDetails(postId)
            withContext(Dispatchers.Main){
                try {
                    if(response.isSuccessful){
                        if(response.body()!=null)
                            postDetailList.postValue(response.body())
                        response.body()?.let { insertDetail(it) }
                    }else{
                        Log.i("llllllllll",response.message())
                    }
                }catch (e: HttpException){
                    Log.i("llllllllll",e.message())
                }

            }
        }
        return postDetailList
    }
    fun insertDetail(postDetailsModel: List<PostDetailsModel>) = viewModelScope.launch(Dispatchers.IO) {
        repo.insertAllDetails(postDetailsModel)
    }
    fun getCachedDetail(postId:Int) = repo.getCashedDetails(postId)
}