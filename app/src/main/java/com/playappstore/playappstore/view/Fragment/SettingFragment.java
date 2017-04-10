package com.playappstore.playappstore.view.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.playappstore.playappstore.content.DummyContent;
import com.playappstore.playappstore.R;
import com.playappstore.playappstore.utils.SharedPreferenceUtil;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SettingFragment extends PreferenceFragmentCompat
        implements Preference.OnPreferenceClickListener,
        Preference.OnPreferenceChangeListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private SharedPreferenceUtil mSharedPreferenceUtil;
    private Preference mChangeHost;
    private Preference mChangeUpdate;
    private Preference mClearCache;
    private CheckBoxPreference mNotificationType;
    private CheckBoxPreference mAnimationOnOff;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
//    public SettingFragment() {
//    }



    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, 1);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        //add xml
        addPreferencesFromResource(R.xml.settings);
        mSharedPreferenceUtil = SharedPreferenceUtil.getInstance();
        mChangeHost = findPreference(SharedPreferenceUtil.HOST);
        mChangeHost.setSummary(mSharedPreferenceUtil.getHost());
        mChangeHost.setOnPreferenceChangeListener(this);

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
        }
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
        void onListFragmentInteraction(DummyContent.DummyItem item);
    }





    @Override
    public boolean onPreferenceClick(Preference preference) {
        return true;
    }
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        System.out.println(newValue.toString());
        if (preference == mChangeHost) {
            mSharedPreferenceUtil.setHost((String) newValue);
        }

        return true;
    }
}
