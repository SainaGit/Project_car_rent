package sdeveloper.car.rent.tutorial;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import com.viewpagerindicator.CirclePageIndicator;

import sdeveloper.car.rent.R;
import sdeveloper.car.rent.application.RootActivity;
import sdeveloper.car.rent.login.ActivityLogin;
import sdeveloper.car.rent.splashscreen.ActivitySplashScreen;

public class ActivityTutorial extends RootActivity {

    private ViewPager viewPagerTutorial;
    private AdapterTutorialViewPager adapterTutorialViewPager;
    private CirclePageIndicator circlePageIndicator;
    private Button tutButtonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ActivitySplashScreen.singleton.setIsFirstTime(false);
        circlePageIndicator = (CirclePageIndicator) findViewById(R.id.tutorial_viewpagerindicator);
        tutButtonLogin = (Button) findViewById(R.id.tutorial_loginbutton);
        viewPagerTutorial = (ViewPager) findViewById(R.id.viewpager_tutorial);
        adapterTutorialViewPager = new AdapterTutorialViewPager(this);
        viewPagerTutorial.setAdapter(adapterTutorialViewPager);
        circlePageIndicator.setViewPager(viewPagerTutorial);
        setTutorialDatas();

        tutButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTutorial.this, ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setTutorialDatas() {
        for(int i = 0; i < 3; i++) {
            adapterTutorialViewPager.mResources.add(i, R.mipmap.ic_noimage_round);
        }
        adapterTutorialViewPager.notifyDataSetChanged();

        circlePageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if(position == 2) {
                    tutButtonLogin.setVisibility(View.VISIBLE);
                } else {
                    tutButtonLogin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}
