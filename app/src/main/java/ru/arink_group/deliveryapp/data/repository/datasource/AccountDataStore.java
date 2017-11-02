package ru.arink_group.deliveryapp.data.repository.datasource;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.dto.AccountDTO;
import ru.arink_group.deliveryapp.domain.dto.AddressDTO;

/**
 * Created by kirillvs on 30.10.17.
 */

public interface AccountDataStore {
    Observable<AccountDTO> createAccount(AccountDTO accountDTO);
    Observable<AccountDTO> updateAccount(AccountDTO accountDTO);
    Observable<AddressDTO> addAddress(AddressDTO addressDTO);
    Observable<AddressDTO> updateAddress(String addressId, AddressDTO addressDTO);
    Observable<Void> updateAddressPatch(String addressId, AddressDTO addressDTO);
    Observable<Void> deleteAddress(String addressId);
    Observable<AccountDTO> getAccount();
}
