package iti.intake40.khaznatask.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import iti.intake40.khaznatask.data.model.PostsModel
import iti.intake40.khaznatask.repository.Repository
import kotlinx.coroutines.*
import retrofit2.HttpException
import androidx.hilt.lifecycle.ViewModelInject
class MainViewModel @ViewModelInject constructor(
    private var repo :Repository
) :  ViewModel(){

    var  postsList = MutableLiveData<List<PostsModel>>()

    fun getAllPosts(): LiveData<List<PostsModel>> {

        CoroutineScope(Dispatchers.IO).launch {
            val response = repo.getAllPosts()
            withContext(Dispatchers.Main){
                try {
                    if(response.isSuccessful){
                        if(response.body()!=null)
                            postsList.postValue(response.body())
                    }
                }catch (e: HttpException){

                }

            }
        }
        return postsList
    }

}