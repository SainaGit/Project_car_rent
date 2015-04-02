package sdeveloper.car.rent.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import sdeveloper.car.rent.R;
import sdeveloper.car.rent.application.CarRent;
import sdeveloper.car.rent.application.RootActivity;
import sdeveloper.car.rent.login.ActivityLogin;
import sdeveloper.car.rent.tutorial.ActivityTutorial;
import sdeveloper.car.rent.utils.Singleton;

public class ActivitySplashScreen extends Activity {
    private static int splash_time_out = 4500;
    public static Singleton singleton;
    private Intent intent;
    private ImageView splashProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        singleton = Singleton.getInstance(this);
        splashProgress = (ImageView) findViewById(R.id.splash_progress);

        final Animation rotate = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotate.setRepeatCount(10);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(3000);
        splashProgress.startAnimation(rotate);
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (singleton.getIsFirst()) {
                    intent = new Intent(ActivitySplashScreen.this, ActivityTutorial.class);
                } else {
                    intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                }
                rotate.cancel();
                splashProgress.clearAnimation();
                startActivity(intent);
                finish();
            }
        }, splash_time_out);
    }
}
