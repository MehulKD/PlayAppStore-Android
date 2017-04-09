package com.playappstore.playappstore.view.Fragment;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.playappstore.playappstore.content.DummyContent;
import com.playappstore.playappstore.R;
import com.playappstore.playappstore.adapter.SettingItemRecyclerViewAdapter;
import com.playappstore.playappstore.utils.SharedPreferenceUtil;

import java.io.File;

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

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        addPreferencesFromResource(R.xml.settings);
////        mSharedPreferenceUtil = SharedPreferenceUtil.getInstance();
////
////        mChangeIcons = findPreference(SharedPreferenceUtil.CHANGE_ICONS);
////        mChangeUpdate = findPreference(SharedPreferenceUtil.AUTO_UPDATE);
////        mClearCache = findPreference(SharedPreferenceUtil.CLEAR_CACHE);
////
////        mAnimationOnOff = (CheckBoxPreference) findPreference(SharedPreferenceUtil.ANIM_START);
////        mNotificationType = (CheckBoxPreference) findPreference(SharedPreferenceUtil.NOTIFICATION_MODEL);
////
////        if (SharedPreferenceUtil.getInstance().getNotificationModel() != Notification.FLAG_ONGOING_EVENT) {
////            mNotificationType.setChecked(false);
////        } else {
////            mNotificationType.setChecked(true);
////        }
////
////        mAnimationOnOff.setChecked(SharedPreferenceUtil.getInstance().getMainAnim());
//
////        mChangeIcons.setSummary(getResources().getStringArray(R.array.icons)[mSharedPreferenceUtil.getIconType()]);
//
////        mChangeUpdate.setSummary(
////                mSharedPreferenceUtil.getAutoUpdate() == 0 ? "禁止刷新" : "每" + mSharedPreferenceUtil.getAutoUpdate() + "小时更新");
////        mClearCache.setSummary(FileSizeUtil.getAutoFileOrFilesSize(BaseApplication.getAppCacheDir() + "/NetCache"));
//
////        mChangeIcons.setOnPreferenceClickListener(this);
////        mChangeUpdate.setOnPreferenceClickListener(this);
////        mClearCache.setOnPreferenceClickListener(this);
////        mNotificationType.setOnPreferenceChangeListener(this);
////
////        mAnimationOnOff.setOnPreferenceChangeListener(this);
//    }


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
