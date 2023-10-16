package com.example.finalstudent;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalstudent.GPS;
import com.example.finalstudent.R;
import com.example.finalstudent.UserDTO;
import com.example.finalstudent.VodBoardDTO.VodBoardDTO;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.overlay.PathOverlay;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassActivity extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_class, container, false);

        Bundle args = getArguments();
        UserDTO userDTO = (UserDTO) args.getSerializable("userDTO");
        Log.i("myapp", userDTO.toString());
        Log.i("myapp", "여기 들어왓음");


// onCreateView 내부에서 리사이클러뷰 초기화

        recyclerView = rootView.findViewById(R.id.vod_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getboardList();
// 서버에서 데이터를 가져온 후:

        return rootView;
    }


    private void getboardList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.220:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.getVodBoard();
        System.out.println(call);
        System.out.println("이게 콜이다");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        System.out.println(response);
                        System.out.println("이게 리스폰스다");
                        Type responseType = new TypeToken<ResponseDTO<VodBoardDTO>>() {
                        }.getType();
                        ResponseDTO<VodBoardDTO> responseDTO = new Gson().fromJson(response.body().string(), responseType);

                        List<VodBoardDTO> vodBoardDTOList = responseDTO.getItems();

                        if (recyclerView != null) {
                            VodAdapter adapter = new VodAdapter(vodBoardDTOList, new VodAdapter.OnVodItemClickListener() {
                                @Override
                                public void onItemClick(VodBoardDTO vod) {
                                    // 아이템 클릭시 원하는 동작을 여기에 코딩하세요.
                                    Log.i("myapp", "Clicked on: " + vod.getTitle());
                                }
                            });
                            recyclerView.setAdapter(adapter);
                            Log.i("myapp", "recyclerview 있는건가");
                        }
                        Log.i("myapp", vodBoardDTOList.toString());


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // TODO: 여기에 사진을 처리하거나 UI에 표시하는 코드를 추가합니다.
                    Log.i("myapp", "사진을 성공적으로 가져왔습니다.");
                } else {
                    try {
                        // response.errorBody()는 ResponseBody 타입이므로 직접 문자열로 변환해야 합니다.
                        String errorJson = response.errorBody().string();
                        Log.i("myapp", errorJson);
                        Gson gson = new Gson();
                        ResponseDTO<?> errorResponse = gson.fromJson(errorJson, ResponseDTO.class);
                        String errorMessage = errorResponse.getErrorMessage();
                        Log.e("myapp", "Error Message: " + errorMessage);
                        // Toast나 AlertDialog로 사용자에게 에러 메시지 표시
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 로그인 실패 처리
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("myapp", "사진을 가져오는데 실패했습니다.", t);
            }
        });
    }


}