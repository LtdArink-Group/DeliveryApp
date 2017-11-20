package ru.arink_group.deliveryapp.domain.dao

import java.io.Serializable

/**
 * Created by kirillvs on 20.11.17.
 */
data class Order(
    val id: String,
    val status: String,
    val totalCost: Double,
    val deliveryCost: Double,
    val companyId: Int,
    val accountId: String,
    val addressId: Int,
    val deliveryTime: String,
    val pickup: Boolean,
    val products: List<OrderProduct>
): Serializable