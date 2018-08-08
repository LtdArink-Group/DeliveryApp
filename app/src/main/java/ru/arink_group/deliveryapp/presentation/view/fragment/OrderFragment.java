package ru.arink_group.deliveryapp.presentation.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.arink_group.deliveryapp.R;
import ru.arink_group.deliveryapp.domain.dao.Address;
import ru.arink_group.deliveryapp.domain.dao.Delivery;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.domain.interactors.GetCompanyFromShared;
import ru.arink_group.deliveryapp.presentation.adapters.OrderAddressesListAdapter;
import ru.arink_group.deliveryapp.presentation.adapters.OrdersListAdapter;
import ru.arink_group.deliveryapp.presentation.shared.DateTime;
import ru.arink_group.deliveryapp.presentation.shared.TimePickerFragment;
import ru.arink_group.deliveryapp.presentation.presenter.OrderPresenterImpl;
import ru.arink_group.deliveryapp.presentation.presenter.interfaces.OrderPresenter;
import ru.arink_group.deliveryapp.presentation.view.FabView;
import ru.arink_group.deliveryapp.presentation.view.MenuView;
import ru.arink_group.deliveryapp.presentation.view.OrderView;
import ru.arink_group.deliveryapp.presentation.view.activity.MenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements OrderView,
        OrdersListAdapter.ProductChangeListener,
        View.OnClickListener,
        TimePicker.OnTimeChangedListener {
    public static final String REDIRECT_TO_ORDER = "redirect to order";

    private Unbinder unbinder;

    private OrderPresenter orderPresenter;
    private OrdersListAdapter ordersListAdapter;
    private ArrayAdapter<String> addressesStringAdaptet;
    private List<Address> addresses;
    private MenuView menuView;


    private DateTime selectedTime=new DateTime(-1,-1);

    @BindView(R.id.summary_self_export_switch)
    SwitchCompat selfExportSwitch;

    @BindView(R.id.summary_cost)
    TextView summaryCost;
    @BindView(R.id.summary_discount)
    TextView summaryDiscount;

    @BindView(R.id.summary_delivery)
    TextView summaryDelivery;

    @BindView(R.id.summary)
    TextView summary;

    @BindView(R.id.summary_address_list)
    TextView summaryAddressListView;

    @BindView(R.id.send_order_button)
    CircularProgressButton sendButton;

    @BindView(R.id.summary_address_list_spinner)
    Spinner addressListSpinner;

    @BindView(R.id.summary_create_address_button)
    Button summaryCreateAddressButton;

    @BindView(R.id.summary_address_progress)
    ProgressBar progressBar;

    @BindString(R.string.free)
    String freeString;

    @BindString(R.string.order_send_ok)
    String orderSendOkString;

    @BindString(R.string.order_send_fail)
    String orderSendFailString;

    @BindString(R.string.order)
    String titleString;

    @BindString(R.string.error_address_empty)
    String errorAddressEmpty;


    @BindView(R.id.start_date_picker)
    Button startDateDialog;

    @BindView(R.id.note)
    EditText note;


    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);

        menuView = (MenuView) getActivity();

        unbinder = ButterKnife.bind(this, root);

        this.orderPresenter = new OrderPresenterImpl(this);

        selfExportSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                OrderFragment.this.updateTotals();
            }
        });

        RecyclerView orderRecyclerView = root.findViewById(R.id.order_recycler_view);
        orderRecyclerView.setHasFixedSize(true);

        this.ordersListAdapter = new OrdersListAdapter();
        this.ordersListAdapter.setProductChangeListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        orderRecyclerView.setAdapter(ordersListAdapter);

        orderRecyclerView.setLayoutManager(llm);

        orderPresenter.getProductsFromBasket();

        sendButton.setOnClickListener(this);

        note.setOnClickListener(this);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle(R.string.order);

        final FabView fabView = (FabView) getActivity();
        fabView.hideOrderFab();

        summaryCreateAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountFragment accountFragment = new AccountFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean(REDIRECT_TO_ORDER, true);
                accountFragment.setArguments(bundle);
                menuView.changeFragment(accountFragment);
            }
        });

        addressesStringAdaptet = new OrderAddressesListAdapter(getActivity(), R.layout.item_spinner_address, new ArrayList<String>());
        addressListSpinner.setAdapter(addressesStringAdaptet);


        initTimePicker();

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initTimePicker() {
        startDateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment dp = new TimePickerFragment();
                dp.setListener(OrderFragment.this);
                dp.show(getFragmentManager(), "DeliveryTime");
            }
        });
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

        Delivery delivery = GetCompanyFromShared.INSTANCE.getCompanyOrDefault().getDelivery();

        double summary = 0.0;

        for (Product product : products) {
            summary += product.getTotalSelectedSum();
        }

        double discount = 0.0;
        double deliveryCost = 0.0;

        if (selfExportSwitch == null) return;

        if (selfExportSwitch.isChecked()) {
            discount = summary * delivery.getPickupDiscount() / 100;
            summaryAddressListView.setText(R.string.summary_address_list_pickup);
            orderPresenter.getAddresses(true);
        } else {
            summaryAddressListView.setText(R.string.summary_address_list);
            orderPresenter.getAddresses(false);

        }

        String summaryCostForm = getString(R.string.form_rubles, String.valueOf(summary));
        this.summaryCost.setText(summaryCostForm);

        String discountForm = getString(R.string.form_rubles, String.valueOf(discount));
        this.summaryDiscount.setText(discountForm);

        if (selfExportSwitch.isChecked()) {
            deliveryCost = 0.0;
            String summaryDeliveryForm = getString(R.string.form_rubles, String.valueOf(deliveryCost));
            this.summaryDelivery.setText(summaryDeliveryForm);
        } else if (summary < delivery.getFreeShipping()) {
            String summaryDeliveryForm = getString(R.string.form_rubles, String.valueOf(delivery.getCost()));
            this.summaryDelivery.setText(summaryDeliveryForm);
            deliveryCost = delivery.getCost();
        } else {
            this.summaryDelivery.setText(freeString);
        }


        double allSummary = summary - discount + deliveryCost;
        allSummary = allSummary >= 0.0 ? allSummary : 0.0;

        String summaryForm = getString(R.string.form_rubles, String.valueOf(allSummary));
        this.summary.setText(summaryForm);
    }

    @Override
    public void updateAddresses(List<Address> addresses) {
        if (addresses.size() > 0) {
            this.addresses = addresses;
            List<String> addressesString = new ArrayList<>();
            for (int i = 0; i < addresses.size(); i++) {
                addressesString.add(i, this.concatAddressName(addresses.get(i)));
            }
            addressesStringAdaptet.clear();
            addressesStringAdaptet.addAll(addressesString);
            addressListSpinner.setVisibility(View.VISIBLE);
            summaryCreateAddressButton.setVisibility(View.GONE);
        } else {
            if (!isSelfPickup())
                summaryCreateAddressButton.setVisibility(View.VISIBLE);
            addressListSpinner.setVisibility(View.GONE);
        }
    }


    @Override
    public void showCreateAccountButton() {
        addressListSpinner.setVisibility(View.GONE);
        summaryCreateAddressButton.setVisibility(View.VISIBLE);
    }

    private String concatAddressName(Address a) {
        StringBuilder addressName = new StringBuilder();
        addressName.append(a.getTitle());
        addressName.append(" (");
        addressName.append(a.getStreet());
        addressName.append(", ");
        addressName.append(a.getHouse());
        addressName.append(")");
        return addressName.toString();
    }

    @Override
    public void showErrorMessage(String e) {
        sendButton.revertAnimation(new OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                sendButton.setText(orderSendFailString);
                sendButton.setBackgroundColor(getResources().getColor(R.color.colorButtonFail));
            }
        });
        if (e != null) Toast.makeText(getActivity(), e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSendingOrderOk() {
        sendButton.revertAnimation(new OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                sendButton.setText(orderSendOkString);
                sendButton.setBackgroundColor(getResources().getColor(R.color.colorButtonSuccess));
            }
        });
        disableUi();
    }

    private void disableUi() {
        sendButton.setOnClickListener(null);
        selfExportSwitch.setEnabled(false);
        startDateDialog.setEnabled(false);
        addressListSpinner.setEnabled(false);
    }

    @Override
    public void showPlaceholder() {
        EmptyOrderFragment emptyOrderFragment = new EmptyOrderFragment();

        menuView.changeFragmentToPlaceHolder(emptyOrderFragment);
    }

    @Override
    public List<Product> getListProducts() {
        return ordersListAdapter.getOrdersList();
    }

    @Override
    public void onChangeProduct(Product product) {
        if (sendButton.getText().toString().equalsIgnoreCase(orderSendOkString)) return;
        orderPresenter.updateProduct(product);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        orderPresenter.destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == sendButton.getId()) {
            sendButton.startAnimation();
            if (verifyOrder() && verifyDeliveryTime())
                orderPresenter.sendOrderToServer();
        } else if(v.getId() != note.getId()) {
            showErrorMessage(null);
        }
    }

    private boolean verifyDeliveryTime() {

        String time = getString(R.string.summary_delivery_time);
        boolean valid = !time.equalsIgnoreCase(String.valueOf(startDateDialog.getText())) && !GetCompanyFromShared.INSTANCE.getCompanyOrDefault().getCurrentDayOrFirst().isRest();
        if (time.equalsIgnoreCase(String.valueOf(startDateDialog.getText())))
            Toast.makeText(getActivity(), R.string.error_delivery_time_empty, Toast.LENGTH_SHORT).show();
        if (GetCompanyFromShared.INSTANCE.getCompanyOrDefault().getCurrentDayOrFirst().isRest())
            Toast.makeText(getActivity(), R.string.error_cant_order_is_rest, Toast.LENGTH_SHORT).show();
        return valid;
    }

    private boolean verifyOrder() {
        if (addressListSpinner.getSelectedItemPosition() != Spinner.INVALID_POSITION || selfExportSwitch.isChecked())
            return true;
        Toast.makeText(getActivity(), errorAddressEmpty, Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public Integer getSelectedAddressId() {
        int pos = addressListSpinner.getSelectedItemPosition();
        return addresses.get(pos).getId();
    }

    @Override
    public DateTime getSelectedTime() {
        return selectedTime;
    }

    @Override
    public boolean isSelfPickup() {
        return selfExportSwitch.isChecked();
    }


    @Override
    public void loadingAddressStart() {
        progressBar.setVisibility(View.VISIBLE);
        addressListSpinner.setVisibility(View.GONE);
    }

    @Override
    public void loadingAddressFinish() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void redirectToHistory() {
        Intent intent = new Intent(getActivity(), MenuActivity.class);
        intent.putExtra(MenuActivity.IS_HISTORY_START, true);
        getActivity().startActivity(intent);
    }

    @Override
    public String getNote() {
        return (note.getText().toString().equals("Комментарий ...")) ? null : note.getText().toString();
    }

    @Override
    public void loadingStart() {
        menuView.loadingStart();
    }

    @Override
    public void loadingFinish() {
        MenuView menuView = (MenuView) getActivity();
        menuView.loadingFinish();
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        if (hourOfDay == null && minute == null) {
            selectedTime = new DateTime(null,null);
            startDateDialog.setText(R.string.soon);
        }
        else {
            DateTime start = GetCompanyFromShared.INSTANCE.getCompanyOrDefault().getCurrentDayOrFirst().startTimeClass();
            DateTime end = GetCompanyFromShared.INSTANCE.getCompanyOrDefault().getCurrentDayOrFirst().endTimeClass();

            Calendar c = Calendar.getInstance();
            DateTime current = new DateTime(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));


            selectedTime = new DateTime(hourOfDay, minute);

            if (start == null || end == null) {
                Toast.makeText(getActivity(), R.string.error_cant_order_is_rest, Toast.LENGTH_SHORT).show();
            } else if (selectedTime.isGreaterThen(end) || selectedTime.isLowerThen(start)) {
                String delivery_error = getString(R.string.time_should_be_between, start, end);
                Toast.makeText(getActivity(), delivery_error, Toast.LENGTH_SHORT).show();
            } else if (selectedTime.isLowerThen(current)) {
                Toast.makeText(getActivity(), R.string.error_cant_be_less_then_current, Toast.LENGTH_SHORT).show();
            } else if (selectedTime.isLowerThenNextHourOf(current) && end.isLowerThenNextHourOf(current)) {
                Toast.makeText(getActivity(), R.string.error_cant_to_late, Toast.LENGTH_SHORT).show();
            } else if (selectedTime.isLowerThenNextHourOf(current)) {
                Toast.makeText(getActivity(), R.string.error_cant_be_greater_then_hour, Toast.LENGTH_SHORT).show();
            } else {
                startDateDialog.setText(selectedTime.toString());
            }
        }
    }
}
