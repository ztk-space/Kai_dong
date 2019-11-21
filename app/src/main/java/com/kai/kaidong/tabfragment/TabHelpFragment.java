package com.kai.kaidong.tabfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import mobile.sarproj.com.layout.SwipeLayout;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

public class TabHelpFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private List<String> list;
    @Override
    protected void initData() {
     recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
     recyclerView.setAdapter(new HelpRecyAdpter(list,mContext));
    }

    @Override
    protected void intView(View view) {
     recyclerView = view.findViewById(R.id.recy);
     list = new ArrayList<>();
     for (int i = 0;i<10;i++){
         list.add("赵腾开"+i);
     }
    }

    @Override
    protected int initLayout() {
        return R.layout.helpfragment;
    }

    public class HelpRecyAdpter extends RecyclerView.Adapter<HelpRecyAdpter.HelpHolder>{

        private List<String> list;
        private Context context;

        public HelpRecyAdpter(List<String> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public HelpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new HelpHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.helplayout, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull HelpHolder holder, int position) {
          holder.dragItem.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private void remove(Context context, int position) {
           list.remove(position);
           notifyDataSetChanged();
        }

        private void upload(Context context, int position) {
            Toast.makeText(context, "upload item " + position, Toast.LENGTH_SHORT).show();
        }

        public class HelpHolder extends RecyclerView.ViewHolder{

            TextView dragItem;
            ImageView leftView;
            ImageView rightView;
            SwipeLayout swipeLayout;

            public HelpHolder(@NonNull final View itemView) {
                super(itemView);
                dragItem = itemView.findViewById(R.id.drag_item);
                swipeLayout = itemView.findViewById(R.id.swipe_layout);
                leftView = itemView.findViewById(R.id.left_view);
                rightView = itemView.findViewById(R.id.right_view);
                rightView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getAdapterPosition() != NO_POSITION) {
                            remove(itemView.getContext(), getAdapterPosition());
                        }
                    }
                });

                leftView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getAdapterPosition() != NO_POSITION) {
                            upload(itemView.getContext(), getAdapterPosition());
                        }
                    }
                });
            }

        }

    }
}
