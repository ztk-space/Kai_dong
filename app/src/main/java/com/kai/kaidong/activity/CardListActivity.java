package com.kai.kaidong.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        list.clear();
        list.add("https://cdn.pixabay.com/photo/2016/05/16/17/59/strawberries-1396330_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2018/02/08/22/27/flower-3140492_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2018/02/07/17/53/poppy-3137588_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2018/02/06/14/07/dance-3134828_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2012/04/26/22/31/substances-43354_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2017/06/06/22/46/mediterranean-cuisine-2378758_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2018/02/01/19/21/easter-3123834_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2017/11/29/09/15/paint-2985569_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2017/11/18/17/09/strawberry-2960533_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2017/11/05/00/46/flower-2919284_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2017/09/25/20/44/peppers-2786684_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2017/09/01/21/53/blue-2705642_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2017/05/31/18/38/sea-2361247_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2018/02/02/22/28/nature-3126513_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2018/01/28/21/14/lens-3114729_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2018/01/27/05/49/woman-3110483_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2018/01/31/16/27/sea-3121435_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2018/01/31/12/16/architecture-3121009_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2018/01/28/14/41/bird-3113835_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2018/01/28/17/48/gallery-3114279_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2017/11/26/19/50/jeans-2979818_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2017/12/15/13/51/polynesia-3021072_640.jpg");
        list.add("https://cdn.pixabay.com/photo/2016/11/13/00/40/girl-1820122_640.jpg");
        recyclerView = findViewById(R.id.cardlist_recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardListAdpter(list,this));
    }

    @Override
    protected int findView() {
        return R.layout.activity_card_list;
    }

    public class CardListAdpter extends RecyclerView.Adapter<CardListAdpter.CardListHolder> {

        private List<String> list;
        private Context context;

        public CardListAdpter(List<String> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public CardListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CardListHolder(LinearLayout.inflate(context, R.layout.item_view, null));
        }

        @Override
        public void onBindViewHolder(@NonNull CardListHolder holder, int position) {
            int adapterPosition = holder.getAdapterPosition();
            holder.textView.setText(adapterPosition + "itemposition======");
            Glide.with(context).load(list.get(position))
                    .into(holder.imageView);
//            Picasso.get()
//                    .load(list.get(position))
//                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class CardListHolder extends RecyclerView.ViewHolder {

            private TextView textView;
            private ImageView imageView;

            public CardListHolder(@NonNull View itemView) {
                super(itemView);

                textView = itemView.findViewById(R.id.nameText);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }

    }

}
