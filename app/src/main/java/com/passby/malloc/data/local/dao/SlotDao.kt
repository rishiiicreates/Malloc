package com.passby.malloc.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.passby.malloc.data.local.entity.SlotEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SlotDao {
    @Query(
        """
        SELECT * FROM slots
        WHERE vendorId = :vendorId
        ORDER BY date ASC, startTime ASC
        """,
    )
    fun observeForVendor(vendorId: String): Flow<List<SlotEntity>>

    @Query(
        """
        SELECT * FROM slots
        WHERE vendorId = :vendorId
          AND date = :date
          AND isBlocked = 0
          AND booked < maxOrders
        ORDER BY startTime ASC
        """,
    )
    fun observeAvailable(vendorId: String, date: String): Flow<List<SlotEntity>>

    @Upsert
    suspend fun upsert(slot: SlotEntity)

    @Query("UPDATE slots SET isBlocked = :blocked WHERE id = :slotId")
    suspend fun setBlocked(slotId: String, blocked: Boolean): Int

    @Query(
        """
        UPDATE slots
        SET booked = booked + 1
        WHERE id = :slotId AND isBlocked = 0 AND booked < maxOrders
        """,
    )
    suspend fun reserve(slotId: String): Int

    @Delete
    suspend fun delete(slot: SlotEntity)
}

