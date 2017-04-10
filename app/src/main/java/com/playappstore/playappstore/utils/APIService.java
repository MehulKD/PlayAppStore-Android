package com.playappstore.playappstore.utils;

import com.playappstore.playappstore.entity.FindBean;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/4/5.
 */

public interface APIService {
    String baseUrl = SharedPreferenceUtil.getInstance().getHost();;

    @GET("/records/ios")
    Call<ArrayList<FindBean>> getFindList();
    @GET("/apps/ios/{bundleId}")
    Call<ArrayList<FindBean>> getFavouriteList(@Path("bundleId")String bundleId);
}
