package sdeveloper.car.rent.login;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sdeveloper.car.rent.R;
import sdeveloper.car.rent.application.RootActivity;
import sdeveloper.car.rent.splashscreen.ActivitySplashScreen;

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

        if(counter > 0) {
            ActivitySplashScreen.singleton.showToast(ActivityLogin.this, "Талбарыг бүрэн бөглөнө үү !");
        } else {
            if(!String.valueOf(edittextUsername.getText()).equals(USERNAME)) {
                ActivitySplashScreen.singleton.showToast(ActivityLogin.this, "Хэрэглэгчийн нэр буруу байна !");
            } else {
                if(!String.valueOf(edittextPassword.getText()).equals(PASSWORD)) {
                    ActivitySplashScreen.singleton.showToast(ActivityLogin.this, "Нууц үг буруу байна !");
                } else {
                    //TODO Үндсэн Activity руу шилжих.
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

            }
        }
    }
}
