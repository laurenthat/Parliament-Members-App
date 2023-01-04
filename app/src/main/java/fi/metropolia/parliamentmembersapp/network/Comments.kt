package fi.metropolia.parliamentmembersapp.network

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import java.util.*

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Comments data class as well as its DAO. Data class is parceled in order to be able to pass
//the data to multiple fragments.

@Entity
@Parcelize
data class Comments (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val hetekaId: Int,
    val timestamp: Date?,
    val comment: String,
    val up: Int,
    val down: Int
): Parcelable

@Dao
interface CommentsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comments: Comments)

    @Query("select * from Comments")
    fun getAll(): LiveData<List<Comments>>

    @Query("select count(:id) from Comments where hetekaId = :id and up is 1")
    fun getUpValue(id: Int): LiveData<Int>

    @Query("select count(:id) from Comments where hetekaId = :id and down is 1")
    fun getDownValue(id: Int): LiveData<Int>

    @Query("select * from Comments where hetekaId = :id and comment <> ''")
    fun getComments(id: Int): List<Comments>
}