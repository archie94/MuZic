package com.prizm.muzic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Arka on 16-01-16.
 */
public class TracksFragment extends Fragment
{

    String[] songNames;
    ListView lv;
    Activity activity;
    public TracksFragment()
    {

    }


    @Override
    public void onAttach(Activity activity)
    {
        this.activity = activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ArrayList<File> mySongs = findSongs(Environment.getExternalStorageDirectory());
        songNames = new String[mySongs.size()];
        for (int i=0;i<mySongs.size();i++)
        {
            songNames[i]=mySongs.get(i).getName().toString().replace(".mp3","").replace(".mp3","");
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>(activity.getApplicationContext(), R.layout.song_layout,R.id.textView,songNames);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Send the whole  songs list (mySongs) to Player.class, where the song will be played when clicked
                startActivity(new Intent(activity.getApplicationContext(), Player.class).putExtra("pos", position).putExtra("songslist", mySongs));
            }
        });
        return inflater.inflate(R.layout.fragment_tracks,container,false);
    }

    public ArrayList<File> findSongs(File root)
    {
        File [] files = root.listFiles();
        ArrayList<File> songList = new ArrayList<File>();
        for(File singleFile : files)
        {
            if(singleFile.isDirectory()&& !singleFile.isHidden())
            {
                songList.addAll(findSongs(singleFile));
            }
            else
            {
                if(singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav"))
                {
                    songList.add(singleFile);
                }
            }
        }
        return  songList;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }
}
