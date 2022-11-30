package com.example.mvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.data.models.UserModelItem
import com.example.mvvm.ui.adapter.UsersAdapter
import com.example.mvvm.ui.viewmodel.GithubViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // connecting activity to viewModel
    val vm by lazy {
        ViewModelProvider(this).get(GithubViewModel::class.java)
    }
    val list = arrayListOf<UserModelItem>()
    val adapter = UsersAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user_rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        search_view.isSubmitButtonEnabled = true
        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    findUsers(it)
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

        })

        vm.fetchUsers()

        vm.users.observe(this, Observer {
            if (it.contentLength() > 0) {
                list.addAll(it as List<UserModelItem>)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun findUsers(query: String) {
        vm.searchUsers(query)
//        vm.searchUsers(query)(this, Observer {})
        vm.searchedUsers.observe(this, Observer {
            if (it.contentLength() > 0) {
                list.addAll(it as List<UserModelItem>)
                adapter.notifyDataSetChanged()
            }
        })
    }
}