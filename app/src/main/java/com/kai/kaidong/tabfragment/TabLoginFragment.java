package com.kai.kaidong.tabfragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kai.kaidong.MainActivity;
import com.kai.kaidong.R;
import com.kai.kaidong.activity.RegisteredActivity;
import com.kai.kaidong.base.BaseFragment;
import com.kai.kaidong.sqLite.Helper;
import com.kai.kaidong.sqLite.Person;

import java.util.List;


public class TabLoginFragment extends BaseFragment implements View.OnClickListener {

    EditText etLoginPhone;
    EditText etLoginPwd;
    Button etLoginSure;
    private RelativeLayout relativeLayout;
    private TextView textView;
    private boolean see = false;
    private Helper helper;
    private  boolean state = true;

    @Override
    protected void initData() {


    }

    @Override
    protected void intView(View view) {
        etLoginPhone = view.findViewById(R.id.et_login_phone);
        etLoginPwd = view.findViewById(R.id.et_login_pwd);
        etLoginSure = view.findViewById(R.id.et_login_sure);
        relativeLayout = view.findViewById(R.id.rethere);
        textView = view.findViewById(R.id.text_re);
        etLoginSure.setOnClickListener(this);
        textView.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.loginfragment;
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
                    helper = new Helper(getActivity());
                    List<Person> persons = helper.getPersons();
                    if(persons.size()<1){
                        showToast("您还未注册账号,请先注册");
                    }
                    for (int i = 0;i<persons.size();i++){
                        String name = persons.get(i).getName();
                        String pwd = persons.get(i).getPwd();
                        if(etLoginPhone.getText().toString().equals(name) && etLoginPwd.getText().toString().equals(pwd)){
                            state = true;
                            startActivity(new Intent(getActivity(), MainActivity.class));
                        }else {
                            state = false;
                        }
                    }
                }
                if (state==false){
                    showToast("账号密码不正确");
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
                   startActivity(new Intent(getActivity(), RegisteredActivity.class));
                break;
        }
    }
}
