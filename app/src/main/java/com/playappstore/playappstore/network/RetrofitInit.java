package com.playappstore.playappstore.network;

import com.playappstore.playappstore.entity.FindBean;
import com.playappstore.playappstore.glideconfig.HttpsUtils;
import com.playappstore.playappstore.utils.APIService;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/4/5.
 */

public class RetrofitInit <T>{
    private static APIService apiInfo;
    private RetrofitInit (){}
    public static synchronized APIService getInstance() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        Retrofit retrofitInfo = new Retrofit.Builder()
                .baseUrl(APIService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpInit.getInstance()).build();
        APIService apiInfo = retrofitInfo.create(APIService.class);
        return apiInfo;
    }
}
