package com.nic.moef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import residemenu.ResideMenu;
import residemenu.ResideMenuItem;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ResideMenu resideMenu;
    private MainActivity mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // attach to current activity;
        mContext = this;
        setUpMenu();
        if (savedInstanceState == null)
            changeFragment(new HomeFragment());

        ListView listView=resideMenu.getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        changeFragment(new HomeFragment());
                        break;
                    case 10:
                        changeFragment(new SettingsFragment());
                        break;
                }

                resideMenu.closeMenu();
            }
        });
    }

    private void setUpMenu() {
        // attach to current activity;
        resideMenu = new ResideMenu(this);
//        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.drawable.bg);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.5f);
        // create menu items;
//        itemHome = new ResideMenuItem(this);
//        for(int i=0;i<13;i++){
//            itemProfile = new ResideMenuItem(this, icons[i], MainActivity.this.getResources().getStringArray(R.array.SideMenu_Hindi)[i]);
//            resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
//        }
//
//        itemProfile.setOnClickListener(this);
//

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
    }

    @Override
    public void onClick(View view) {
//        if (view == itemHome) {
//            changeFragment(new HomeFragment());
//        } else if (view == itemProfile) {
//            changeFragment(new ProfileFragment());
//        } else if (view == itemCalendar) {
//            changeFragment(new CalendarFragment());
//        } else if (view == itemSettings) {
//            changeFragment(new SettingsFragment());
//        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
//            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
//            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment) {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu() {
        return resideMenu;
    }
}
