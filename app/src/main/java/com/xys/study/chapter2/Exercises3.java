package com.xys.study.chapter2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.io.InputStream;
import java.util.List;
import com.xys.study.chapter2.model.Message;
import com.xys.study.chapter2.model.PullParser;
import com.xys.study.chapter2.widget.MyAdapter;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity implements MyAdapter.ListItemClickListener {
    private MyAdapter mAdapter;
    private RecyclerView myListView;
    private LinearLayout statusBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tips);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        float statusBarHight = getStatusBarHeight();
        setHight(statusBarHight);

        myListView = findViewById(R.id.rv_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myListView.setLayoutManager(layoutManager);

        try
        {
            InputStream assetInput = getAssets().open("data.xml");
            List<Message> messages = PullParser.pull2xml(assetInput);
            mAdapter = new MyAdapter(messages,this);
            myListView.setAdapter(mAdapter);
        }
        catch (Exception exception) {
             exception.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(int clickedItemIndex, String title) {
        Intent intent = new Intent(this, Chatroom.class);
        intent.putExtra("Title",title);
        startActivity(intent);
    }

    public float getStatusBarHeight() {
        float result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = getResources().getDimension(resourceId);
        return result;
    }

    private void setHight(float statusBarHight) {
        statusBar = findViewById(R.id.statusBar);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) statusBar.getLayoutParams();
        params.height = (int)statusBarHight;
        statusBar.setLayoutParams(params);
    }
}
