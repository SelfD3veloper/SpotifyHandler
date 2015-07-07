package self.cbedoy.SpotifyHandler;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos Bedoy on 7/6/15.
 * <p/>
 * Mobile App Developer - Spotify Handler
 * <p/>
 * Pademobile
 */
public class SpotifyCell extends BaseAdapter
{

    private List<HashMap<String, Object>> mDataModel;

    public SpotifyCell(List<HashMap<String, Object>> dataModel){
        mDataModel = dataModel;
    }

    @Override
    public int getCount() {
        return mDataModel.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataModel.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder viewHolder;
        if(view == null){
            view = ApplicationLoader.mLayoutInflater.inflate(R.layout.spotify_cell, null);
            viewHolder = new ViewHolder();
            viewHolder.coverView = (ImageView) view.findViewById(R.id.coverView);
            viewHolder.songView = (TextView) view.findViewById(R.id.songView);
            viewHolder.albumView = (TextView) view.findViewById(R.id.albumView);
            viewHolder.artistView = (TextView) view.findViewById(R.id.artistView);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }


        return view;
    }


    private class ViewHolder{
        ImageView coverView;
        TextView songView;
        TextView artistView;
        TextView albumView;
    }
}
