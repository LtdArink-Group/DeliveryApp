package ru.arink_group.deliveryapp.domain.dao

import java.io.Serializable

/**
 * Created by kirillvs on 20.11.17.
 */
data class OrderProduct(
     val id: String,
     val productTitle: String,
     val totalCost: Double,
     val productId: Int,
     val mainOption: String,
     val qty: Int,
     val ingredients: List<OrderIngredient>
): Serializable