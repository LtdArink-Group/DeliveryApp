package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.MenuView;

import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 */
public class OrdersHistoryFragment extends Fragment {

    private Unbinder unbinder;


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
        fabView.showOrderFab();

        fabView.getFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderFragment orderFragment = new OrderFragment();
                fabView.changeFragment(orderFragment);
            }
        });

        AppCompatActivity titleActivity = (AppCompatActivity) getActivity();
        titleActivity.getSupportActionBar().setTitle(R.string.orders_history);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
