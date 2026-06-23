package com.passby.malloc.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.passby.malloc.data.local.dao.OrderDao
import com.passby.malloc.data.local.dao.ProductDao
import com.passby.malloc.data.local.dao.SlotDao
import com.passby.malloc.data.local.dao.UserDao
import com.passby.malloc.data.local.dao.VendorDao
import com.passby.malloc.data.local.entity.OrderEntity
import com.passby.malloc.data.local.entity.OrderItemEntity
import com.passby.malloc.data.local.entity.ProductEntity
import com.passby.malloc.data.local.entity.SlotEntity
import com.passby.malloc.data.local.entity.UserEntity
import com.passby.malloc.data.local.entity.VendorEntity

@Database(
    entities = [
        UserEntity::class,
        VendorEntity::class,
        ProductEntity::class,
        OrderEntity::class,
        OrderItemEntity::class,
        SlotEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(PassByTypeConverters::class)
abstract class PassByDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun vendorDao(): VendorDao
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
    abstract fun slotDao(): SlotDao
}

