package com.example.mvvm.data.repos

import com.example.mvvm.data.api.Client

/**
 * Repository contains all the methods from which you get the response
 */
object GithubRepository {

    suspend fun getUsers() = Client.api.getUsers()

    suspend fun searchUsers(name: String) = Client.api.searchUser(name)

}