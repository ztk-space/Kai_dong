package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment;
import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;
import com.kai.kaidong.tabfragment.TabRegisterediFragment;

//引用的事lib第三方

public class LibActivity extends BaseActivity {
    private Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        button = findViewById(R.id.btn_re);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabRegisterediFragment.DemoBottomSheetFragment demoBottomSheetFragment = new TabRegisterediFragment.DemoBottomSheetFragment();
                demoBottomSheetFragment.show(getSupportFragmentManager().beginTransaction(),"DemoBottomSheetFragment");
            }
        });
    }

    @Override
    protected int findView() {
        return R.layout.activity_lib;
    }
    class DemoBottomSheetFragment extends SuperBottomSheetFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            return inflater.inflate(R.layout.fragment_demo_sheet, container, false);
        }

        @Override
        public float getCornerRadius() {
            return LibActivity.this.getResources().getDimension(R.dimen.demo_sheet_rounded_corner);
        }
    }
}
