package ru.arink_group.deliveryapp.domain.dao

/**
 * Created by kirillvs on 31.10.17.
 */
data class Account(
        var id: String?,
        var name: String = "",
        var email: String = "",
        var phone: String = "",
        var addresses: List<Address>? = arrayListOf(Address(null))
)