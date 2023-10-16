package com.example.finalstudent;

import android.Manifest;

import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.OverlayImage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;

import java.util.Arrays;
import java.util.List;

public class GPSActivity extends Fragment implements OnMapReadyCallback {
    private FusedLocationProviderClient fusedLocationClient;
    private int carnumber;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_gpsactivity, container, false);

        Bundle args = getArguments();
        if(args != null) {
            UserDTO userDTO = (UserDTO) args.getSerializable("userDTO");
            carnumber = userDTO.getUserBus();
            GPS gps = (GPS) args.getSerializable("GPS");
            Log.i("myapp", gps.toString());
            Log.i("myapp", "gps.toString()");

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

            FragmentManager fm = getChildFragmentManager();
            com.naver.maps.map.MapFragment mapFragment = (com.naver.maps.map.MapFragment) fm.findFragmentById(R.id.map_fragment);
            if (mapFragment == null) {
                mapFragment = com.naver.maps.map.MapFragment.newInstance();
                fm.beginTransaction().add(R.id.map_fragment, mapFragment).commit();
            }

            mapFragment.getMapAsync(this);
        }

        return rootView;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Bundle args = getArguments();
        if (args == null) {
            // Handle the case where no arguments are set
            return;
        }

        GPS gps = (GPS) args.getSerializable("GPS");

        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
        locationOverlay.setVisible(true);
        locationOverlay.setCircleRadius(30);
        locationOverlay.setCircleColor(Color.BLUE);
        locationOverlay.setCircleOutlineWidth(10);


        List<LatLng> polylinePath;

        if (carnumber == 1) {
            polylinePath = Arrays.asList(
                    new LatLng(37.509601, 127.11172),
                    new LatLng(37.509601, 127.11172),
                    new LatLng(37.510321, 127.112175),
                    new LatLng(37.511988, 127.108133),
                    new LatLng(37.515563, 127.106607),
                    new LatLng(37.514395, 127.103134),
                    new LatLng(37.515495, 127.102553),
                    new LatLng(37.514920, 127.100712),
                    new LatLng(37.514965, 127.100581),
                    new LatLng(37.516561, 127.099627),
                    new LatLng(37.515984, 127.098111),
                    new LatLng(37.515803, 127.098029),
                    new LatLng(37.513146, 127.099976),
                    new LatLng(37.509812, 127.102804),
                    new LatLng(37.505083, 127.107074),
                    new LatLng(37.505553, 127.107975),
                    new LatLng(37.505250, 127.108265),
                    new LatLng(37.505968, 127.109341),
                    new LatLng(37.505505, 127.109869),
                    new LatLng(37.507890, 127.112307),
                    new LatLng(37.508105, 127.111890),
                    new LatLng(37.509094, 127.112583),
                    new LatLng(37.508898, 127.113106),
                    new LatLng(37.509558, 127.113535),
                    new LatLng(37.510309, 127.112170),
                    new LatLng(37.509601, 127.11172),
                    new LatLng(37.509601, 127.11172),
                    new LatLng(37.509601, 127.11172),
                    new LatLng(37.509601, 127.11172),
                    new LatLng(37.509601, 127.11172)
            );
        } else if (carnumber == 2) {
            polylinePath = Arrays.asList(
                    new LatLng(37.509601, 127.11172),
                    new LatLng(37.508774, 127.111215),
                    new LatLng(37.509601, 127.11172),
                    new LatLng(37.507790, 127.110462),
                    new LatLng(37.506962, 127.109681),
                    new LatLng(37.506615, 127.109273),
                    new LatLng(37.505820, 127.110209),
                    new LatLng(37.506466, 127.110928),
                    new LatLng(37.505860, 127.111899),
                    new LatLng(37.506309, 127.112379),
                    new LatLng(37.506441, 127.112385),
                    new LatLng(37.506537, 127.112275),
                    new LatLng(37.507001, 127.111505),
                    new LatLng(37.507888, 127.112309),
                    new LatLng(37.508109, 127.111889),
                    new LatLng(37.508441, 127.112149),
                    new LatLng(37.506541, 127.115923),
                    new LatLng(37.514434, 127.120843),
                    new LatLng(37.513972, 127.122212),
                    new LatLng(37.514213, 127.122352),
                    new LatLng(37.516429, 127.115954),
                    new LatLng(37.509601, 127.11172)
            );
        } else  {
            polylinePath = Arrays.asList(
                    new LatLng(37.509601, 127.11172),
                    new LatLng(37.510325, 127.112163),
                    new LatLng(37.515231, 127.110510),
                    new LatLng(37.516176, 127.110098),
                    new LatLng(37.516320, 127.110542),
                    new LatLng(37.516624, 127.110415),
                    new LatLng(37.517371, 127.112454),
                    new LatLng(37.517487, 127.112717),
                    new LatLng(37.527881, 127.119116),
                    new LatLng(37.521404, 127.133725),
                    new LatLng(37.519649, 127.133097),
                    new LatLng(37.511970, 127.127951),
                    new LatLng(37.516434, 127.115945),
                    new LatLng(37.516290, 127.115885),
                    new LatLng(37.514434, 127.120841),
                    new LatLng(37.511182, 127.118810),
                    new LatLng(37.510315, 127.120962),
                    new LatLng(37.510531, 127.121098),
                    new LatLng(37.508683, 127.125891),
                    new LatLng(37.504878, 127.123592),
                    new LatLng(37.505919, 127.121298),
                    new LatLng(37.510316, 127.112168),
                    new LatLng(37.509601, 127.11172),
                    new LatLng(37.509601, 127.11172)
            );
        }

// 폴리라인 생성 및 설정
        PathOverlay pathOverlay = new PathOverlay();
        pathOverlay.setCoords(polylinePath);
        int color = Color.rgb(217, 70, 1);

        pathOverlay.setColor(color);
        pathOverlay.setMap(naverMap);

        // Custom marker icon
        BitmapDrawable busIcon = (BitmapDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.bus);
        Marker marker = new Marker();
        Bitmap resizedBitmap = resizeBitmap(busIcon, 46, 46);
        OverlayImage overlayImage = OverlayImage.fromBitmap(resizedBitmap);
        marker.setIcon(overlayImage);
        marker.setPosition(new LatLng(gps.getLatitude(), gps.getLongitude()));
        marker.setMap(naverMap);


        BitmapDrawable Logo = (BitmapDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.logo);
        Marker markerLogo = new Marker();
        Bitmap resizedBitmapLogo = resizeBitmap(Logo, 36, 40);
        OverlayImage overlayImageLogo = OverlayImage.fromBitmap(resizedBitmapLogo);
        markerLogo.setIcon(overlayImageLogo);
        markerLogo.setPosition(new LatLng(37.509601, 127.11172));
        markerLogo.setMap(naverMap);






        // Set user's location as the center of the map
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), location -> {
            if (location != null) {
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

                // 사용자의 현재 위치를 LocationOverlay에 설정
                locationOverlay.setPosition(currentLocation);

                // 지도의 중심을 사용자의 현재 위치로 변경
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(currentLocation);
                naverMap.moveCamera(cameraUpdate);
            }
        });

    }

    private Bitmap resizeBitmap(BitmapDrawable source, int desiredWidth, int desiredHeight) {
        Bitmap bitmap = source.getBitmap();
        return Bitmap.createScaledBitmap(bitmap, desiredWidth, desiredHeight, false);
    }



}