package fi.metropolia.parliamentmembersapp.network

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fi.metropolia.parliamentmembersapp.Converters
import fi.metropolia.parliamentmembersapp.MyApp

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
//Database class with its 2 entities.
//TypeConverters class is also added sine the timestamp property of type Date?
//used in the Comments needed to be converted in order to be readable in the fragment.

@TypeConverters(Converters::class)
@Database(entities = [Members::class, Comments::class], version = 9, exportSchema = false)
abstract class MembersDatabase: RoomDatabase() {
    abstract val membersDAO: MembersDAO
    abstract val commentsDAO: CommentsDAO
    companion object {

        @Volatile
        private var INSTANCE: MembersDatabase? = null

        // .allowMainThreadQueries() is used since I get an error from UI thread
        fun getInstance(): MembersDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance =  Room.databaseBuilder(
                        MyApp.appContext,
                        MembersDatabase::class.java, "members_database")
                        .fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}