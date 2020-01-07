package com.abhay.taskapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhay.taskapp.R
import com.abhay.taskapp.models.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_item.view.*


class UserAdapter(val users: List<User>) : RecyclerView.Adapter<UserAdapter.UsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.view.age.text = users[position].age
        holder.view.name.text = users[position].name
        holder.view.location.text = users[position].location

        Picasso.get().load(users[position].url).into(holder.view.user_img)
    }


    class UsersViewHolder(val view: View) : RecyclerView.ViewHolder(view)


}