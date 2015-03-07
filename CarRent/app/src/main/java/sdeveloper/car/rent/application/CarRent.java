package sdeveloper.car.rent.application;

import android.app.Application;
import android.widget.Toast;

/**
 * Created by Saina on 3/6/2015.
 */
public class CarRent extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Light.ttf");
    }
}
