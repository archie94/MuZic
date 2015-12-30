package com.prizm.muzic;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {


    public NowPlayingFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // called on creation of fragment(not activity)
        // data will be ready here , can also get data from other activity through bundle
        // the view will not be ready here
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false);
    }

    @Override
    public void onDestroy()
    {
        // when fragment is destroyed
        super.onDestroy();
    }

    @Override
    public void onDetach()
    {
        // called when fragment is completely removed
        super.onDetach();
    }
}
