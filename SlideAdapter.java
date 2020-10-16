package com.example.user.emergencyapps;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by user on 10/1/2020.
 */

class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //Array
    public int[] slide_image ={
            R.drawable.armed,
            R.drawable.lock,
            R.drawable.medical
    };
    public String[] slide_Heading = {
            "Welcome to SeeSay",
            "You are almost there",
            "Congratulation"
    };
    public String[] slide_Description = {
            "You are welcome to SeeSay Apps, Report all Crimes here, Swipe Left to Continue",
            "Swipe Left Once Again! to get Started",
            "Now you can get started"
    };

    @Override
    public int getCount() {
        return slide_Heading.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageview = view.findViewById(R.id.slide_image);
        TextView slideHeadinng = view.findViewById(R.id.slide_heading);
        TextView slideDesc = view.findViewById(R.id.slide_description);

        slideImageview.setImageResource(slide_image[position]);
        slideHeadinng.setText(slide_Heading[position]);
        slideDesc.setText(slide_Description[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
