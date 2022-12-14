package com.example.mvvm.data.models

data class SearchResponse(
	val totalCount: Int? = null,
	val incompleteResults: Boolean? = null,
	val items: List<UserModelItem?>? = null
)

