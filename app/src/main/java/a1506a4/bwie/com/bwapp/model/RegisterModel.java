package a1506a4.bwie.com.bwapp.model;

import a1506a4.bwie.com.bwapp.model.constant.RegisterApi;
import a1506a4.bwie.com.bwapp.model.utils.RetrofitManager;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Shadow on 2017/10/14.
 */

public class RegisterModel implements IModel {

    @Override
    public Observable<ResponseBody> register() {
        return RetrofitManager.getRetrofitManager("https://www.baidu.com").create(RegisterApi.class).register();
    }
}
