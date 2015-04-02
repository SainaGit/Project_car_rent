package sdeveloper.car.rent.tutorial;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import sdeveloper.car.rent.R;

/**
 * Created by Saina on 3/15/2015.
 */
public class AdapterTutorialViewPager extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<String> backgroundColors = new ArrayList<>();
    private ArrayList<String> tutStrings = new ArrayList<>();
    public ArrayList<Integer> mResources = new ArrayList<>();

    public AdapterTutorialViewPager(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        tutStrings = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.tutorial_strings)));
        backgroundColors = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.tutorial_background_colors)));
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String tutString = tutStrings.get(position);
        String backgroundColor = backgroundColors.get(position);
        int tutImageResource = mResources.get(position);

        View itemView = mLayoutInflater.inflate(R.layout.item_tutorial_viewpager, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view_tutorial_viewpager);
        TextView textView = (TextView) itemView.findViewById(R.id.tutorial_text);
        FrameLayout frameLayout = (FrameLayout) itemView.findViewById(R.id.tutorial_main_frame);
        frameLayout.setBackgroundColor(Color.parseColor(backgroundColor));
        imageView.setImageResource(tutImageResource);
        textView.setText(tutString);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
