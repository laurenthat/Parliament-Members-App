package fi.metropolia.parliamentmembersapp.network

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Members data class as well as its DAO.

@Entity
@Parcelize
data class Members (
    @PrimaryKey
    val hetekaId: Int,
    val seatNumber: Int,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean,
    val pictureUrl: String
) : Parcelable

@Dao
interface MembersDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(members: Members)

    @Query("select distinct party from Members")
    fun getAllParties(): List<String>
}