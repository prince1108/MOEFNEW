package com.nic.moef;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import adapters.CirclePageIndicator;
import adapters.CustomPagerAdapter;
import adapters.ReaderViewPagerTransformer;
import models.GuideLinesMetaData;


/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:33
 * Mail: specialcyci@gmail.com
 */
public class HomeFragment extends Fragment {
    CustomPagerAdapter mCustomPagerAdapter;
    private Handler handler = new Handler();
    ViewPager mViewPager;
    int position = 0;
    // the images to display
    int[] imageIDs = { R.drawable.menu, R.drawable.menu, R.drawable.menu,
            R.drawable.menu };
    private RelativeLayout newReleaseBtn,guideLinesBtn;
    private View parentView;
//    private ResideMenu resideMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.home, container, false);
        CirclePageIndicator indicator = (CirclePageIndicator) parentView.findViewById(R.id.indicat);
        mCustomPagerAdapter = new CustomPagerAdapter(getActivity());
        mViewPager = (ViewPager) parentView.findViewById(R.id.pager);
        newReleaseBtn=(RelativeLayout) parentView.findViewById(R.id.newReleaseBtn);
        guideLinesBtn=(RelativeLayout) parentView.findViewById(R.id.guidelinesbtn);
        mViewPager.setAdapter(mCustomPagerAdapter);
        mViewPager.setPageTransformer(false, new ReaderViewPagerTransformer(ReaderViewPagerTransformer.TransformType.DEPTH));
        indicator.setViewPager(mViewPager);

        newReleaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),NewReleaseActivity.class);
                startActivity(intent);
            }
        });
        guideLinesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), GuideLineActivity.class);
                startActivity(intent);
            }
        });
        return parentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    Runnable runnable = new Runnable() {
        public void run() {
            if (position == 4) {
                position = 0;
            }
            mViewPager.setCurrentItem(position++, true);
            handler.postDelayed(runnable, 3000);
        }
    };
    //    private void setUpViews() {
//        MainActivity parentActivity = (MainActivity) getActivity();
//        resideMenu = parentActivity.getResideMenu();
//        // add gesture operation's ignored views
//    }

}
