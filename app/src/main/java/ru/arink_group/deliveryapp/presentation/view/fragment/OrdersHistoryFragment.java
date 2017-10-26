package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.MenuView;

import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 */
public class OrdersHistoryFragment extends Fragment {

    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    private PlusOneButton mPlusOneButton;


    public OrdersHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders_history, container, false);

        //Find the +1 button
        mPlusOneButton = (PlusOneButton) view.findViewById(R.id.plus_one_button);

        FabView menuView = (FabView) getActivity();
        menuView.showOrderFab();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
        mPlusOneButton.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE);
    }


}
