package com.passby.malloc.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.passby.malloc.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :id")
    fun observeById(id: String): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE phone = :phone LIMIT 1")
    suspend fun findByPhone(phone: String): UserEntity?

    @Upsert
    suspend fun upsert(user: UserEntity)

    @Query(
        """
        UPDATE users
        SET loyaltyPoints = MAX(0, loyaltyPoints + :delta)
        WHERE id = :userId
        """,
    )
    suspend fun adjustLoyaltyPoints(userId: String, delta: Long): Int
}

