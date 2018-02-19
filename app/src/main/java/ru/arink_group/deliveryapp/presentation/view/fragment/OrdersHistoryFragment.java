package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.Order;
import ru.arink_group.deliveryapp.presentation.adapters.OrdersHistoryAdapter;
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnOrdersHistoryClickListener;
import ru.arink_group.deliveryapp.presentation.presenter.OrdersHistoryPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.OrdersHistoryPresenter;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.MenuView;
import ru.arink_group.deliveryapp.presentation.view.OrdersHistoryView;
import ru.arink_group.deliveryapp.presentation.view.ProgressView;
import ru.arink_group.deliveryapp.presentation.view.activity.RetryOrderActivity;

import java.util.List;

public class OrdersHistoryFragment extends Fragment implements OrdersHistoryView, OnOrdersHistoryClickListener {

    public static final String RETRY_ORDER = "RETRY ORDER";

    private Unbinder unbinder;

    ProgressView progressView;
    OrdersHistoryPresenter presenter;
    MenuView menuView;

    private OrdersHistoryAdapter activeAdapter;
    private OrdersHistoryAdapter completedAdapter;

    @BindView(R.id.order_history_active_recycler)
    RecyclerView activeRecyclerView;

    @BindView(R.id.order_history_completed_recycler)
    RecyclerView completedRecyclerView;


    public OrdersHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders_history, container, false);

        unbinder = ButterKnife.bind(this, view);

        final FabView fabView = (FabView) getActivity();
        fabView.hideOrderFab();

        AppCompatActivity titleActivity = (AppCompatActivity) getActivity();
        titleActivity.getSupportActionBar().setTitle(R.string.orders_history);

        progressView = (ProgressView) getActivity();
        menuView = (MenuView) getActivity();

        presenter = new OrdersHistoryPresenterImpl(this);

        initRecyclers();

        presenter.getOrders();

        return view;
    }

    public void initRecyclers() {
        activeAdapter = new OrdersHistoryAdapter();
        LinearLayoutManager activellm = new LinearLayoutManager(getActivity());
        activeRecyclerView.setHasFixedSize(true);
        activeRecyclerView.setLayoutManager(activellm);
        activeRecyclerView.setAdapter(activeAdapter);
        activeAdapter.setOnClickListener(this);

        completedAdapter = new OrdersHistoryAdapter(false);
        LinearLayoutManager completedllm = new LinearLayoutManager(getActivity());
        completedRecyclerView.setHasFixedSize(true);
        completedRecyclerView.setLayoutManager(completedllm);
        completedRecyclerView.setAdapter(completedAdapter);
        completedAdapter.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.destroy();
    }

    @Override
    public void loadingStart() {
        progressView.loadingStart();
    }

    @Override
    public void loadingFinish() {
        progressView.loadingFinish();
    }

    @Override
    public void setOrders(List<Order> activeOrders, List<Order> completedOrders) {
        activeAdapter.setOrders(activeOrders);
        completedAdapter.setOrders(completedOrders);
    }

    @Override
    public void showErrorMessage(String e) {
        Toast.makeText(getActivity(), e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPlaceHolder() {
        Fragment fragment = new EmptyListFragment();
        menuView.changeFragmentToPlaceHolder(fragment);
    }

    @Override
    public void showContent() {

    }

    @Override
    public void onOrderHistoryItemClick(Order order) {
        startRetryOrder(order);
    }

    private void startRetryOrder(Order order) {
        Intent intent = new Intent(getActivity(), RetryOrderActivity.class);
        intent.putExtra(RETRY_ORDER, order);
        getActivity().startActivity(intent);
    }
}
