package sdeveloper.car.rent.login;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sdeveloper.car.rent.R;
import sdeveloper.car.rent.application.RootActivity;
import sdeveloper.car.rent.main.MainActivity;
import sdeveloper.car.rent.register.ActivityRegister;
import sdeveloper.car.rent.splashscreen.ActivitySplashScreen;
import sdeveloper.car.rent.user.User;

public class ActivityLogin extends RootActivity implements View.OnClickListener {
    private String USERNAME = "ulemj";
    private String PASSWORD = "12345";

    private Button buttonLogin;
    private Button buttonRegister;
    private EditText edittextUsername;
    private EditText edittextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_login);

        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonRegister = (Button) findViewById(R.id.button_register);
        edittextUsername = (EditText) findViewById(R.id.edit_text_username);
        edittextPassword = (EditText) findViewById(R.id.edit_text_password);

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }

    private void editTextIsFilled() {
        int counter = 0;
        if(edittextUsername.getText().length() == 0) {
            edittextUsername.setBackgroundResource(R.drawable.framebutton_border_red);
            counter++;
        } else {
            edittextUsername.setBackgroundResource(R.drawable.framebutton_border);
        }

        if(edittextPassword.getText().length() == 0) {
            edittextPassword.setBackgroundResource(R.drawable.framebutton_border_red);
            counter++;
        } else {
            edittextPassword.setBackgroundResource(R.drawable.framebutton_border);
        }

        //TODO Сервер лүү нэвтрэх хүсэлт явуулах.
        if(counter > 0) {
            ActivitySplashScreen.singleton.showToast(ActivityLogin.this, "Талбарыг гүйцэд бөглөнө үү !");
        } else {
            if(!String.valueOf(edittextUsername.getText()).equals(USERNAME)) {
                ActivitySplashScreen.singleton.showToast(ActivityLogin.this, "Хэрэглэгчийн нэр буруу байна !");
                edittextUsername.setBackgroundResource(R.drawable.framebutton_border_red);
            } else {
                edittextUsername.setBackgroundResource(R.drawable.framebutton_border);
                if(!String.valueOf(edittextPassword.getText()).equals(PASSWORD)) {
                    ActivitySplashScreen.singleton.showToast(ActivityLogin.this, "Нууц үг буруу байна !");
                    edittextPassword.setBackgroundResource(R.drawable.framebutton_border_red);
                } else {
                    //TODO Үндсэн Activity руу шилжих.
                    User user = new User();
                    user.setUserName(USERNAME);
                    //TODO base64
                    user.setUserPicture("");
                    Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login : {
                editTextIsFilled();
                break;
            }
            case R.id.button_register : {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        }
    }
}
