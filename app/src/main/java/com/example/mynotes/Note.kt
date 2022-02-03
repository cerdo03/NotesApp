package com.example.mynotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="notes_table")
data class Note( @ColumnInfo(name="text")var text :String , @ColumnInfo(name="Heading") var heading:String):Serializable {
@PrimaryKey(autoGenerate = true) var id =0
}