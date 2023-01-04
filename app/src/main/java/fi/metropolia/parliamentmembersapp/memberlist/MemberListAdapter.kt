package fi.metropolia.parliamentmembersapp.memberlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fi.metropolia.parliamentmembersapp.databinding.FragmentMemberListItemBinding
import fi.metropolia.parliamentmembersapp.network.Members

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Adapter for the comments fragment. Shows the required data on the display

class MemberListAdapter(private val members: List<Members>, private val onClickListener: OnClickListener):
    RecyclerView.Adapter<MemberListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: FragmentMemberListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentMemberListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.memberListLastname.text = members[position].lastname
        holder.binding.memberListFirstname.text = members[position].firstname
        holder.binding.memberListHetekaid.text = String.format("Heteka Id: ${members[position].hetekaId}")
        holder.binding.marsImage.load("https://avoindata.eduskunta.fi/"+ members[position].pictureUrl)

        holder.itemView.setOnClickListener{
            onClickListener.onClick(members[position])
        }
    }

    override fun getItemCount(): Int {
        return members.size
    }

    open class OnClickListener(val clickListener: (members:Members) -> Unit) {
        fun onClick(members: Members) {
            clickListener(members)
            Log.d("Showing the last name:", members.lastname)
        }
    }
}