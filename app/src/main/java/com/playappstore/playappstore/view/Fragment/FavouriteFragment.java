package com.playappstore.playappstore.view.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playappstore.playappstore.content.DummyContent;
import com.playappstore.playappstore.content.DummyContent.DummyItem;
import com.playappstore.playappstore.R;
import com.playappstore.playappstore.adapter.FavouriteItemRecyclerViewAdapter;
import com.playappstore.playappstore.entity.FindBean;
import com.playappstore.playappstore.network.RetrofitInit;
import com.playappstore.playappstore.utils.APIService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FavouriteFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private static Call<ArrayList<FindBean>> list;
    private ArrayList<ArrayList<FindBean>> listData = new ArrayList<>();
    private Set<String> bundleIds;
    private static APIService gitHubService;
    private FavouriteItemRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
//    public FavouriteFragment() {
//    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FavouriteFragment newInstance() {
        gitHubService = RetrofitInit.getInstance();

        FavouriteFragment fragment = new FavouriteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        SharedPreferences sp = getActivity().getSharedPreferences("APP_IDS",Context.MODE_PRIVATE);
        bundleIds = sp.getStringSet("bundleId", new HashSet<String>());

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new FavouriteItemRecyclerViewAdapter(listData, mListener,
                    LayoutInflater.from(getActivity()),getActivity());
            recyclerView.setAdapter(adapter);
        }

        if (bundleIds.size()!=0) {
            for(String bundleId:bundleIds)
                getData(bundleId);
        }
        return view;
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

    private void getData(String bundleId){
        list = gitHubService.getFavouriteList(bundleId);
        list.enqueue(new Callback<ArrayList<FindBean>>() {
            @Override
            public void onResponse(Call<ArrayList<FindBean>> call, Response<ArrayList<FindBean>> response) {
                if (response.body()!=null&&response.body().size()!=0){
                    listData.add(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FindBean>> call, Throwable t) {

            }
        });
    }
}
