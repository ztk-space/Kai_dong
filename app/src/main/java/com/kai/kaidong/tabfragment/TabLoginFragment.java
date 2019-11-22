package com.kai.kaidong.tabfragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.kai.kaidong.MainActivity;
import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseFragment;


public class TabLoginFragment extends BaseFragment implements View.OnClickListener {

    EditText etLoginPhone;
    EditText etLoginPwd;
    Button etLoginSure;
    private RelativeLayout relativeLayout;
    private boolean see = false;
    @Override
    protected void initData() {


    }

    @Override
    protected void intView(View view) {
        etLoginPhone = view.findViewById(R.id.et_login_phone);
        etLoginPwd = view.findViewById(R.id.et_login_pwd);
        etLoginSure = view.findViewById(R.id.et_login_sure);
        relativeLayout = view.findViewById(R.id.rethere);
        etLoginSure.setOnClickListener(this);
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
                    startActivity(new Intent(getActivity(), MainActivity.class));
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
        }
    }
}
