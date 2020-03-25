package com.xys.study.chapter2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.xys.study.chapter2.widget.SoftHideKeyBoardUtil;

public class Chatroom extends AppCompatActivity {
    private RelativeLayout statusBar;
    private String chat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_chatroom);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        float statusBarHight = getStatusBarHeight();
        setHight(statusBarHight);
        SoftHideKeyBoardUtil.assistActivity(this);

        Intent intent = getIntent();
        TextView tv_title = findViewById(R.id.tv_with_name);
        tv_title.setText(new StringBuilder("我和"+intent.getStringExtra("Title")+"的对话"));

        chat = "";
        final ImageView iv_send = findViewById(R.id.btn_send_info);
        final EditText et_say = findViewById(R.id.ed_say);
        final TextView content = findViewById(R.id.tv_content_info);
        iv_send.setEnabled(false);
        iv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String say = et_say.getText().toString();
                if(!say.equals("")){
                    chat += say + '\n';
                    content.setText(chat);
                    et_say.setText("");
                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable(){
                        @Override
                        public void run() {
                            chat += say.replace('你','我') + '\n';
                            content.setText(chat);
                        }
                    };
                    handler.postDelayed(runnable, 1000);
                }
            }
        });
        et_say.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(et_say.length()==0)
                    iv_send.setEnabled(false);
                else
                    iv_send.setEnabled(true);
            }
        });
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
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) statusBar.getLayoutParams();
        params.height = (int)statusBarHight;
        statusBar.setLayoutParams(params);
    }

}
