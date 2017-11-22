package ru.arink_group.deliveryapp.data.repository.datasource;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.dto.OrderDTO;

/**
 * Created by kirillvs on 17.11.17.
 */

public interface OrderDataStore {
    Observable<Object> sendOrderToServer(OrderDTO orderDTO);
    Observable<List<OrderDTO>> getOrders();
    Observable<Object> cancelOrder(String orderId);
}
