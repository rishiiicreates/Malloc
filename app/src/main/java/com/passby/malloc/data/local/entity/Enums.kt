package com.passby.malloc.data.local.entity

enum class UserRole { CUSTOMER, VENDOR, BOTH, ADMIN }

enum class PayoutMethod { UPI, BANK }

enum class VerificationStatus { PENDING, VERIFIED, REJECTED }

enum class OrderStatus { PENDING, ACCEPTED, READY, COMPLETED, CANCELLED }

enum class PaymentStatus { PENDING, PAID }

enum class PickupType { NOW, PREBOOKED }

