package com.xys.study.chapter2.widget;

import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.xys.study.chapter2.R;

public class BarHeight extends AppCompatActivity{
    public float getStatusBarHeight() {
        float result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = getResources().getDimension(resourceId);
        return result;
    }

    private void setHight(float statusBarHight, LinearLayout statusBar) {
        statusBar = findViewById(R.id.statusBar);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) statusBar.getLayoutParams();
        params.height = (int)statusBarHight;
        statusBar.setLayoutParams(params);
    }
}
