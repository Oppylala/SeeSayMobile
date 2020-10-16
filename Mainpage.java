package com.example.user.emergencyapps;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Mainpage extends AppCompatActivity {
    private ViewPager slide_ViewPager;
    private LinearLayout dotsLayout;

    private SliderAdapter sliderAdapter;
    private TextView mDots[];

    private Button Finish;
    private int CurrentPage;
    private ImageView Backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        slide_ViewPager = findViewById(R.id.slide_ViewPager);
        dotsLayout = findViewById(R.id.dotsLayout);

        Finish = findViewById(R.id.finish);

        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(Mainpage.this, Loginpage.class));
            }
        });

        sliderAdapter = new SliderAdapter(this);
        slide_ViewPager.setAdapter(sliderAdapter);



        addDotsindicator(0);
        slide_ViewPager.addOnPageChangeListener(viewListener);

    }
    private void addDotsindicator(int position){
        mDots = new TextView[3];
        dotsLayout.removeAllViews();

        for(int i=0; i<mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

            dotsLayout.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.Special));
        }


    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsindicator(position);

            CurrentPage = position;
            if(position > 1){
                Finish.setVisibility(View.VISIBLE);

            }

            else{
                Finish.setVisibility(View.INVISIBLE);

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
