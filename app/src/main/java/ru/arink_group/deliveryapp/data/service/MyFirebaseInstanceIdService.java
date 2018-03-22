package ru.arink_group.deliveryapp.data.service;

import android.content.SharedPreferences;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import io.reactivex.observers.DisposableObserver;
import ru.arink_group.deliveryapp.App;
import ru.arink_group.deliveryapp.domain.interactors.RegisterDevice;

/**
 * Created by kirillvs on 21.03.2018.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(App.APP_SHARED_PREF, App.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(App.CLOUD_REGISTRATION_KEY, refreshedToken);
        editor.apply();

        RegisterDevice registerDevice = new RegisterDevice();
        registerDevice.execute(new RefreshTokenObserver(), new RegisterDevice.Params(refreshedToken));
    }

    private final class RefreshTokenObserver extends DisposableObserver<Void> {

        @Override
        public void onNext(Void aVoid) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }


}
