package com.example.finalstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.CarrierConfigManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

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

public class DashboardActivity extends AppCompatActivity {
    public GPS gpstonextscreen;
    public int flag;
    private FragmentManager fragmentManager;

    private FragmentTransaction transaction;
    private ShowPhotoAndSMSActivity showPhotoAndSMSActivity;
    private AttendActivity attendActivity;
    private GPSActivity gpsActivity;
    private ClassActivity classActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        UserDTO userDTO = (UserDTO) intent.getSerializableExtra("userDTO");
        Log.i("myapp", userDTO.toString());
        setContentView(R.layout.activity_dashboard);  // 여기서는 해당 액티비티의 레이아웃을 설정해야 합니다.

        fragmentManager = getSupportFragmentManager();


        showPhotoAndSMSActivity = new ShowPhotoAndSMSActivity();
        attendActivity = new AttendActivity();
        Bundle bundle = new Bundle();
        bundle.putSerializable("userDTO", userDTO);
        attendActivity.setArguments(bundle);


        classActivity = new ClassActivity();
        classActivity.setArguments(bundle);

        gpsActivity = new GPSActivity();
        gpsActivity.setArguments(bundle);
        showPhotoAndSMSActivity.setArguments(bundle);

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, attendActivity).commitAllowingStateLoss();


    }

    public void clickHandler(View view) {
        transaction = fragmentManager.beginTransaction();

        switch (view.getId()) {
            case R.id.btn_fragmentA:
                transaction.replace(R.id.frameLayout, attendActivity).commitAllowingStateLoss();
                break;
            case R.id.btn_fragmentB:
                flag = 0;

                // 입실 처리 API 호출
                getGPs();
//                transaction.replace(R.id.frameLayout, gpsActivity).commitAllowingStateLoss();

                break;
            case R.id.btn_fragmentC:
                flag = 1;
                // 입실 처리 API 호출
                getGPs();
//                transaction.replace(R.id.frameLayout, showPhotoAndSMSActivity).commitAllowingStateLoss();
                break;

            case R.id.btn_fragmentD:
                transaction.replace(R.id.frameLayout, classActivity).commitAllowingStateLoss();
                break;


        }
    }


    private void getGPs() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.220:8081/") // 여기에 API의 기본 URL을 입력해주세요.
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);


        Call<ResponseBody> call = apiService.getGPS();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    try {
                        Log.i("myapp", "되었다.");


                        Type responseType = new TypeToken<ResponseDTO<GPS>>() {
                        }.getType();
                        ResponseDTO<GPS> responseDTO = new Gson().fromJson(response.body().string(), responseType);


                        Log.i("myapp", "responseDTO: " + responseDTO.toString());

                        ArrayList<GPS> gpslist = (ArrayList<GPS>) responseDTO.getItems();

                        Log.i("myapp", "gpslist: " + gpslist);
                        Intent intent = getIntent();
                        UserDTO userDTO = (UserDTO) intent.getSerializableExtra("userDTO");
                        Optional<GPS> mygpsOptional = gpslist.stream()
                                .filter(gps -> (int) gps.getCarnumber() == userDTO.getUserBus())
                                .findFirst();

                        if (mygpsOptional.isPresent()) {
                            GPS mygps = mygpsOptional.get();
                            gpstonextscreen = mygps;
                            // 이제 mygps를 사용할 수 있습니다.

                            Log.i("myapp", "나의 gps" + mygps.toString());
                            Log.i("myapp", gpstonextscreen.toString() + "////////////////gpstonextscreen");

                            if (flag == 0) {
//지도보러가는곳


                                Bundle bundle = new Bundle();
                                bundle.putSerializable("userDTO", userDTO);
                                bundle.putSerializable("GPS", mygps);

                                // gpsActivity 인스턴스를 새로 생성하고 번들을 설정
                                gpsActivity = new GPSActivity();
                                gpsActivity.setArguments(bundle);

                                // 프래그먼트 교체
                                transaction = fragmentManager.beginTransaction();
                                transaction.replace(R.id.frameLayout, gpsActivity).commitAllowingStateLoss();


                            }
                            if (flag == 1) {
                                //사진보러가는곳


                                Bundle bundle = new Bundle();
                                bundle.putSerializable("userDTO", userDTO);
                                bundle.putSerializable("myGPS", gpstonextscreen);

                                // gpsActivity 인스턴스를 새로 생성하고 번들을 설정
                                showPhotoAndSMSActivity = new ShowPhotoAndSMSActivity();
                                showPhotoAndSMSActivity.setArguments(bundle);

                                // 프래그먼트 교체
                                transaction = fragmentManager.beginTransaction();
                                transaction.replace(R.id.frameLayout, showPhotoAndSMSActivity).commitAllowingStateLoss();
                            }

                        } else {
                            // 조건을 만족하는 GPS 객체가 없습니다.
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
                Log.e("myapp", "안됨");
                Log.e("myapp", t.toString());
                Log.e("myapp", call.toString());
                // 네트워크 오류 또는 다른 문제로 인한 실패 처리
            }
        });
    }


    private String getToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("authPrefs", MODE_PRIVATE);
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

                        TextView startText = findViewById(R.id.starttime);

                        TextView finishText = findViewById(R.id.finishtime);

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
                        Toast.makeText(DashboardActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
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
