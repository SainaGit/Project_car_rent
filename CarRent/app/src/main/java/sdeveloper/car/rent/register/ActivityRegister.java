package sdeveloper.car.rent.register;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import sdeveloper.car.rent.R;
import sdeveloper.car.rent.application.RootActivity;
import sdeveloper.car.rent.splashscreen.ActivitySplashScreen;
import sdeveloper.car.rent.utils.Utility;

public class ActivityRegister extends RootActivity implements View.OnClickListener{

    private TextView textViewTitle;
    private FrameLayout frameInsertImage;
    private ImageView imageViewLogo;
    private ImageView imageViewProfilepicture;
    private EditText mailAddress;
    private EditText username;
    private EditText password;
    private EditText passwordRepeat;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.linear_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textViewTitle = (TextView) findViewById(R.id.textview_title);
        textViewTitle.setTypeface(ActivitySplashScreen.singleton.robotoMedium(ActivityRegister.this));
        frameInsertImage = (FrameLayout) findViewById(R.id.frame_register_insertimage);
        imageViewLogo = (ImageView) findViewById(R.id.imageview_register_profilelogo);
        imageViewProfilepicture = (ImageView) findViewById(R.id.imageview_register_profile);
        mailAddress = (EditText) findViewById(R.id.edittext_register_mail);
        username = (EditText) findViewById(R.id.edittext_register_username);
        password = (EditText) findViewById(R.id.edittext_register_password);
        passwordRepeat = (EditText) findViewById(R.id.edittext_register_password2);
        buttonRegister = (Button) findViewById(R.id.button_register_action);

        frameInsertImage.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Utility.GALLERY_PICTURE) {
            // data contains result
            // Do some task
            Image_Selecting_Task(data);
        } else if (requestCode == Utility.CAMERA_PICTURE) {
            // Do some task
            Image_Selecting_Task(data);
        }
    }

    public void Image_Picker_Dialog() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
        myAlertDialog.setTitle("Зураг оруулах");
        myAlertDialog.setMessage("Зургаа хаанаас оруулахаа сонгох");

        myAlertDialog.setPositiveButton("Зургын сан", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Utility.pictureActionIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
                Utility.pictureActionIntent.setType("image/*");
                Utility.pictureActionIntent.putExtra("return-data", true);
                startActivityForResult(Utility.pictureActionIntent, Utility.GALLERY_PICTURE);
            }
        });

        myAlertDialog.setNegativeButton("Камер", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Utility.pictureActionIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(Utility.pictureActionIntent, Utility.CAMERA_PICTURE);
            }
        });
        myAlertDialog.show();
    }

    public void Image_Selecting_Task(Intent data) {
        try {
            Utility.uri = data.getData();
            if (Utility.uri != null) {
                // User had pick an image.
                Cursor cursor = getContentResolver().query(Utility.uri, new String[]
                        {android.provider.MediaStore.Images.ImageColumns.DATA}, null, null, null);
                cursor.moveToFirst();
                // Link to the image
                final String imageFilePath = cursor.getString(0);

                //Assign string path to File
                Utility.Default_DIR = new File(imageFilePath);

                // Create new dir MY_IMAGES_DIR if not created and copy image into that dir and store that image path in valid_photo
                Utility.Create_MY_IMAGES_DIR();

                // Copy your image
                Utility.copyFile(Utility.Default_DIR, Utility.MY_IMG_DIR);

                // Get new image path and decode it
                Bitmap b = Utility.decodeFile(Utility.Paste_Target_Location);

                // use new copied path and use anywhere
                b = Bitmap.createScaledBitmap(b, 150, 150, true);

                //set your selected image in image view
                imageViewLogo.setVisibility(View.GONE);
                imageViewProfilepicture.setVisibility(View.VISIBLE);
                imageViewProfilepicture.setImageBitmap(b);
                cursor.close();

            } else {
                ActivitySplashScreen.singleton.showToast(ActivityRegister.this, "Уучлаарай!!! Та ямар нэгэн зураг сонгоогүй байна.");
            }
        } catch (Exception e) {
            // you get this when you will not select any single image
            Log.e("onActivityResult", "" + e);
        }
    }

    private void editTextIsFilled() {
        boolean isFilled = true;
        if(mailAddress.getText().length() == 0) {
            mailAddress.setBackgroundResource(R.drawable.framebutton_border_red);
           isFilled = false;
        } else {
            mailAddress.setBackgroundResource(R.drawable.framebutton_border);
        }
        if(username.getText().length() == 0) {
            username.setBackgroundResource(R.drawable.framebutton_border_red);
            isFilled = false;
        } else {
            username.setBackgroundResource(R.drawable.framebutton_border);
        }
        if(password.getText().length() == 0) {
            password.setBackgroundResource(R.drawable.framebutton_border_red);
            isFilled = false;
        } else {
            password.setBackgroundResource(R.drawable.framebutton_border);
        }
        if(passwordRepeat.getText().length() == 0) {
            passwordRepeat.setBackgroundResource(R.drawable.framebutton_border_red);
            isFilled = false;
        } else {
            passwordRepeat.setBackgroundResource(R.drawable.framebutton_border);
        }

        if(isFilled) {
            if(String.valueOf(password.getText()).equals(String.valueOf(passwordRepeat.getText()))) {
                //TODO Серверт бүртгүүлэх хүсэлт
                ActivitySplashScreen.singleton.showToast(ActivityRegister.this, "Амжилттай бүртгэгдлээ !");
                finish();
            } else {
                ActivitySplashScreen.singleton.showToast(ActivityRegister.this, "Нууц үг тохирохгүй байна.");
                password.setBackgroundResource(R.drawable.framebutton_border_red);
                passwordRepeat.setBackgroundResource(R.drawable.framebutton_border_red);
            }
        } else {
            ActivitySplashScreen.singleton.showToast(ActivityRegister.this, "Талбарыг гүйцэд бөглөнө үү !");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frame_register_insertimage : {
                Image_Picker_Dialog();
                break;
            }
            case R.id.button_register_action : {
                editTextIsFilled();
                break;
            }
        }
    }
}
