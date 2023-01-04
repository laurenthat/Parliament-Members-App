package fi.metropolia.parliamentmembersapp.memberdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import fi.metropolia.parliamentmembersapp.R
import fi.metropolia.parliamentmembersapp.databinding.FragmentMemberDetailBinding
import java.util.*

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Member detail fragment that connects to the fragment_member_detail.xml

class MemberDetailFragment: Fragment() {
    private lateinit var viewModel: MemberDetailViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = FragmentMemberDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this


        viewModel = ViewModelProvider(this)[MemberDetailViewModel::class.java]
        viewModel.member = MemberDetailFragmentArgs.fromBundle(requireArguments()).selectedMember

        //I'm using String.format since I was getting error: "do not concatenate text
        // displayed with settext. use resource string with placeholders"
        binding.textViewName.text = String.format("${viewModel.member.firstname} ${viewModel.member.lastname}")

        //I'm formatting the short party names that are present in the database
        // with the long party names
        binding.textViewParty.text  = when (viewModel.member.party) {
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
        }

        //here I'm assigning to the short party names its corresponding drawable icon
        binding.mainPartyImage.setImageResource(when (viewModel.member.party) {
            "vihr" -> (R.drawable.vihr)
            "ps" -> (R.drawable.ps)
            "kesk" -> (R.drawable.kesk)
            "kok" -> (R.drawable.kok)
            "sd" -> (R.drawable.sd)
            "vas" -> (R.drawable.vas)
            "kd" -> (R.drawable.kd)
            "r" -> (R.drawable.r)
            "vkk" -> (R.drawable.vkk)
            "liik" -> (R.drawable.liik)
            else -> (R.drawable.wr)
        })

        binding.textViewHetekaId.text = viewModel.member.hetekaId.toString()
        binding.textViewSeatNumber.text = viewModel.member.seatNumber.toString()
        binding.mainPhotoImage.load("https://avoindata.eduskunta.fi/"+ viewModel.member.pictureUrl)

        //needed to recognise the hetekaId when adding comments to the database
        val hetekaMember = this.viewModel.member.hetekaId

        //adding comments
        binding.buttonSubmitComment.setOnClickListener {
            if(binding.commentBox.text?.isNotEmpty() == true) {
                val addComment = binding.commentBox.text.toString()
                val timestamp = Date()
                viewModel.addComment(0, hetekaMember, timestamp, addComment, 0, 0)
                Toast.makeText(requireContext().applicationContext, "Comment added.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext().applicationContext, "Add a comment first!", Toast.LENGTH_SHORT).show()
            }
        }

        //thumbs up button implementation
        binding.thumbsUp.setOnClickListener{
            val addComment = ""
            val timestamp = Date()
            viewModel.addComment(0, hetekaMember, timestamp, addComment, 1, 0)
        }

        //showing amount of likes next to the icon
        viewModel.upVotesCount.observe(viewLifecycleOwner) {
            binding.thumbsUpValue.text = it.toString()
        }

        //showing amount of likes next to the icon
        viewModel.downVotesCount.observe(viewLifecycleOwner) {
            binding.thumbsDownValue.text = it.toString()
        }

        //thumbs up button implementation
        binding.thumbsDown.setOnClickListener{
            val addComment = ""
            val timestamp = Date()
            viewModel.addComment(0, hetekaMember, timestamp, addComment, 0,  1)
        }

        binding.buttonShowComments.setOnClickListener {
            this.findNavController().navigate(
                MemberDetailFragmentDirections.actionMemberDetailFragmentToMemberCommentFragment(
                    viewModel.member
                )
            )
            viewModel.displayMemberCommentsComplete()
        }
        return binding.root
    }
}
