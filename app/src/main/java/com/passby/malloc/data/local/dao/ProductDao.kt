package com.passby.malloc.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.passby.malloc.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE id = :id")
    fun observeById(id: String): Flow<ProductEntity?>

    @Query("SELECT * FROM products WHERE vendorId = :vendorId ORDER BY name COLLATE NOCASE")
    fun observeForVendor(vendorId: String): Flow<List<ProductEntity>>

    @Query(
        """
        SELECT * FROM products
        WHERE vendorId = :vendorId AND isAvailable = 1 AND stock > 0
        ORDER BY name COLLATE NOCASE
        """,
    )
    fun observeAvailableForVendor(vendorId: String): Flow<List<ProductEntity>>

    @Upsert
    suspend fun upsert(product: ProductEntity)

    @Delete
    suspend fun delete(product: ProductEntity)
}

