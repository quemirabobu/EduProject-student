package com.example.finalstudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AttendActivity  extends Fragment {







    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_attend, container, false);

        // Intent는 Fragment에서 사용할 수 없으므로 getArguments()를 사용하여 Bundle에서 데이터를 가져옵니다.
        UserDTO userDTO = (UserDTO) getArguments().getSerializable("userDTO");
        Log.i("myapp", userDTO.toString());

        Button enterButton = view.findViewById(R.id.enterButton);
        Button exitButton = view.findViewById(R.id.exitButton);
        TextView userText = view.findViewById(R.id.welcomeTextView);
        userText.setText(userDTO.getUserName() + "님 환영합니다.");





        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입실 처리 API 호출
                sendAttendanceRequest("enter");
            }
        });


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 퇴실 처리 API 호출
                sendAttendanceRequest("exit");
            }
        });
        return view;
    }



    private String getToken() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("authPrefs", getActivity().MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }

    private void sendAttendanceRequest(String type) {
        String token = "Bearer " + getToken(); // "Bearer " 접두어 추가

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.220:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call;  // <-- 이 부분 수정

        if ("enter".equals(type)) {
            call = apiService.registerEnterTime(token);
        } else {
            call = apiService.registerExitTime(token);
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // 성공적인 응답 처리
                    try {
                        AttendDTO attendDTO = new Gson().fromJson(response.body().string(), AttendDTO.class);
                        Log.i("myapp", "AttendDTO: " + attendDTO.toString());

                        TextView startText = getView().findViewById(R.id.starttime);

                        TextView finishText = getView().findViewById(R.id.finishtime);

                        startText.setText(attendDTO.getAttStart().split("T")[1].split("\\.")[0]);

//attendText.setText(attendDTO.getAttFinish().split("T")[1].split(".")[0]);

                        if (attendDTO.getAttFinish() != null) {
                            finishText.setText(attendDTO.getAttFinish().split("T")[1].split("\\.")[0]);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Gson gson = new Gson();
                    try {
                        ResponseDTO<AttendDTO> errorResponse = gson.fromJson(response.errorBody().string(), new TypeToken<ResponseDTO<AttendDTO>>() {
                        }.getType());
                        String errorMessage = errorResponse.getErrorMessage();
                        Log.e("myapp", "Error Message: " + errorMessage);
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
//                        new AlertDialog.Builder(DashboardActivity.this)
//                                .setTitle("Error")
//                                .setMessage(errorMessage)
//                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // 사용자가 '확인' 버튼을 눌렀을 때 수행될 작업
//                                    }
//                                })
//                                .setIcon(android.R.drawable.ic_dialog_alert)
//                                .show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // 네트워크 오류 또는 다른 문제로 인한 실패 처리
                Log.e("myapp", t.getMessage());
            }
        });


    }

}
