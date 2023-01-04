package fi.metropolia.parliamentmembersapp.memberlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.metropolia.parliamentmembersapp.network.MemberApi
import fi.metropolia.parliamentmembersapp.network.Members
import kotlinx.coroutines.launch

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Works with the member list fragment to manage the UI data. It also helps to keep the data alive when
//a screen rotation occurs

class MemberListViewModel: ViewModel() {
    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<List<Members>>()
    // The external immutable LiveData for the request status String
    val status: LiveData<List<Members>> = _status

    init {
        getMemberProperties()
    }

    private val _navigateToSelectedMember = MutableLiveData<Members?>()
    val navigateToSelectedMember: LiveData<Members?>
        get() = _navigateToSelectedMember

    fun displayMemberDetails(members: Members) {
        _navigateToSelectedMember.value = members
    }
    fun displayMemberDetailsComplete() {
        _navigateToSelectedMember.value = null
    }

    private fun getMemberProperties() {
        viewModelScope.launch{
            val listResult = MemberApi.retrofitService.getMembersList()
            Log.d("Get Member Properties", listResult.toString())
            _status.value = listResult
        }
    }
}