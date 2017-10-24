package ru.arink_group.deliveryapp.domain.interactors

import io.reactivex.Observable
import ru.arink_group.deliveryapp.domain.Product

/**
 * Created by kirillvs on 24.10.17.
 */
class SendOrderToServer: UseCase<Boolean, SendOrderToServer.Params>() {

    override fun buildUseCaseObservable(params: Params?): Observable<Boolean> {
        return Observable.just(true)
        TODO("Implement after server side will be created")
    }


    data class Params(val products:List<Product>)
}
