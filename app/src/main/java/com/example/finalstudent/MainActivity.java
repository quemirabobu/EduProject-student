package com.example.finalstudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.finalstudent.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  // 이 부분은 뷰 바인딩을 사용할 때 레이아웃을 설정하는 방법입니다.

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }


    private void attemptLogin() {
        String userId = binding.usernameEditText.getText().toString();
        String userPw = binding.passwordEditText.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.220:8081/") // 여기에 API의 기본 URL을 입력해주세요.
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        LoginRequest loginRequest = new LoginRequest(userId, userPw);

        Call<ResponseDTO<UserDTO>> call = apiService.loginUser(loginRequest);

        call.enqueue(new Callback<ResponseDTO<UserDTO>>() {
            @Override
            public void onResponse(Call<ResponseDTO<UserDTO>> call, Response<ResponseDTO<UserDTO>> response) {
                if (response.isSuccessful()) {
                    UserDTO userDTO = response.body().getItem();
                    String token = userDTO.getToken();
                    saveToken(token);
                    Log.i("myapp",userDTO.toString());
                    // 토큰이 성공적으로 저장되면 다음 화면으로 넘어갑니다.
                    Intent dashboardIntent = new Intent(MainActivity.this, DashboardActivity.class);
                    dashboardIntent.putExtra("userDTO", userDTO);

                    startActivity(dashboardIntent);
                    finish();  // 선택사항: 로그인 화면을 스택에서 제거하려면 이 코드를 사용
                } else {
                    try {
                        // response.errorBody()는 ResponseBody 타입이므로 직접 문자열로 변환해야 합니다.
                        String errorJson = response.errorBody().string();
Log.i("myapp",errorJson);

                        Gson gson = new Gson();
                        ResponseDTO<?> errorResponse = gson.fromJson(errorJson, ResponseDTO.class);
                        String errorMessage = errorResponse.getErrorMessage();

                        Log.e("myapp", "Error Message: " + errorMessage);

                        // Toast나 AlertDialog로 사용자에게 에러 메시지 표시
                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 로그인 실패 처리
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<UserDTO>> call, Throwable t) {
                Log.e("myapp","안됨");
                Log.e("myapp",t.toString());
                Log.e("myapp",call.toString());
                // 네트워크 오류 또는 다른 문제로 인한 실패 처리
            }
        });
    }

    private void saveToken(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("authPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }


}
