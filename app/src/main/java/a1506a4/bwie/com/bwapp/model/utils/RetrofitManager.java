package a1506a4.bwie.com.bwapp.model.utils;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shadow on 2017/10/13.
 */

public class RetrofitManager {
    private Retrofit retrofit;
    private static final String DEFAULT_BASE_URL = "";

    public static class SingelHolder {
        public static RetrofitManager instance = new RetrofitManager(DEFAULT_BASE_URL);
    }


    public static RetrofitManager getInstance() {
        return SingelHolder.instance;
    }

    public RetrofitManager(String baseUrl) {
        this.retrofit = createRetrofit(baseUrl);
    }

    public static RetrofitManager getRetrofitManager(String baseUrl) {
        return new RetrofitManager(baseUrl);
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    private Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .client(createOkHttp())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    private OkHttpClient createOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }


    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            //打印retrofit日志
            Log.i("RetrofitLog", "retrofitBack = " + message);

        }
    });


}
