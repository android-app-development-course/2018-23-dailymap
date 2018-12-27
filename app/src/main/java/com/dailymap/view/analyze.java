package com.dailymap.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dailymap.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class analyze extends AppCompatActivity {


    private BarChart mBarChart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        initView();
    }

    //初始化
    private void initView() {




        mBarChart = (BarChart) findViewById(R.id.mBarChart);

        //设置描述
        mBarChart.getDescription().setEnabled(false);
        mBarChart.setMaxVisibleValueCount(60);
        //按比例缩放
        mBarChart.setPinchZoom(true);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawGridBackground(false);

        //得到X轴
        XAxis xAxis = mBarChart.getXAxis();
        //设置X轴的位置(默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelCount(3);
        xAxis.setDrawLabels(true);
        xAxis.setTextSize(20f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                List<String> mList=new ArrayList<>();
                mList.add("季节");
                mList.add("距离");
                mList.add("频率");
                return mList.get((int)value);
            }
        });

        YAxis yAxisl=mBarChart.getAxisLeft();
        yAxisl.setTextSize(20f);

        mBarChart.getAxisLeft().setDrawGridLines(false);
        mBarChart.animateY(2500);
        mBarChart.getLegend().setEnabled(false);

        YAxis yAxisr=mBarChart.getAxisRight();
        yAxisr.setTextSize(20f);

        mBarChart.getAxisRight().setDrawGridLines(false);
        mBarChart.animateY(2500);
        mBarChart.getLegend().setEnabled(false);

        setData();
    }

    //设置数据
    private void setData() {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < 3; i++) {
            float mult = 50;
            float val = (float) (Math.random() * mult) + mult / 3;
            yVals1.add(new BarEntry(i, val));
        }
        BarDataSet set1;


        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "旅行数据分析");
            //设置多彩 也可以单一颜色
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            mBarChart.setData(data);
            mBarChart.setFitBars(true);
        }
        for (IDataSet set : mBarChart.getData().getDataSets())
        {
            set.setDrawValues(!set.isDrawValuesEnabled());
            set.setValueTextSize(20f);
        }
        mBarChart.invalidate();
    }

}
