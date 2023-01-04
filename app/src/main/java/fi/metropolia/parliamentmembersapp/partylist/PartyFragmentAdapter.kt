package fi.metropolia.parliamentmembersapp.partylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fi.metropolia.parliamentmembersapp.R
import fi.metropolia.parliamentmembersapp.databinding.FragmentPartyItemBinding

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Adapter for the party fragment. Shows the required data on the display

class PartyFragmentAdapter : ListAdapter<String, PartyFragmentAdapter.PartyFragmentViewHolder>(PartyDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyFragmentViewHolder {
        val binding =
            FragmentPartyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartyFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PartyFragmentViewHolder, position: Int) {
        val partyItem = getItem(position)

        holder.binding.partyName.text = when (partyItem) {
            "vihr" -> "Vihreä eduskuntaryhmä"
            "ps" -> "Perussuomalaisten eduskuntaryhmä"
            "kesk" -> "Keskustan eduskuntaryhmä"
            "kok" -> "Kokoomuksen eduskuntaryhmä"
            "sd" -> "Sosialidemokraattinen eduskuntaryhmä"
            "vas" -> "Vasemmistoliiton eduskuntaryhmä"
            "kd" -> "Kristillisdemokraattinen eduskuntaryhmä"
            "r" -> "Ruotsalainen eduskuntaryhmä"
            "vkk" -> "Eduskuntaryhmä Valta kuuluu kansalle"
            "liik" -> "Liike Nyt -eduskuntaryhmä"
            else -> "Eduskuntaryhmä Wille Rydman"
        }.toString()

        holder.binding.partyImage.setImageResource(when (partyItem) {
            "vihr" -> R.drawable.vihr
            "ps" -> R.drawable.ps
            "kesk" -> R.drawable.kesk
            "kok" -> R.drawable.kok
            "sd" -> R.drawable.sd
            "vas" -> R.drawable.vas
            "kd" -> R.drawable.kd
            "r" -> R.drawable.r
            "vkk" -> R.drawable.vkk
            "liik" -> R.drawable.liik
            else -> R.drawable.wr
        })
    }

    class PartyFragmentViewHolder(var binding: FragmentPartyItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object PartyDiffCallback: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}