package com.prizm.muzic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Arka on 16-01-16.
 */
public class CustomListTracks extends BaseAdapter
{

    private final Context context;
    private final String tracks[];

    public CustomListTracks(Context context, String[] tracks)
    {
        this.context = context;
        this.tracks = tracks;
    }

    @Override
    public int getCount()
    {
        return tracks.length;
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    class ViewHolder
    {
        ImageView imageView;
        TextView songName, songLength, artistName;
        public ViewHolder(View v)
        {
            imageView = (ImageView)v.findViewById(R.id.songlist_songImage);
            songName = (TextView)v.findViewById(R.id.songlist_row_songName);
            songLength = (TextView)v.findViewById(R.id.songlist_row_songLength);
            artistName = (TextView)v.findViewById(R.id.songlist_row_artistName);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rowView = convertView;
        ViewHolder viewHolder;
        if(rowView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = layoutInflater.inflate(R.layout.songlist_row,parent,false);
            viewHolder = new ViewHolder(rowView);
            rowView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)rowView.getTag();
        }


        viewHolder.songName.setText(tracks[position]);

        return null;
    }
}
