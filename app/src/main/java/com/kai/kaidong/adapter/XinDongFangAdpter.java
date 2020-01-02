package com.kai.kaidong.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kai.kaidong.R;
import com.kai.kaidong.util.PopUtil;

import java.util.ArrayList;
import java.util.List;

public  class XinDongFangAdpter extends RecyclerView.Adapter<XinDongFangAdpter.XinDongFangHolder>{

    private Context context;
    private List<String> list = new ArrayList<>();
    private boolean aBoolean = true;
    private OnClickListeners onClickListeners;
    public XinDongFangAdpter(Context context, List<String> list, boolean aBoolean) {
        this.context = context;
        this.list = list;
        this.aBoolean = aBoolean;
    }
    public void setOnClickListeners(OnClickListeners onClickListeners) {
        this.onClickListeners = onClickListeners;
    }

    @NonNull
    @Override
    public XinDongFangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new XinDongFangHolder(LinearLayout.inflate(context, R.layout.xindongfanglayout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull XinDongFangHolder holder, int position) {
        Log.i("TAG",aBoolean+"========");
        holder.textView.setText(list.get(position));
        if(aBoolean == false){
            holder.imageView.setVisibility(View.VISIBLE);
        }else {
            holder.imageView.setVisibility(View.INVISIBLE);
        }
        Log.i("TAG",onClickListeners+"========");
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG",onClickListeners+"========");
               // Toast.makeText(context,"点击了"+position,Toast.LENGTH_LONG).show;
                PopUtil.getInstance().makePopupWindow((Activity) context,R.layout.xindongfanglayout,R.layout.xindongfangpopu, Gravity.BOTTOM);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   public interface OnClickListeners{
        void setOnClick();
    }

    public class XinDongFangHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;

        public XinDongFangHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.xindongfangtext);
            imageView = itemView.findViewById(R.id.imageone);
        }
    }



}