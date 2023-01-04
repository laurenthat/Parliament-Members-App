package fi.metropolia.parliamentmembersapp.repositories

import androidx.lifecycle.LiveData
import fi.metropolia.parliamentmembersapp.network.MembersDatabase
import fi.metropolia.parliamentmembersapp.network.Comments
import java.util.*

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//The repository sits between the app and the database and is used for easier access of data.

object CommentRepository {
    suspend fun addComment(id: Int, hetekaId: Int, timestamp: Date?, comment: String, up: Int, down: Int) {
        MembersDatabase.getInstance()
            .commentsDAO
            .insert(Comments(id, hetekaId, timestamp, comment, up, down))
    }

    fun getMemberComments(id: Int) : List<Comments?> = MembersDatabase.getInstance().commentsDAO.getComments(id)
    fun getUpValue(id: Int): LiveData<Int> = MembersDatabase.getInstance().commentsDAO.getUpValue(id)
    fun getDownValue(id: Int): LiveData<Int> = MembersDatabase.getInstance().commentsDAO.getDownValue(id)
}

