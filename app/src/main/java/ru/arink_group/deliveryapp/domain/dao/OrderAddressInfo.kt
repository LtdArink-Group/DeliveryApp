package ru.arink_group.deliveryapp.domain.dao

import java.io.Serializable

/**
 * Created by kirillvs on 21.11.17.
 */
data class OrderAddressInfo(
    val id: Int,
    val city: String,
    val code: String?,
    val floor: String?,
    val house: String,
    val title: String,
    val office: String?,
    val street: String,
    val entrance: String?
): Serializable