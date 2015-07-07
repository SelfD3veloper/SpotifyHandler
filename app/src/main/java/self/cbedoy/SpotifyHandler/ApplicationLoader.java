package self.cbedoy.SpotifyHandler;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;


import kaaes.spotify.webapi.android.SpotifyApi;

/**
 * Created by Carlos Bedoy on 7/6/15.
 * <p/>
 * Mobile App Developer - Spotify Handler
 * <p/>
 * Pademobile
 */
public class ApplicationLoader extends Application
{

    public static volatile Context mContext;
    public static volatile LayoutInflater mLayoutInflater;
    public static volatile Handler mMainHandler;

    public static volatile SpotifyApi mSpotifyApi;

    private final String CLIENT_ID = "f5ba21a8014d4f718dc2b41b5ecc2a87";
    private final String CLIENTE_SECRET = "23764b678ddb411298127fd784fdc3d0";

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        mMainHandler = new Handler(getMainLooper());

        mSpotifyApi = new SpotifyApi();

        // Most (but not all) of the Spotify Web API endpoints require authorisation.
        // If you know you'll only use the ones that don't require authorisation you can skip this step
        //mSpotifyApi.setAccessToken()

    }
}
