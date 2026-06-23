package com.passby.malloc.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.passby.malloc.data.local.entity.OrderEntity
import com.passby.malloc.data.local.entity.OrderItemEntity
import com.passby.malloc.data.local.entity.OrderStatus
import kotlinx.coroutines.flow.Flow

@Dao
abstract class OrderDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    protected abstract suspend fun insertOrder(order: OrderEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    protected abstract suspend fun insertItems(items: List<OrderItemEntity>)

    @Transaction
    open suspend fun createOrder(order: OrderEntity, items: List<OrderItemEntity>) {
        require(items.isNotEmpty()) { "An order must contain at least one item." }
        require(order.orderPin.length == 6 && order.orderPin.all(Char::isDigit)) {
            "Handshake PIN must contain exactly six digits."
        }
        insertOrder(order)
        insertItems(items)
    }

    @Query("SELECT * FROM orders WHERE id = :id")
    abstract fun observeById(id: String): Flow<OrderEntity?>

    @Query("SELECT * FROM order_items WHERE orderId = :orderId")
    abstract fun observeItems(orderId: String): Flow<List<OrderItemEntity>>

    @Query("SELECT * FROM orders WHERE customerId = :customerId ORDER BY rowid DESC")
    abstract fun observeForCustomer(customerId: String): Flow<List<OrderEntity>>

    @Query(
        """
        SELECT * FROM orders
        WHERE vendorId = :vendorId AND status IN ('PENDING', 'ACCEPTED', 'READY')
        ORDER BY rowid ASC
        """,
    )
    abstract fun observeActiveForVendor(vendorId: String): Flow<List<OrderEntity>>

    @Query("UPDATE orders SET status = :status WHERE id = :orderId")
    abstract suspend fun updateStatus(orderId: String, status: OrderStatus): Int

    @Query(
        """
        UPDATE orders
        SET status = 'COMPLETED'
        WHERE id = :orderId AND status = 'READY' AND orderPin = :pin
        """,
    )
    abstract suspend fun completeWithPin(orderId: String, pin: String): Int
}

