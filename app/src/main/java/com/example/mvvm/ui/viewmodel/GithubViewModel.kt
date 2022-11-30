package com.example.mvvm.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.models.UserModelItem
import com.example.mvvm.data.repos.GithubRepository
import kotlinx.coroutines.*
import okhttp3.ResponseBody

/**
 * One thing that I like about ViewModel is that it has an inbuilt support for coroutines
 */
class GithubViewModel : ViewModel() {

    val users = MutableLiveData<ResponseBody>()
    fun fetchUsers() {
        // instead of writing this again and again, we can use an extended function for this
        viewModelScope.launch() {
            val response = withContext(Dispatchers.IO) {
                GithubRepository.getUsers()
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    users.postValue(it) // does everything on default thread
//                    users.value = it // does everything on main thread
                }
            }
        }
    }

    val searchedUsers = MutableLiveData<ResponseBody>()
    fun searchUsers(name: String) {
        viewModelScope.launch() {
            val response = withContext(Dispatchers.IO) {
                GithubRepository.searchUsers(name)
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    searchedUsers.postValue(it)
                }
            }
        }
    }

//    not working
//    fun searchUsers(name: String) = liveData<Dispatchers.IO> {
//        val response = withContext(Dispatchers.IO) {
//            GithubRepository.searchUsers(name)
//        }
//        if (response.isSuccessful) {
//            response.body()?.let {
//                emit(it.items)
//            }
//        }
//    }

//    runIO(Dispatchers.IO) {
//
//    }
//
//    runIO2() {
//
//    }
}

/**
 * Writing an extended function for [ViewModel] scope
 */
// if you want to write a simple function, write it like:
// fun ViewModel.runIO(function: () -> Unit)
fun ViewModel.runIO(
    dispatchers: CoroutineDispatcher = Dispatchers.IO,
    function: suspend CoroutineScope.() -> Unit) { // higher order suspended function of type Coroutine
    viewModelScope.launch(dispatchers) {
        function()
    }
}

fun ViewModel.runIO2(
    function: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        function()
    }
}