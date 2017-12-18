package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.Account;
import ru.arink_group.deliveryapp.domain.dao.Address;
import ru.arink_group.deliveryapp.presentation.adapters.AddressesListAdapter;
import ru.arink_group.deliveryapp.presentation.adapters.interfaces.OnAddressListener;
import ru.arink_group.deliveryapp.presentation.presenter.AccountPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.AccountPresenter;
import ru.arink_group.deliveryapp.presentation.view.AccountView;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.MenuView;
import ru.arink_group.deliveryapp.presentation.view.activity.AddressActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements AccountView, OnAddressListener {

    private Unbinder unbinder;

    private AccountPresenter accountPresenter;
    private Account account;
    private AddressesListAdapter addressesListAdapter;
    private FloatingActionButton fab;
    private MenuView menuView;

    @BindView(R.id.addresses_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.summary_create_address_button)
    Button summaryCreateAccountButton;

    @BindView(R.id.account_name)
    TextInputEditText accountName;

    @BindView(R.id.account_email)
    TextInputEditText accountEmail;

    @BindView(R.id.account_phone)
    TextInputEditText accountPhone;

    @BindView(R.id.send_account_button)
    CircularProgressButton sendButton;

    @BindString(R.string.error_cant_be_blank)
    String errorCantBeBlankString;

    @BindString(R.string.send_ok)
    String sendOk;

    @BindString(R.string.send_fail)
    String sendFail;

    @BindString(R.string.account)
    String accountTitle;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        unbinder = ButterKnife.bind(this, rootView);

        final FabView fabView = (FabView) getActivity();
        fabView.showOrderFab();

        accountPresenter = new AccountPresenterImpl(this);
        accountPresenter.getAccount();

        recyclerView.setHasFixedSize(true);

        addressesListAdapter = new AddressesListAdapter();
        addressesListAdapter.setListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addressesListAdapter);

        fab = fabView.getFab();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accountPresenter.isNewAccount()) {
                    if (!verifyAccountModel()) return;
                    updateAccountModel();
                    accountPresenter.createAccountWithAddress(account);
                } else {
                    Intent intent = new Intent(getActivity(), AddressActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });
        fab.setImageResource(R.drawable.plus);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verifyAccountModel()) return;
                updateAccountModel();
                account.setAddresses(addressesListAdapter.getAddresses());
                accountPresenter.updateAccount(account);
                sendButton.startAnimation();

                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendButton.revertAnimation(new OnAnimationEndListener() {
                            @Override
                            public void onAnimationEnd() {
                                if (!sendButton.getText().toString().equalsIgnoreCase(sendFail)) {
                                    sendButton.setText(sendOk);
                                    sendButton.setBackgroundColor(getResources().getColor(R.color.green));
                                }
                            }
                        });
                    }
                }, 1000);
            }
        });

        summaryCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accountPresenter.isNewAccount()) {
                    if (!verifyAccountModel()) return;
                    updateAccountModel();
                    accountPresenter.createAccountWithAddress(account);
                } else {
                    Intent intent = new Intent(getActivity(), AddressActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });

        AppCompatActivity titleActivity = (AppCompatActivity) getActivity();
        titleActivity.getSupportActionBar().setTitle(R.string.account);

        menuView = (MenuView) getActivity();
        loadingStarted();
        return rootView;
    }

    @Override
    public void showErrorMessage(String message) {
        sendButton.revertAnimation(new OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                sendButton.setText(sendFail);
                sendButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            }
        });
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAccount(Account account) {
        this.account = account;
        this.updateAccountView();
        if(account.getAddresses().size() > 0 && !(account.getAddresses().size() == 1 && account.getAddresses().get(0).getId() == null)) {
            this.addressesListAdapter.updateAddresses(account.getAddresses());
            recyclerView.setVisibility(View.VISIBLE);
            summaryCreateAccountButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void startNewAddressAfterCreateAccount() {
        Intent intent = new Intent(getActivity(), AddressActivity.class);
        try{
            boolean startOrderAfterAddress = getArguments().getBoolean(OrderFragment.REDIRECT_TO_ORDER, false);
            intent.putExtra(OrderFragment.REDIRECT_TO_ORDER, startOrderAfterAddress);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        getActivity().startActivity(intent);
    }

    @Override
    public void loadingStarted() {
        menuView.loadingStart();
    }

    @Override
    public void loadingFinished() {
        menuView.loadingFinish();
    }

    private void updateAccountModel() {
        this.account.setName(accountName.getText().toString());
        this.account.setEmail(accountEmail.getText().toString());
        this.account.setPhone(accountPhone.getText().toString());
    }

    private boolean verifyAccountModel() {
        boolean flag = true;
        if (accountName.getText().toString().isEmpty()) {
            accountName.setError(errorCantBeBlankString);
            flag = false;
        }
        if (accountEmail.getText().toString().isEmpty()) {
            accountEmail.setError(errorCantBeBlankString);
            flag = false;
        }
        if (accountPhone.getText().toString().isEmpty()) {
            accountPhone.setError(errorCantBeBlankString);
            flag = false;
        }

        return flag;
    }

    private void updateAccountView() {
        accountName.setText(account.getName());
        accountEmail.setText(account.getEmail());
        accountPhone.setText(account.getPhone());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        fab.setImageResource(R.drawable.cart); // TODO rework it's not good
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accountPresenter.destroy();
    }

    @Override
    public void onAddressRemove(int id, int addrSize) {
        if (addrSize == 1) {
            Toast.makeText(getActivity(), R.string.error_must_be_at_leat_one_address, Toast.LENGTH_SHORT).show();
            return;
        }
        accountPresenter.deleteAddress(id);
    }

    @Override
    public void onAddressEdit(Address address) {
        Intent intent = new Intent(getActivity(), AddressActivity.class);
        intent.putExtra(AddressActivity.ADDRESS_SER, address);
        getActivity().startActivity(intent);
    }
}
