package a1506a4.bwie.com.bwapp.app;

import android.app.Application;
import android.content.Context;
;

/**
 * Created by Shadow on 2017/10/13.
 */

public class MyApplication extends Application {

    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
