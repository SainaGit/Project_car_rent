package sdeveloper.car.rent.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import sdeveloper.car.rent.R;
import sdeveloper.car.rent.application.RootActivity;
import sdeveloper.car.rent.user.User;

public class MainActivity extends RootActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (User) getIntent().getSerializableExtra("user");

        AccountHeader.Result headerResult = new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.header)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(user.getUserName())
                                .withIcon(getResources().getDrawable(R.mipmap.ic_profile_image))
                                .withEmail("ulemj@gmail.com")
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        Drawer.Result result = new Drawer()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Захиалга"),
                        new PrimaryDrawerItem().withName("Машинууд"),
                        new PrimaryDrawerItem().withName("Захиалгын түүх"),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Тухай")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                    }
                })
                .build();
    }
}
