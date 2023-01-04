package fi.metropolia.parliamentmembersapp.memberlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import fi.metropolia.parliamentmembersapp.databinding.FragmentMemberListBinding

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Member list fragment that connects to the fragment_member_list.xml

class MemberListFragment: Fragment() {
    private val viewModel: MemberListViewModel by viewModels()
    private lateinit var adapter: MemberListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMemberListBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the MemberListViewModel
        binding.viewModel = viewModel

        viewModel.status.observe(viewLifecycleOwner) { response ->
            adapter = MemberListAdapter(response, MemberListAdapter.OnClickListener{
                viewModel.displayMemberDetails(it)
            })
            binding.memberListRecyclerView.adapter = adapter
        }

        viewModel.navigateToSelectedMember.observe(viewLifecycleOwner) {
            if(null != it) {
                this.findNavController().navigate(MemberListFragmentDirections.actionMemberListFragmentToMemberDetailFragment(it))
                viewModel.displayMemberDetailsComplete()
            }
        }
        return binding.root
    }
}