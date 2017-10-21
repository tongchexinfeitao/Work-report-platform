package a1506a4.bwie.com.bwapp.model.constant;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Shadow on 2017/10/14.
 */

public interface RegisterApi {
    @GET(".")
    Observable<ResponseBody> register();
}
