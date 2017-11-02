package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.Account;
import ru.arink_group.deliveryapp.domain.Address;
import ru.arink_group.deliveryapp.presentation.adapters.AddressesListAdapter;
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnAddressRemoveListener;
import ru.arink_group.deliveryapp.presentation.presenter.AccountPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.AccountPresenter;
import ru.arink_group.deliveryapp.presentation.view.AccountView;
import ru.arink_group.deliveryapp.presentation.view.FabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements AccountView, OnAddressRemoveListener {


    private AccountPresenter accountPresenter;
    private Account account;
    private AddressesListAdapter addressesListAdapter;
    private FloatingActionButton fab;

    @BindView(R.id.account_name)
    TextInputEditText accountName;

    @BindView(R.id.account_email)
    TextInputEditText accountEmail;

    @BindView(R.id.account_phone)
    TextInputEditText accountPhone;

    @BindView(R.id.send_account_button)
    Button sendButton;

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

        RecyclerView recyclerView = rootView.findViewById(R.id.addresses_recycler_view);
        recyclerView.setHasFixedSize(true);

        addressesListAdapter = new AddressesListAdapter();
        addressesListAdapter.setListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addressesListAdapter);

        FabView fabView = (FabView) getActivity();
        fab = fabView.getFab();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressesListAdapter.addNewAddress();
            }
        });
        fab.setImageResource(R.drawable.plus);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAccountModel();
                account.setAddresses(addressesListAdapter.getUpdatedList());
                accountPresenter.updateAccount(account);
            }
        });



        return rootView;
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAccount(Account account) {
        this.account = account;
        this.updateAccountView();
        this.addressesListAdapter.updateAddresses(account.getAddresses());
    }

    private void updateAccountModel() {
        this.account.setName(accountName.getText().toString());
        this.account.setEmail(accountEmail.getText().toString());
        this.account.setPhone(accountPhone.getText().toString());
    }

    private void updateAccountView() {
        accountName.setText(account.getName());
        accountEmail.setText(account.getEmail());
        accountPhone.setText(account.getPhone());
    }

    @Override
    public void updateAddress(Address address) {
        addressesListAdapter.updateAddress(address);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fab.setImageResource(R.drawable.cart);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accountPresenter.destroy();
    }

    @Override
    public void onAddressRemove(int id) {
        accountPresenter.deleteAddress(id);
    }
}
