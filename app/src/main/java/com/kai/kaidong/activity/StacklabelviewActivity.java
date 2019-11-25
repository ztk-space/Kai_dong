package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.kai.kaidong.MainActivity;
import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;
import com.kai.kaidong.util.CustomTitleBar;
import com.kongzue.stacklabelview.StackLabel;
import com.kongzue.stacklabelview.interfaces.OnLabelClickListener;

import java.util.ArrayList;
import java.util.List;

public class StacklabelviewActivity extends BaseActivity {
    //引用第三方stacklabelview

    private StackLabel stackLabelView;
    private List<String> labels = new ArrayList<>();
    private Switch switchDelete;
    private CustomTitleBar customTitleBar;
    private ImageView imageView;
    private ImageView imageViewadd;
    private Switch switchSelect;
    private EditText editText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        stackLabelView = findViewById(R.id.stackLabelView);
        switchDelete = findViewById(R.id.switchDelete);
        customTitleBar = findViewById(R.id.stack_custom);
        switchSelect = findViewById(R.id.switchSelect);
        imageView = findViewById(R.id.stack_image);
        imageViewadd = findViewById(R.id.stack_image_add);
        editText = findViewById(R.id.stack_ed);
        customTitleBar.setTitle("Stacklabelview");
        labels.add("花哪儿记账");
        labels.add("给未来写封信");
        labels.add("密码键盘");
        labels.add("抬手唤醒");
        labels.add("Cutisan");
        labels.add("记-专注创作");
        labels.add("我也不知道我是谁");
        labels.add("崩崩崩");
        labels.add("Android");
        labels.add("开发");
        stackLabelView.setLabels(labels);
        stackLabelView.setOnLabelClickListener(new OnLabelClickListener() {
            @Override
            public void onClick(int index, View v, String s) {
                if (switchDelete.isChecked()) {
                    labels.remove(index);
                    stackLabelView.setLabels(labels);
                } else {
                    Toast.makeText(StacklabelviewActivity.this, "点击了：" + s, Toast.LENGTH_SHORT).show();
                    if (stackLabelView.isSelectMode()) {
                        for (int i : stackLabelView.getSelectIndexList()) {
                            Log.i(">>>", "select: " + i);
                        }
                    }
                }
            }
        });
        switchSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                List<String> selectLabels = new ArrayList<>();
                selectLabels.add("Android");
                selectLabels.add("Cutisan");
                selectLabels.add("密码键盘");
                stackLabelView.setSelectMode(isChecked, selectLabels);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labels.clear();
                //这个方法起的是刷新作用  无论是添加，删除之后都得调用此方法
                stackLabelView.setLabels(labels);
            }
        });
        imageViewadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed = editText.getText().toString();
                if(TextUtils.isEmpty(ed)){
                    labels.add(ed);
                    stackLabelView.setLabels(labels);
                }else {
                    showToast("请输入内容");
                }

                //这个方法起的是刷新作用  无论是添加，删除之后都得调用此方法

            }
        });
    }

    @Override
    protected int findView() {
        return R.layout.activity_stacklabelview;
    }
}
