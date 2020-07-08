package jp.kosuke.kinoshita.roomapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    var name: String,

    @ColumnInfo(name = "nenrei")
    var age: Int
)