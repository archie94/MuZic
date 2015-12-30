package com.prizm.muzic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
//import android.support.v7.app.ActionBar;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;




import java.io.File;
import java.util.ArrayList;



public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener
{
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView optionList;
    ListView lv;
    String[] songNames;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        optionList = (ListView)findViewById(R.id.optionList);
        lv=(ListView)findViewById(R.id.lvPlayList);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.openDrawer,R.string.closeDrawer);

        /* Set the option list with the options as stored in the option[] array
           Store them in an ArrayList and create an array adapter with it
           Set the adapter of the list view as the created adapter
         */
        String[] option = {"Now Playing","Tracks","Albums","Playlists","Options","About"};
        ArrayList<String> optionArrayList = new ArrayList<String>();
        for(int i=0;i<option.length;i++)
            optionArrayList.add(option[i]);
        optionList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);// keep track of which option is selected in the list
        ArrayAdapter<String> optionAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,optionArrayList);
        optionList.setAdapter(optionAdapter);
        optionList.setOnItemClickListener(this);


        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        fragmentManager = getSupportFragmentManager();
        loadFragment(0); // on creation load the fragment for 0th position ,ie , Now Playing
        

        /*StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog()
                .penaltyDeath().build());*/


        /*final ArrayList<File> mySongs = findSongs(Environment.getExter nalStorageDirectory());
        songNames = new String[mySongs.size()];
        for (int i=0;i<mySongs.size();i++)
        {
            songNames[i]=mySongs.get(i).getName().toString().replace(".mp3","").replace(".mp3","");
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>(getApplicationContext(), R.layout.song_layout,R.id.textView,songNames);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                // Send the whole  songs list (mySongs) to Player.class, where the song will be played when clicked
                startActivity(new Intent(getApplicationContext(), Player.class).putExtra("pos",position).putExtra("songslist",mySongs));
            }
        });*/
    }

    private void loadFragment(int position)
    {
        optionList.setItemChecked(position,true);
        switch (position)
        {
            case 0:
                NowPlayingFragment nowPlayingFragment = new NowPlayingFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentHolder,nowPlayingFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
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

    public  void toast(String text)
    {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.action_settings)
            return true;
        else if(item.getItemId() == android.R.id.home)
        {
            if(drawerLayout.isDrawerOpen(optionList))
                drawerLayout.closeDrawer(optionList);
            else
                drawerLayout.openDrawer(optionList);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        loadFragment(position);
        drawerLayout.closeDrawer(optionList);
    }
}
