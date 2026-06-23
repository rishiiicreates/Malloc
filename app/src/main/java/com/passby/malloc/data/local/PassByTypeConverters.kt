package com.passby.malloc.data.local

import androidx.room.TypeConverter
import com.passby.malloc.data.local.entity.OrderStatus
import com.passby.malloc.data.local.entity.PaymentStatus
import com.passby.malloc.data.local.entity.PayoutMethod
import com.passby.malloc.data.local.entity.PickupType
import com.passby.malloc.data.local.entity.UserRole
import com.passby.malloc.data.local.entity.VerificationStatus
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class PassByTypeConverters {
    private val imagesAdapter: JsonAdapter<List<String>> = Moshi.Builder()
        .build()
        .adapter(Types.newParameterizedType(List::class.java, String::class.java))

    @TypeConverter
    fun imagesToJson(value: List<String>): String = imagesAdapter.toJson(value)

    @TypeConverter
    fun jsonToImages(value: String): List<String> = imagesAdapter.fromJson(value).orEmpty()

    @TypeConverter
    fun roleToString(value: UserRole): String = value.name

    @TypeConverter
    fun stringToRole(value: String): UserRole = UserRole.valueOf(value)

    @TypeConverter
    fun payoutMethodToString(value: PayoutMethod?): String? = value?.name

    @TypeConverter
    fun stringToPayoutMethod(value: String?): PayoutMethod? = value?.let(PayoutMethod::valueOf)

    @TypeConverter
    fun verificationStatusToString(value: VerificationStatus): String = value.name

    @TypeConverter
    fun stringToVerificationStatus(value: String): VerificationStatus =
        VerificationStatus.valueOf(value)

    @TypeConverter
    fun orderStatusToString(value: OrderStatus): String = value.name

    @TypeConverter
    fun stringToOrderStatus(value: String): OrderStatus = OrderStatus.valueOf(value)

    @TypeConverter
    fun paymentStatusToString(value: PaymentStatus): String = value.name

    @TypeConverter
    fun stringToPaymentStatus(value: String): PaymentStatus = PaymentStatus.valueOf(value)

    @TypeConverter
    fun pickupTypeToString(value: PickupType): String = value.name

    @TypeConverter
    fun stringToPickupType(value: String): PickupType = PickupType.valueOf(value)
}

