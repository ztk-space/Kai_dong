package com.kai.kaidong.tabfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment;
import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseFragment;

public class TabRegisterediFragment extends BaseFragment {
    private Button button;
    @Override
    protected void initData() {
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DemoBottomSheetFragment demoBottomSheetFragment = new DemoBottomSheetFragment();
            demoBottomSheetFragment.show(getActivity().getSupportFragmentManager().beginTransaction(),"DemoBottomSheetFragment");
        }
    });
    }

    @Override
    protected void intView(View view) {
      button = view.findViewById(R.id.btn_re);
    }

    @Override
    protected int initLayout() {
        return R.layout.registeredfragment;
    }
    public static class DemoBottomSheetFragment extends SuperBottomSheetFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            return inflater.inflate(R.layout.fragment_demo_sheet, container, false);
        }

    }
}
