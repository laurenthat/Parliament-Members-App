package fi.metropolia.parliamentmembersapp.partylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fi.metropolia.parliamentmembersapp.databinding.FragmentPartyBinding

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Party fragment that connects to the fragment_party.xml

class PartyFragment : Fragment() {
    private lateinit var viewModel: PartyFragmentViewModel
    private lateinit var adapter: PartyFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPartyBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[PartyFragmentViewModel::class.java]

        //both lines are needed because I get the error "lateinit property adapter
        // has not been initialized"
        requireContext().applicationContext
        adapter = PartyFragmentAdapter()

        binding.photosGrid.adapter = adapter

        viewModel.party.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        return binding.root
    }
}