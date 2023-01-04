package fi.metropolia.parliamentmembersapp.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fi.metropolia.parliamentmembersapp.databinding.FragmentMemberCommentItemBinding
import fi.metropolia.parliamentmembersapp.network.Comments

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Adapter for the comments fragment. Shows the required data on the display

class MemberCommentAdapter(private val comments: List<Comments?>):
    RecyclerView.Adapter<MemberCommentAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: FragmentMemberCommentItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentMemberCommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.memberComment.text = comments[position]?.comment
        holder.binding.memberCommentDate.text = comments[position]?.timestamp.toString()
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}
