package com.example.finalstudent;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalstudent.VodBoardDTO.VodBoardDTO;

import java.util.List;
@OptIn(markerClass = androidx.media3.common.util.UnstableApi.class)

public class VodAdapter extends RecyclerView.Adapter<VodAdapter.VodViewHolder> {
    private List<VodBoardDTO> vodList;
    private OnVodItemClickListener listener;

    public interface OnVodItemClickListener {
        void onItemClick(VodBoardDTO vod);
    }

    public VodAdapter(List<VodBoardDTO> vodList, OnVodItemClickListener listener) {
        this.vodList = vodList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vod, parent, false);
        return new VodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VodViewHolder holder, int position) {
        VodBoardDTO vod = vodList.get(position);
        holder.title.setText(vod.getTitle());
        holder.content.setText(vod.getContent());
        holder.writer.setText(vod.getWriter());

        Glide.with(holder.itemView.getContext())
                .load(vod.getSaveThumb())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return vodList.size();
    }

    class VodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView title;
        TextView content;
        TextView writer;

        VodViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.vod_image);
            title = itemView.findViewById(R.id.vod_title);
            content = itemView.findViewById(R.id.vod_content);
            writer = itemView.findViewById(R.id.vod_writer);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                VodBoardDTO vod = vodList.get(getAdapterPosition());
                listener.onItemClick(vod);  // 기존의 리스너 호출 코드 (필요하면 그대로 유지)

                // 새로운 액티비티를 시작하기 위한 인텐트 설정
                Intent intent = new Intent(v.getContext(), VodDetailActivity.class);
                intent.putExtra("VOD_DATA", vod);
                v.getContext().startActivity(intent);
            }
        }
    }
}
