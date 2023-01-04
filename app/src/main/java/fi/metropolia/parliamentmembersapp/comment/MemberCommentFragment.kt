package fi.metropolia.parliamentmembersapp.comment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fi.metropolia.parliamentmembersapp.R
import fi.metropolia.parliamentmembersapp.databinding.FragmentMemberCommentBinding

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Comment fragment that connects to the fragment_member_comment.xml

class MemberCommentFragment : Fragment() {
    private lateinit var binding: FragmentMemberCommentBinding
    private lateinit var viewModel: MemberCommentViewModel
    private lateinit var adapter: MemberCommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_member_comment, container, false)
        viewModel = ViewModelProvider(this)[MemberCommentViewModel::class.java]
        val memberId = MemberCommentFragmentArgs.fromBundle(requireArguments()).selectedMember.hetekaId

        //I had to initialise getComments here instead of the MemberCommentViewModel since I got errors
        viewModel.getComments(memberId)

        viewModel.listOfComments.observe(viewLifecycleOwner) {
            adapter = MemberCommentAdapter(it)
            binding.memberCommentRecyclerView.adapter = adapter
        }

        return binding.root
    }
}

