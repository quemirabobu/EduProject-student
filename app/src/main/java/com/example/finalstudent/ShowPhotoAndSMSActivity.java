package com.example.finalstudent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPhotoAndSMSActivity extends Fragment {
    private UserDTO userDTO;
    private GPS userGPS;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_show_photo_and_smsactivity, container, false);

        // Bundle에서 데이터를 가져오기
        userDTO = (UserDTO) getArguments().getSerializable("userDTO");
        userGPS = (GPS) getArguments().getSerializable("myGPS");
        Log.i("myapp", userDTO.toString() + " ///////////////gaga");
        Log.i("myapp", userGPS.toString());

        getPhoto(Integer.toString(userGPS.getCarnumber()));

        Button sendbutton = view.findViewById(R.id.sendbutton); // view에서 findViewById 호출
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });

        return view;
    }


    private void sendSMS() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.220:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        EditText messageEditText = getView().findViewById(R.id.Message);

//        MessageDTO messageDTO = new MessageDTO(userGPS.getPhonenumber(), messageEditText.getText().toString());
        MessageDTO messageDTO = new MessageDTO( userGPS.getPhonenumber().replace("\"", ""), messageEditText.getText().toString());
Log.i("myapp",messageDTO.toString());
Log.i("myapp",userGPS.getPhonenumber());
Log.i("myapp",messageEditText.getText().toString());
Log.i("myapp","messageDTO.toString()");


        Call<ResponseBody> call = apiService.sendSMS(messageDTO);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Type responseType = new TypeToken<ResponseDTO<MessageDTO>>() {
                        }.getType();
                        ResponseDTO<MessageDTO> responseDTO = new Gson().fromJson(response.body().string(), responseType);
                        MessageDTO messageDTO1 = responseDTO.getItem();
                        Log.i("myapp", messageDTO1.toString());

                        Toast.makeText(getContext(), "문자보내기 성공!", Toast.LENGTH_SHORT).show();
                        messageEditText.setText("");
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


    private void getPhoto(String userBusNumber) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.220:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.getPhotoKids(userBusNumber);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Type responseType = new TypeToken<ResponseDTO<DriverPhoto>>() {
                        }.getType();
                        ResponseDTO<DriverPhoto> responseDTO = new Gson().fromJson(response.body().string(), responseType);
                        DriverPhoto driverPhoto = responseDTO.getItem();
                        Log.i("myapp", driverPhoto.toString());
                        String imageUrl = driverPhoto.getPhotoname();  // 실제로 ResponseBody에서 URL을 가져오는 방법은 API에 따라 다를 수 있습니다.
                        ImageView photoview = getView().findViewById(R.id.photoview);
                        Glide.with(ShowPhotoAndSMSActivity.this)
                                .load(imageUrl)
                                .into(photoview);
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