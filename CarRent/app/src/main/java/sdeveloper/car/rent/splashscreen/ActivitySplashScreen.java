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

import sdeveloper.car.rent.R;
import sdeveloper.car.rent.application.CarRent;
import sdeveloper.car.rent.application.RootActivity;
import sdeveloper.car.rent.login.ActivityLogin;
import sdeveloper.car.rent.tutorial.ActivityTutorial;
import sdeveloper.car.rent.utils.Singleton;

public class ActivitySplashScreen extends Activity {
    private static int splash_time_out = 3000;
    public static Singleton singleton;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        singleton = Singleton.getInstance(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(singleton.getIsFirst()) {
                    intent = new Intent(ActivitySplashScreen.this, ActivityTutorial.class);
                } else {
                    //TODO Доорхи мөрийг идэвхжүүлэх
//                    intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                    intent = new Intent(ActivitySplashScreen.this, ActivityTutorial.class);
                }
                startActivity(intent);
                finish();
            }
        }, splash_time_out);
    }
}
