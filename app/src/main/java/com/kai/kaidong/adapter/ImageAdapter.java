package com.kai.kaidong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kai.kaidong.R;

import java.io.File;

public class ImageAdapter extends ListBaseAdapter<File> {
    private LayoutInflater mLayoutInflater;

    // 图片的点击事件
    public interface OnPicClickListener {
        void onPicClick();
    }

    private OnPicClickListener mOnPicClickListener;

    public void setOnPicClickListener(OnPicClickListener mOnPicClickListener) {
        this.mOnPicClickListener = mOnPicClickListener;
    }

    public ImageAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mDataList.size() + 1;//比集合里的数据多一条，显示自定义的添加图片按钮
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_image_seclect, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final int mPosition = viewHolder.getAdapterPosition();
        if (mPosition == getItemCount() - 1) {//数据的最后一条（显示的添加图片的那个按钮）
            viewHolder.ivPic.setBackgroundResource(R.drawable.takeapicture);
            if (mOnPicClickListener != null) {
                viewHolder.ivPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnPicClickListener.onPicClick();
                    }
                });
            }
        } else {
            File fileImage = mDataList.get(mPosition);
            viewHolder.ivPic.setBackgroundResource(R.drawable.back);
            Glide.with(mContext).load(fileImage).into(viewHolder.ivPic);
            viewHolder.ivPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击的图片", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPic;

        ViewHolder(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_imageSelect_item_img);
        }
    }
}