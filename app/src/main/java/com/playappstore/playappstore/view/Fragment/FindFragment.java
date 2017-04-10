package com.playappstore.playappstore.view.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import com.playappstore.playappstore.content.DummyContent;
import com.playappstore.playappstore.content.DummyContent.DummyItem;

import com.playappstore.playappstore.R;
import com.playappstore.playappstore.adapter.FindItemGridViewAdapter;
import com.playappstore.playappstore.entity.FindBean;
import com.playappstore.playappstore.network.RetrofitInit;
import com.playappstore.playappstore.utils.APIService;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FindFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;
    private GridView gridView;
    private FindItemGridViewAdapter adpter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FindFragment() {
    }

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, 3);
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
        View view = inflater.inflate(R.layout.fragment_find_list, container, false);
        SharedPreferences sp = getActivity().getSharedPreferences("APP_IDS",Context.MODE_PRIVATE);
        gridView = (GridView)view.findViewById(R.id.gridview);
        adpter = new FindItemGridViewAdapter(new ArrayList<FindBean>(), mListener,sp);
        gridView.setAdapter(adpter);


        APIService gitHubService = RetrofitInit.getInstance();
        retrofit2.Call<ArrayList<FindBean>> list = gitHubService.getFindList();
        list.enqueue(new Callback<ArrayList<FindBean>>() {
            @Override
            public void onResponse(retrofit2.Call<ArrayList<FindBean>> call, Response<ArrayList<FindBean>> response) {
                adpter.setData(response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<ArrayList<FindBean>> call, Throwable t) {

            }
        });
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
}
