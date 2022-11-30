package com.example.mvvm.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.data.models.UserModelItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*


class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: UserModelItem) = with(itemView) {
        usernameTv.text = item.login
        Picasso.get().load(item.avatarUrl).into(user_img_view)
    }
}