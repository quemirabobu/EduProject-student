package com.example.finalstudent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.ui.PlayerView;

import com.example.finalstudent.VodBoardDTO.VodBoardDTO;

import okhttp3.OkHttpClient;


@UnstableApi // Add this annotation

public class VodDetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvContent, tvWriter, tvRegDate;
    private PlayerView playerView;
    private ExoPlayer exoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);




        setContentView(R.layout.activity_vod_detail);

        // Views 초기화
//        tvTitle = findViewById(R.id.tv_title);
//        tvWriter = findViewById(R.id.tv_writer);
//        tvContent = findViewById(R.id.tv_content);
//        tvRegDate = findViewById(R.id.tv_regdate);
        playerView = findViewById(R.id.video_view);

        Intent intent = getIntent();
        VodBoardDTO vodBoardDTO = (VodBoardDTO) intent.getSerializableExtra("VOD_DATA");

        // 데이터 설정
//        tvTitle.setText(vodBoardDTO.getTitle());
//        tvWriter.setText(vodBoardDTO.getWriter());
//        tvContent.setText(vodBoardDTO.getContent());
//        tvRegDate.setText(vodBoardDTO.getRegDate());
        OkHttpClient client = new OkHttpClient.Builder().build();

        // ExoPlayer 설정
        exoPlayer = new ExoPlayer.Builder(this)
                .build();

        playerView.setPlayer(exoPlayer);

        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(vodBoardDTO.getSavePath()));
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(false); // 자동으로 비디오 재생
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false); // 활동이 일시 중지될 때 비디오 중지
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            exoPlayer.release(); // 리소스 반환
            exoPlayer = null;
        }
    }
}
