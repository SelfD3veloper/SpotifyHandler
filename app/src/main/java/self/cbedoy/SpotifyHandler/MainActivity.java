package self.cbedoy.SpotifyHandler;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.melnykov.fab.FloatingActionButton;
import com.poliveira.apps.parallaxlistview.ParallaxListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Album;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.TrackSimple;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {


    private ParallaxListView parallaxListView;

    private FloatingActionButton floatingActionButton;

    private View parallaxViewHeader;

    private ImageView imageView;
    private ArrayList<HashMap<String, Object>> dataModel;

    private ViewCell viewCell;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        parallaxListView = (ParallaxListView) findViewById(R.id.parallaxListview);

        parallaxViewHeader = getLayoutInflater().inflate(R.layout.header_view, parallaxListView, false);

        imageView = (ImageView) parallaxViewHeader.findViewById(R.id.imageView);

        parallaxListView.setParallaxView(parallaxViewHeader);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.attachToListView(parallaxListView);

        dataModel = new ArrayList<>();

        viewCell = new ViewCell(dataModel);

        parallaxListView.setAdapter(viewCell);


        ApplicationLoader.mMainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                search();
            }
        }, 600);

        viewCell.notifyDataSetChanged();
    }

    public void search(){

        SpotifyService spotifyService = ApplicationLoader.mSpotifyApi.getService();


        List<String> albumsRandom = new ArrayList<>();

        albumsRandom.add("62UU2DzfeLRV17PGCJOSN5");
        albumsRandom.add("0yQMi4eGKQtZGZCqrjq0kc");
        albumsRandom.add("4dhfdOEUI4tfqwdgouFTQA");

        albumsRandom.add("0C7eBRwY63jNnFyqqwrwlj");
        albumsRandom.add("0uVCixyyh2PiVN4hGDVpMl");
        albumsRandom.add("1NllrdYrRGnzaOEgMOifMB");

        albumsRandom.add("3hRE5kwKGasVPXvcIpx2xv");
        albumsRandom.add("6YQu7tXYfN1r2NLx8tfEI6");
        albumsRandom.add("21zAhIFLexPAxVa3oSZoi9");


        Collections.shuffle(albumsRandom);


        String albumToSearch = albumsRandom.get(0);

        //This wild life album
        spotifyService.getAlbum(albumToSearch, new Callback<Album>() {
            @Override
            public void success(Album album, Response response) {

                List<Image> images = album.images;

                Image image = images.get(0);

                Pager<TrackSimple> tracks = album.tracks;

                List<TrackSimple> items = tracks.items;

                dataModel.clear();

                for(TrackSimple trackSimple : items){
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("value", trackSimple.name);
                    data.put("href", trackSimple.href);
                    data.put("id", trackSimple.id);
                    data.put("preview_url", trackSimple.preview_url);
                    data.put("uri", trackSimple.uri);

                    dataModel.add(data);
                }

                Picasso.with(getApplicationContext()).load(image.url).into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {


                        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                        ColorProviderService.getInstance().decorateImage(bitmap);

                        int backgroundColor = ColorProviderService.getInstance().getBackgroundColor();
                        int detailColor = ColorProviderService.getInstance().getDetailColor();
                        int primaryColor = ColorProviderService.getInstance().getPrimaryColor();
                        int secondaryColor = ColorProviderService.getInstance().getSecondaryColor();


                        viewCell.setBackgroundColor(backgroundColor);
                        viewCell.setDetailColor(detailColor);

                        floatingActionButton.setColorNormal(detailColor);
                        floatingActionButton.setColorPressed(primaryColor);
                        floatingActionButton.setColorRipple(secondaryColor);

                        viewCell.notifyDataSetChanged();
                    }

                    @Override
                    public void onError() {
                        dataModel.clear();

                        viewCell.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Album failure", error.toString());
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);

        startActivity(intent);

        finish();
    }
}
