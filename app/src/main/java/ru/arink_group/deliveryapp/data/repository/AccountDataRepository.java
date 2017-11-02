package ru.arink_group.deliveryapp.data.repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.data.repository.factory.AccountDataFactory;
import ru.arink_group.deliveryapp.domain.dto.AccountDTO;
import ru.arink_group.deliveryapp.domain.dto.AddressDTO;
import ru.arink_group.deliveryapp.domain.repository.AccountRepository;
import ru.arink_group.deliveryapp.App;

/**
 * Created by kirillvs on 30.10.17.
 */

public class AccountDataRepository implements AccountRepository {

    @Inject
    AccountDataFactory accountDataFactory;

    public AccountDataRepository() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<AccountDTO> createAccount(AccountDTO accountDTO) {
        return accountDataFactory.create().createAccount(accountDTO);
    }

    @Override
    public Observable<AccountDTO> updateAccount(AccountDTO accountDTO) {
        return accountDataFactory.create().updateAccount(accountDTO);
    }

    @Override
    public Observable<AddressDTO> addAddress(AddressDTO addressDTO) {
        return accountDataFactory.create().addAddress(addressDTO);
    }

    @Override
    public Observable<AddressDTO> updateAddress(String addressId, AddressDTO addressDTO) {
        return accountDataFactory.create().updateAddress(addressId, addressDTO);
    }

    @Override
    public Observable<Void> updateAddressPatch(String addressId, AddressDTO addressDTO) {
        return accountDataFactory.create().updateAddressPatch(addressId, addressDTO);
    }

    @Override
    public Observable<Void> deleteAddress(String addressId) {
        return accountDataFactory.create().deleteAddress(addressId);
    }

    @Override
    public Observable<AccountDTO> getAccount() {
        return accountDataFactory.create().getAccount();
    }
}
