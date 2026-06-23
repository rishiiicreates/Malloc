package com.passby.malloc.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.passby.malloc.data.local.entity.VendorEntity
import com.passby.malloc.data.local.entity.VerificationStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface VendorDao {
    @Query("SELECT * FROM vendors WHERE id = :id")
    fun observeById(id: String): Flow<VendorEntity?>

    @Query("SELECT * FROM vendors WHERE userId = :userId LIMIT 1")
    suspend fun findByUserId(userId: String): VendorEntity?

    @Query(
        """
        SELECT * FROM vendors
        WHERE verificationStatus = 'VERIFIED' AND isOpen = 1
        ORDER BY businessName COLLATE NOCASE
        """,
    )
    fun observeDiscoverable(): Flow<List<VendorEntity>>

    @Query(
        """
        SELECT * FROM vendors
        WHERE verificationStatus = :status
        ORDER BY businessName COLLATE NOCASE
        """,
    )
    fun observeByVerificationStatus(status: VerificationStatus): Flow<List<VendorEntity>>

    @Upsert
    suspend fun upsert(vendor: VendorEntity)

    @Query("UPDATE vendors SET isOpen = :isOpen WHERE id = :vendorId")
    suspend fun setOpen(vendorId: String, isOpen: Boolean): Int

    @Query(
        """
        UPDATE vendors
        SET verificationStatus = :status, rejectionReason = :reason
        WHERE id = :vendorId
        """,
    )
    suspend fun updateVerification(
        vendorId: String,
        status: VerificationStatus,
        reason: String?,
    ): Int
}

