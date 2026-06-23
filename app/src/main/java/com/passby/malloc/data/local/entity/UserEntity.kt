package com.passby.malloc.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["phone"], unique = true)],
)
data class UserEntity(
    @PrimaryKey val id: String,
    val phone: String,
    val name: String,
    val role: UserRole,
    val loyaltyPoints: Long = 0,
    val createdAt: Long,
)

