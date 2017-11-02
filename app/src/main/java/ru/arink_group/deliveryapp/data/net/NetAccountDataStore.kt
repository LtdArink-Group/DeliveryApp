package ru.arink_group.deliveryapp.data.net

import io.reactivex.Observable
import ru.arink_group.deliveryapp.App
import ru.arink_group.deliveryapp.data.net.api.BookingFoodApi
import ru.arink_group.deliveryapp.data.repository.datasource.AccountDataStore
import ru.arink_group.deliveryapp.domain.dto.AccountDTO
import ru.arink_group.deliveryapp.domain.dto.AddressDTO

/**
 * Created by kirillvs on 30.10.17.
 */
class NetAccountDataStore: AccountDataStore {

    private val apiService = BookingFoodApi.create()

    override fun createAccount(accountDTO: AccountDTO): Observable<AccountDTO> {
        return apiService.createAccount(accountDTO)
    }

    override fun updateAccount(accountDTO: AccountDTO): Observable<AccountDTO> {
        return apiService.updateAccount(App.getUUID(), accountDTO)
    }

    override fun addAddress(addressDTO: AddressDTO): Observable<AddressDTO> {
        return apiService.addAddress(App.getUUID(), addressDTO)
    }

    override fun updateAddress(addressId: String, addressDTO: AddressDTO): Observable<AddressDTO> {
        return apiService.updateAddress(addressId, App.getUUID(), addressDTO)
    }

    override fun updateAddressPatch(addressId: String, addressDTO: AddressDTO): Observable<Void?> {
        return apiService.updateAddressPatch(addressId, App.getUUID(), addressDTO)
    }

    override fun deleteAddress(addressId: String): Observable<Void> {
        return apiService.deleteAddress(addressId, App.getUUID())
    }

    override fun getAccount(): Observable<AccountDTO> {
        return apiService.getAccount(App.getUUID())
    }

}