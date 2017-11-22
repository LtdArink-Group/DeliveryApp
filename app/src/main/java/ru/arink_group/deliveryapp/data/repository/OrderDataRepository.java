package ru.arink_group.deliveryapp.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.App;
import ru.arink_group.deliveryapp.data.repository.datasource.OrderDataStore;
import ru.arink_group.deliveryapp.domain.dto.OrderDTO;
import ru.arink_group.deliveryapp.domain.repository.OrderRepository;

/**
 * Created by kirillvs on 17.11.17.
 */

public class OrderDataRepository implements OrderRepository {

    @Inject
    OrderDataStore orderDataStore;

    public OrderDataRepository() {
        App.getComponent().inject(this);
    }


    @Override
    public Observable<Object> sendOrder(OrderDTO orderDTO) {
        return orderDataStore.sendOrderToServer(orderDTO);
    }

    @Override
    public Observable<List<OrderDTO>> getOrders() {
        return orderDataStore.getOrders();
    }

    @Override
    public Observable<Object> cancelOrder(String orderId) {
        return orderDataStore.cancelOrder(orderId);
    }
}
