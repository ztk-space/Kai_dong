package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;
import com.kai.kaidong.util.CustomTitleBar;

public class EaseUiFeaturesActivity extends BaseActivity implements View.OnClickListener {
    private CustomTitleBar customTitleBar;
    private Button btn_go,btn_exit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        customTitleBar = findViewById(R.id.featurescustom);
        customTitleBar.setTitle("聊天功能");
        btn_go = findViewById(R.id.btn_go);
        btn_exit = findViewById(R.id.btn_exit);
        btn_go.setOnClickListener(this);
        btn_exit.setOnClickListener(this);

    }

    @Override
    protected int findView() {
        return R.layout.activity_ease_ui_features;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //发起聊天
            case R.id.btn_go:
                String chatId ="test-4fa4813a-7f06-4a2c-9f0f";
                if (!TextUtils.isEmpty(chatId)) {
                    // 跳转到聊天界面，开始聊天
                    Intent intent = new Intent(EaseUiFeaturesActivity.this, ECChatActivity.class);
                    // EaseUI封装的聊天界面需要这两个参数，聊天者的username，以及聊天类型，单聊还是群聊
                    intent.putExtra("userId", chatId);
                    intent.putExtra("chatType", EMMessage.ChatType.Chat);
                    startActivity(intent);
                } else {
                    Toast.makeText(EaseUiFeaturesActivity.this, "Username 不能为空", Toast.LENGTH_LONG).show();
                }
                break;
            //退出
            case R.id.btn_exit:
                signOut();
                break;
        }
    }

    private void signOut() {
        // 调用sdk的退出登录方法，第一个参数表示是否解绑推送的token，没有使用推送或者被踢都要传false
        EMClient.getInstance().logout(false, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.i("lzan13", "logout success");
                // 调用退出成功，结束app
                finish();
            }

            @Override
            public void onError(int i, String s) {
                Log.i("lzan13", "logout error " + i + " - " + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
