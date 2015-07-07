package self.cbedoy.SpotifyHandler;

import android.util.Log;

/**
 * Created by Carlos Bedoy on 7/7/15.
 * <p/>
 * Mobile App Developer - Spotify Handler
 * <p/>
 * Pademobile
 */
public class LogService
{
    private static final String TAG = LogService.class.getSimpleName();

    public static void e(String args){
        Log.e(TAG, args);
    }
}
