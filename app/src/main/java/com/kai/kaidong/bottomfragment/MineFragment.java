package com.kai.kaidong.bottomfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kai.kaidong.R;
import com.kai.kaidong.activity.TablayoutActivity;
import com.kai.kaidong.activity.UpdateActivity;
import com.kai.kaidong.base.BaseFragment;
import com.kai.kaidong.sqLite.Helper;
import com.kai.kaidong.sqLite.Person;

import java.util.List;

public class MineFragment extends BaseFragment {

    private Helper helper;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private ImageView imageView;
    RelativeLayout relativeLayout;
    TextView textViewphone,textViewname;

    @Override
    protected void initData() {

    }

    @Override
    protected void intView(View view) {
        imageView = view.findViewById(R.id.image_setting);
        relativeLayout = view.findViewById(R.id.reqingchu);
        textViewname = view.findViewById(R.id.text_name);
        textViewphone = view.findViewById(R.id.text_phone); helper = new Helper(getActivity());
        List<Person> persons = helper.getPersons();
        for (int i = 0; i < persons.size(); i++) {
            String name = persons.get(i).getName();
            String phone = persons.get(i).getPhone();
             textViewphone.setText(phone);
             textViewname.setText(name);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UpdateActivity.class));
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper = new Helper(getActivity());
                List<Person> persons = helper.getPersons();
                for (int i = 0; i < persons.size(); i++) {
                    String name = persons.get(i).getName();
                    helper.delete(name);

                }
                List<Person> personsq = helper.getPersons();
                if (personsq.size() == 0) {
                    showToast("数据已清空，已返回到登录界面");
                    startActivity(new Intent(getActivity(), TablayoutActivity.class));
                }
            }
        });

    }

    @Override
    protected int initLayout() {
        return R.layout.minefragment;
    }
}
