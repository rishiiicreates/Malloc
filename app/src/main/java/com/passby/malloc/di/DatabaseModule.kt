package com.passby.malloc.di

import android.content.Context
import androidx.room.Room
import com.passby.malloc.data.local.PassByDatabase
import com.passby.malloc.data.local.dao.OrderDao
import com.passby.malloc.data.local.dao.ProductDao
import com.passby.malloc.data.local.dao.SlotDao
import com.passby.malloc.data.local.dao.UserDao
import com.passby.malloc.data.local.dao.VendorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PassByDatabase =
        Room.databaseBuilder(
            context,
            PassByDatabase::class.java,
            "passby.db",
        ).build()

    @Provides
    fun provideUserDao(database: PassByDatabase): UserDao = database.userDao()

    @Provides
    fun provideVendorDao(database: PassByDatabase): VendorDao = database.vendorDao()

    @Provides
    fun provideProductDao(database: PassByDatabase): ProductDao = database.productDao()

    @Provides
    fun provideOrderDao(database: PassByDatabase): OrderDao = database.orderDao()

    @Provides
    fun provideSlotDao(database: PassByDatabase): SlotDao = database.slotDao()
}

