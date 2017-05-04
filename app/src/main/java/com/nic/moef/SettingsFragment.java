package com.nic.moef;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import utility.Util;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午3:28
 * Mail: specialcyci@gmail.com
 */
public class SettingsFragment extends Fragment {
    private View parentView;
    ToggleButton togglebutton;
    SharedPreferences mUserSettings;
    private TextView langText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.settings, container, false);
        togglebutton = (ToggleButton) parentView.findViewById(R.id.togglebutton);
        langText= (TextView) parentView.findViewById(R.id.langText);
        mUserSettings = getActivity().getSharedPreferences(Util.PREFS_NAME, Context.MODE_PRIVATE);
        if(Util.changeLanguage(getActivity())==1){
            togglebutton.setChecked(true);
            togglebutton.setText("English");
        }else{
            togglebutton.setChecked(false);
            togglebutton.setText("Hindi");
        }
        togglebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (togglebutton.isChecked()) {
                    togglebutton.setText("English");
                    SharedPreferences.Editor editor;
                    editor = mUserSettings.edit(); //2
                    editor.putInt("Language", 1); //3
                    editor.commit(); //4
                    startActivity(new Intent(getActivity(),MainActivity.class));
                    getActivity().finish();
//                    Toast.makeText(getActivity(), "ON", Toast.LENGTH_SHORT).show();
                } else {
                    togglebutton.setText("Hindi");
                    SharedPreferences.Editor editor;
                    editor = mUserSettings.edit(); //2
                    editor.putInt("Language", 2); //3
                    editor.commit(); //4
                    startActivity(new Intent(getActivity(),MainActivity.class));
                    getActivity().finish();
//                    Toast.makeText(getActivity(), "OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return parentView;
    }
}
