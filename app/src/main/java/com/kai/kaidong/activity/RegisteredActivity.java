package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kai.kaidong.MainActivity;
import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;
import com.kai.kaidong.sqLite.Helper;
import com.kai.kaidong.sqLite.Person;

import java.util.List;

public class RegisteredActivity extends BaseActivity implements View.OnClickListener {
    EditText etLoginPhone;
    EditText etLoginPwd;
    EditText getEtLoginPhone;
    Button etLoginSure;
    private Helper helper;
    private boolean see = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void init() {

            etLoginPhone = findViewById(R.id.et_login_name);
            etLoginPwd = findViewById(R.id.et_login_pwd);
            etLoginSure = findViewById(R.id.et_login_sure);
            getEtLoginPhone = findViewById(R.id.et_login_phone);
            etLoginSure.setOnClickListener(this);
    }

    @Override
    protected int findView() {
        return R.layout.activity_registered;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_login_sure :
                if(TextUtils.isEmpty(etLoginPhone.getText().toString())){
                    showToast("请输入手机号");
                }else if(TextUtils.isEmpty(etLoginPwd.getText().toString())){
                    showToast("请输入密码");
                }else {
                    helper = new Helper(this);
                    helper.add(etLoginPhone.getText().toString(),etLoginPwd.getText().toString(),getEtLoginPhone.getText().toString());
                    List<Person> persons = helper.getPersons();
                    if(persons.size()>0){
                        showToast("注册成功");
                        finish();
                    }
                }
                break;
            case R.id.rethere :
                if(see){
                    see = false;
                    etLoginPwd.setInputType(128);//
                }else {
                    see = true;
                    etLoginPwd.setInputType(129);//
                }

                break;
            case R.id.text_re:

                break;
        }
    }
}
