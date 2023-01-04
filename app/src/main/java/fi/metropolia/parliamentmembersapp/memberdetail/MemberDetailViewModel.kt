package fi.metropolia.parliamentmembersapp.memberdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.metropolia.parliamentmembersapp.repositories.CommentRepository
import fi.metropolia.parliamentmembersapp.network.Comments
import fi.metropolia.parliamentmembersapp.network.Members
import kotlinx.coroutines.launch
import java.util.*

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Works with the member detail fragment fragment to manage the UI data. It also helps to keep the data alive when
//a screen rotation occurs

class MemberDetailViewModel : ViewModel() {
    lateinit var member: Members
    lateinit var comments: Comments

    fun addComment(id: Int, hetekaId: Int, timestamp: Date?, comment: String, up: Int, down: Int) {
        viewModelScope.launch {
            CommentRepository.addComment(id,hetekaId, timestamp, comment, up, down)
        }
    }

    val upVotesCount: LiveData<Int>
        get()= CommentRepository.getUpValue(member.hetekaId)

    val downVotesCount: LiveData<Int>
        get()= CommentRepository.getDownValue(member.hetekaId)

    private val _navigateToListOfComments = MutableLiveData<Members?>()

    fun displayMemberCommentsComplete() {
        _navigateToListOfComments.value = null
    }
}