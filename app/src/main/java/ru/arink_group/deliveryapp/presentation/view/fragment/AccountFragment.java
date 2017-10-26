package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.MenuView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FabView menuView = (FabView) getActivity();
        menuView.showOrderFab();

        return inflater.inflate(R.layout.fragment_account, container, false);
    }

}
