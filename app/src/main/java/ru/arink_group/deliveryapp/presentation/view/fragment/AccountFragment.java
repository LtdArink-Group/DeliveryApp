package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Account;
import ru.arink_group.deliveryapp.presentation.presenter.AccountPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.AccountPresenter;
import ru.arink_group.deliveryapp.presentation.view.AccountView;
import ru.arink_group.deliveryapp.presentation.view.FabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements AccountView {


    private AccountPresenter accountPresenter;

    @BindView(R.id.account_name)
    TextInputEditText accountName;

    @BindView(R.id.account_email)
    TextInputEditText accountEmail;

    @BindView(R.id.account_phone)
    TextInputEditText accountPhone;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        ButterKnife.bind(this, rootView);

        FabView menuView = (FabView) getActivity();
        menuView.showOrderFab();

        accountPresenter = new AccountPresenterImpl(this);
        accountPresenter.getAccount();

        return rootView;
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAccount(Account account) {
        accountName.setText(account.getName());
        accountEmail.setText(account.getEmail());
        accountPhone.setText(account.getPhone());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
