package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.presentation.adapters.OrdersListAdapter;
import ru.arink_group.deliveryapp.presentation.presenter.OrderPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.OrderPresenter;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.MenuView;
import ru.arink_group.deliveryapp.presentation.view.OrderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements OrderView, OrdersListAdapter.ProductChangeListener, View.OnClickListener {


    private OrderPresenter orderPresenter;
    private OrdersListAdapter ordersListAdapter;

    @BindView(R.id.summary_self_export_switch)
    Switch selfExportSwitch;

    @BindView(R.id.summary_cost)
    TextView summaryCost;

    @BindView(R.id.summary_discount)
    TextView summaryDiscount;

    @BindView(R.id.summary_delivery)
    TextView summaryDelivery;

    @BindView(R.id.summary)
    TextView summary;

    @BindView(R.id.send_order_button)
    Button sendButton;

    @BindString(R.string.free)
    String freeString;

    @BindString(R.string.order_send_ok)
    String orderSendOkString;

    @BindString(R.string.order)
    String titleString;


    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);

        ButterKnife.bind(this, root);

        this.orderPresenter = new OrderPresenterImpl(this);

        selfExportSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderFragment.this.updateTotals();
            }
        });

        RecyclerView orderRecyclerView = root.findViewById(R.id.order_recycler_view);

        this.ordersListAdapter = new OrdersListAdapter();
        this.ordersListAdapter.setProductChangeListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        orderRecyclerView.setAdapter(ordersListAdapter);

        orderRecyclerView.setLayoutManager(llm);

        orderPresenter.getProductsFromBasket();

        sendButton.setOnClickListener(this);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle(titleString);

        FabView menuView = (FabView) getActivity();
        menuView.hideOrderFab();

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
        List<Product> products = ordersListAdapter.getOrdersList();
        double summary = 0.0;

        for (Product product : products) {
            summary += product.getTotalSelectedSum();
        }

        // TODO rework discount logic and delivey

        double discount = 0.0;

        if (selfExportSwitch.isChecked()) {
            discount = 125.0;
        }

        this.summaryCost.setText(String.valueOf(summary));

        this.summaryDiscount.setText(String.valueOf(discount));

        this.summaryDelivery.setText(freeString);

        double allSummary = summary - discount;
        allSummary = allSummary >= 0.0 ? allSummary : 0.0;

        this.summary.setText(String.valueOf(allSummary));
    }

    @Override
    public void showErrorMessage(String e) {
        Toast.makeText(getActivity(), e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSendingOrderOk() {
        Toast.makeText(getActivity(), orderSendOkString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPlaceholder() {

    }

    @Override
    public List<Product> getListProducts() {
        return ordersListAdapter.getOrdersList();
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

    @Override
    public void onClick(View v) {
        if(v.getId() == sendButton.getId()) orderPresenter.sendOrderToServer();
    }
}
