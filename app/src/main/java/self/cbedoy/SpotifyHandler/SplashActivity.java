package self.cbedoy.SpotifyHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Carlos Bedoy on 7/7/15.
 * <p/>
 * Mobile App Developer - Spotify Handler
 * <p/>
 * Pademobile
 */
public class SplashActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        ApplicationLoader.mMainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);

                finish();
            }
        }, 1000);
    }
}
