package com.xys.study.chapter2;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.FrameLayout;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise2);

        View view = this.findViewById(android.R.id.content);

        TextView tv = findViewById(R.id.tv_center);
        tv.setText(String.valueOf(getAllChildViewCount(view)));
    }

    public int getAllChildViewCount(View view) {
        if (null == view)
            return 0;

        int count = 0;
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                if (child instanceof ViewGroup)
                    count += getAllChildViewCount(child);
                else
                    count++;
            }
        }
        else
            count++;

        return count;
    }
}
