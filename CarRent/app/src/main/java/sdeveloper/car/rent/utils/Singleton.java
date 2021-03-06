package sdeveloper.car.rent.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.widget.Toast;

import sdeveloper.car.rent.R;

/**
 * Created by Saina on 3/6/2015.
 */
public class Singleton {
    private static Singleton mInstance = null;
    private SharedPreferences sharedPreferencesUserDatas;
    private SharedPreferences.Editor editorUserDatas;
    private Context context;

    private Singleton(Context context){
        this.context = context;
        sharedPreferencesUserDatas = context.getSharedPreferences("sp_user_datas", Context.MODE_PRIVATE);
        editorUserDatas = sharedPreferencesUserDatas.edit();
    }

    public Typeface robotoMedium(Context  context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        return typeface;
    }

    public static Singleton getInstance(Context context){
        if(mInstance == null)
        {
            mInstance = new Singleton(context);
        }
        return mInstance;
    }

    public void setIsFirstTime(boolean isFirstTime) {
        editorUserDatas.putBoolean("user_datas_isfirst", isFirstTime);
        editorUserDatas.commit();
    }

    public boolean getIsFirst() {
        return sharedPreferencesUserDatas.getBoolean("user_datas_isfirst", true);
    }

    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
