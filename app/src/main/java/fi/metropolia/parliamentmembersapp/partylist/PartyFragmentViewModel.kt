package fi.metropolia.parliamentmembersapp.partylist

import android.util.Log
import androidx.lifecycle.*
import fi.metropolia.parliamentmembersapp.repositories.MemberRepository
import fi.metropolia.parliamentmembersapp.network.Members
import kotlinx.coroutines.launch

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Works with the party fragment to manage the UI data. It also helps to keep the data alive when
//a screen rotation occurs

class PartyFragmentViewModel: ViewModel()  {
    lateinit var member: Members
    private val _party = MutableLiveData<List<String>>()
    val party: LiveData<List<String>>
        get() = _party

    init {
        getParties()
    }

    private fun getParties() {
        viewModelScope.launch {
            val partyList = MemberRepository.getParties()
            Log.d("Get Parties ", partyList.toString())
            _party.value = partyList
        }
    }
}