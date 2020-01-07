package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;
import com.kai.kaidong.sqLite.Helper;
import com.kai.kaidong.sqLite.Person;

import java.util.List;

public class UpdateActivity extends BaseActivity implements View.OnClickListener {
    EditText etLoginPhone;
    EditText etLoginPwd;
    Button etLoginSure;
    EditText etLoginPwdnew;
    private boolean see = false;
    private Helper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void init() {
        etLoginPhone = findViewById(R.id.et_login_phone);
        etLoginPwd = findViewById(R.id.et_login_pwd);
        etLoginPwdnew = findViewById(R.id.et_login_pwdnew);
        etLoginSure = findViewById(R.id.et_login_sure);
        etLoginSure.setOnClickListener(this);
    }

    @Override
    protected int findView() {
        return R.layout.activity_update;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_login_sure :
                if(TextUtils.isEmpty(etLoginPhone.getText().toString())){
                    showToast("请输入手机号");
                }else if(TextUtils.isEmpty(etLoginPwd.getText().toString())){
                    showToast("请输入原来的密码");
                }else if(TextUtils.isEmpty(etLoginPwdnew.getText().toString())){
                    showToast("请输入新密码");
                }
                else {
                    helper = new Helper(this);
                    helper.update(etLoginPwdnew.getText().toString(),etLoginPwd.getText().toString());
                    List<Person> persons = helper.getPersons();

                    for (int i = 0;i<persons.size();i++){
                        Log.i("TAG",persons.get(i).getPwd());
                        String pwd = persons.get(i).getPwd();
                        if(etLoginPwdnew.getText().toString().equals(pwd)){
                        showToast("修改成功");
                        finish();
                        }
                    }

                }
                break;
            case R.id.rethere :
                if(see){
                    see = false;
                    etLoginPwd.setInputType(128);//
                    etLoginPwdnew.setInputType(128);
                }else {
                    see = true;
                    etLoginPwd.setInputType(129);//
                    etLoginPwdnew.setInputType(129);
                }

                break;
            case R.id.text_re:

                break;
        }
    }
}
