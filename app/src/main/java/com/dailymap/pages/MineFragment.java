package com.dailymap.pages;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.dailymap.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MineFragment extends Fragment {

    public MineFragment(){}
    private Button btn_map;
    private ListView mPersonalInformationListView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.mine_page, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initView();
    }

    private void initView(View view) {
        mPersonalInformationListView=(ListView)view.findViewById(R.id.lv_personal_information);
    }


    private void initView()
    {

        int[] imageIdList = new int[]{R.drawable.find, R.drawable.find, R.drawable.find, R.drawable.find};

        String[] titleList = new String[]{"我的积分", "我的地图", "随记", "设置"};

        List<Map<String, Object>> messageItemsList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < imageIdList.length; i++)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageIdList[i]);
            map.put("title", titleList[i]);

            messageItemsList.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), messageItemsList, R.layout.
                personal_information_item, new String[]{"image", "title"}, new int[]{R.id.image, R.id.title});

        mPersonalInformationListView.setAdapter(adapter);

    }

}
