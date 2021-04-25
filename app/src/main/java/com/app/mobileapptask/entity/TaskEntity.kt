package com.app.mobileapptask.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    val taskName : String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}