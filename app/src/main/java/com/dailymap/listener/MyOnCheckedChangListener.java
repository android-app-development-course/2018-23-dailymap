package com.dailymap.listener;

import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.dailymap.R;

public class MyOnCheckedChangListener implements CompoundButton.OnCheckedChangeListener {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int id=compoundButton.getId();
        switch (id){
            case R.id.showflagline:
                break;
            case R.id.showfootline:
                break;
                default:
                    break;
        }
    }
}
