package ru.arink_group.deliveryapp.domain.dao

import java.io.Serializable

/**
 * Created by kirillvs on 31.10.17.
 */
data class Address(
        var id: Int?,
        var title: String = "",
        var city: String = "Хабаровск",
        var street: String = "",
        var house: String = "",
        var office: String? = "",
        var floor: String? = "",
        var entrance: String? = "",
        var code: String? = ""
) : Serializable