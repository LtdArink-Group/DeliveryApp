package ru.arink_group.deliveryapp.data.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.arink_group.deliveryapp.domain.dto.OrderDTO;

/**
 * Created by kirillvs on 20.11.17.
 */

public class OrdersObject {

    @SerializedName("orders")
    @Expose
    private List<OrderDTO> orders = null;

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
