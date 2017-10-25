package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.presentation.adapters.OrdersListAdapter;
import ru.arink_group.deliveryapp.presentation.presenter.OrderPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.OrderPresenter;
import ru.arink_group.deliveryapp.presentation.view.OrderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements OrderView, OrdersListAdapter.ProductChangeListener {


    private OrderPresenter orderPresenter;
    private OrdersListAdapter ordersListAdapter;
    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);

        this.orderPresenter = new OrderPresenterImpl(this);

        RecyclerView orderRecyclerView = root.findViewById(R.id.order_recycler_view);

        this.ordersListAdapter = new OrdersListAdapter();
        this.ordersListAdapter.setProductChangeListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        orderRecyclerView.setAdapter(ordersListAdapter);

        orderRecyclerView.setLayoutManager(llm);

        orderPresenter.getProductsFromBasket();

        return root;
    }

    @Override
    public void setProducts(List<Product> products) {
        this.ordersListAdapter.setOrdersListAndNotifyAdapter(products);
    }

    @Override
    public void updateProductState(Product product) {
        ordersListAdapter.updateOrder(product);
    }

    @Override
    public void updateTotals() {

    }

    @Override
    public void showErrorMessage(String e) {
        Toast.makeText(getActivity(), e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChangeProduct(Product product) {
        orderPresenter.updateProduct(product);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        orderPresenter.destroy();
    }
}
