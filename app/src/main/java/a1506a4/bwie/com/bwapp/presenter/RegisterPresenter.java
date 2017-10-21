package a1506a4.bwie.com.bwapp.presenter;

import android.util.Log;

import java.io.IOException;

import a1506a4.bwie.com.bwapp.model.RegisterModel;
import a1506a4.bwie.com.bwapp.view.IRegisterView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Shadow on 2017/10/14.
 */

public class RegisterPresenter extends IPresenter<IRegisterView> {

    private static final String TAG = "TAG";
    private RegisterModel registerModel;

    public RegisterPresenter(IRegisterView view) {
        super(view);
    }

    @Override
    public void init() {
        super.init();
        registerModel = new RegisterModel();
    }

    public void register() {
        registerModel.register()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {

                        try {
                            view.registerSuccessed();
                            Log.e(TAG, "onNext: " + responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        view.registerFailed();
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
