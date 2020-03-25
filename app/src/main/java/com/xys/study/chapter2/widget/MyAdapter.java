package com.xys.study.chapter2.widget;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xys.study.chapter2.R;
import com.xys.study.chapter2.model.Message;
import com.xys.study.chapter2.widget.CircleImageView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG = "MyAdapter";
    private static ListItemClickListener mOnClickListener;
    private List<Message> mDataset;

    public MyAdapter(List<Message> myDataSet, ListItemClickListener listener) {
        mDataset = myDataSet;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.im_list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mTitleView.setText(mDataset.get(position).getTitle());
        holder.mDescriptionView.setText(mDataset.get(position).getDescription());
        holder.mTimeView.setText(mDataset.get(position).getTime());
        switch (mDataset.get(position).getIcon()){
            case "TYPE_ROBOT":
                holder.mImageView.setImageResource(R.drawable.session_robot);
                break;
            case "TYPE_GAME":
                holder.mImageView.setImageResource(R.drawable.icon_micro_game_comment);
                break;
            case "TYPE_SYSTEM":
                holder.mImageView.setImageResource(R.drawable.session_system_notice);
                break;
            case "TYPE_STRANGER":
                holder.mImageView.setImageResource(R.drawable.session_stranger);
                break;
            case "TYPE_USER":
                holder.mImageView.setImageResource(R.drawable.icon_girl);
                break;
            default:break;
        }
        if(mDataset.get(position).isOfficial())
            holder.mNoticeView.setImageResource(R.drawable.im_icon_notice_official);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTitleView;
        TextView mDescriptionView;
        TextView mTimeView;
        CircleImageView mImageView;
        ImageView mNoticeView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleView = (TextView) itemView.findViewById(R.id.tv_title);
            mDescriptionView = (TextView) itemView.findViewById(R.id.tv_description);
            mTimeView = (TextView) itemView.findViewById(R.id.tv_time);
            mImageView = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
            mNoticeView = (ImageView) itemView.findViewById(R.id.robot_notice);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition, mDataset.get(clickedPosition).getTitle());
            }
        }

    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex, String title);
    }
}
