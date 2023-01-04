package fi.metropolia.parliamentmembersapp

import androidx.room.TypeConverter
import java.util.*

//Date: 10.10.2022
//Laurentiu Sebastian Hategan
//Student id: 2112621
// Class was created to convert the timestamp property used in the Comments entity database
// in order to insert and retrieve date and time in the application.

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    @TypeConverter
    fun toTimestamp(value: Date?): Long? {
        return value?.let { value.time }
    }
}