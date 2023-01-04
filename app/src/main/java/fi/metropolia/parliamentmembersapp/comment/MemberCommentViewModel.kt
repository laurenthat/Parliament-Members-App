package fi.metropolia.parliamentmembersapp.comment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.metropolia.parliamentmembersapp.network.Comments
import fi.metropolia.parliamentmembersapp.network.Members
import fi.metropolia.parliamentmembersapp.repositories.CommentRepository
import kotlinx.coroutines.launch

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Works with the comment fragment to manage the UI data. It also helps to keep the data alive when
//a screen rotation occurs

class MemberCommentViewModel : ViewModel() {
    lateinit var comments: Comments
    lateinit var members: Members
    private val _listOfComments = MutableLiveData<List<Comments?>>()
    val listOfComments: LiveData<List<Comments?>>
        get() = _listOfComments

    fun getComments(memberId: Int) {
        viewModelScope.launch {
            val commentList = CommentRepository.getMemberComments(memberId)
            Log.d("Get Comments ", commentList.toString())
            _listOfComments.value = commentList
        }
    }
}
