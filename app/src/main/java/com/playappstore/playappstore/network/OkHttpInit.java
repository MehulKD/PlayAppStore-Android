package com.playappstore.playappstore.network;

import com.playappstore.playappstore.glideconfig.HttpsUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/4/5.
 */

public class OkHttpInit {

    private static OkHttpClient instance;
    private OkHttpInit (){}
    public static synchronized OkHttpClient getInstance() {
        if (instance == null) {
            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
            instance = new OkHttpClient.Builder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    .hostnameVerifier(new HostnameVerifier()
                    {
                        @Override
                        public boolean verify(String hostname, SSLSession session)
                        {
                            return true;
                        }
                    })
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .build();
        }
        return instance;
    }


}
