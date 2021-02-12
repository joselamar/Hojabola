package pt.ubi.di.pdm.hojabola;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pt.ubi.di.pdm.hojabola.Model.UserAPI;
import pt.ubi.di.pdm.hojabola.Remote.Common;
import pt.ubi.di.pdm.hojabola.Remote.api4app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addParking extends AppCompatActivity {
    private static final int LOCATION_PERMISSION = 2;
    api4app mService;
    private Spinner isPayedSpinner;
    private SharedPreferences sp;
    private Toolbar toolbar;
    private TextView parkingPicture;
    private Uri tempImgUri;
    private Bitmap bitmap;
    private String encoded_string, mImgUriString, mTempPhotoPath;
    private Button addParkingButton;
    private EditText parTitle, parAdress, parSpots, parAvSpots;
    private Location currentLocation=null;
    int stadium;
    String coordinates,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp = getSharedPreferences("login", MODE_PRIVATE);
        String team = sp.getString("club", "LoginTheme");
        handleTheme(team);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking);

        stadium=getIntent().getIntExtra("stadium",0);
        coordinates=getIntent().getStringExtra("coordinates");
        title=getIntent().getStringExtra("title");

        mService= Common.getAPI();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
        } else {
            getCurrentLocation();
        }

        parTitle = findViewById(R.id.parTitle);
        parAdress = findViewById(R.id.parAdress);
        parSpots = findViewById(R.id.parSpots);
        parAvSpots = findViewById(R.id.parAvSpots);
        isPayedSpinner = findViewById(R.id.parkingISPayed);

        toolbar = findViewById(R.id.my_toolbarAP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Adicionar Estacionamento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ArrayAdapter<String> iAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.isPayedparking));
        iAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        isPayedSpinner.setAdapter(iAdapter);
        handlePopUpSpinnersBackground(team);
        parkingPicture = findViewById(R.id.parkingPicture);
        parkingPicture.setPaintFlags(parkingPicture.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        parkingPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parkingPicture.setClickable(false);
                startCamera();
            }
        });

        addParkingButton = findViewById(R.id.addParkingButton);

        addParkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentLocation==null){
                    mService.addParking(parTitle.getText().toString(), parAdress.getText().toString(), String.valueOf(parSpots.getText()), String.valueOf(parAvSpots.getText()), String.valueOf(stadium),"NULL", String.valueOf(isPayedSpinner.getSelectedItemPosition()), encoded_string).enqueue(new Callback<UserAPI>() {
                        @Override
                        public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
                            UserAPI result=response.body();
                            if(result.isError()){
                                Toast.makeText(addParking.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(addParking.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                Intent iActivity = new Intent(addParking.this,parkingActivity.class);
                                iActivity.putExtra("stadium",stadium);
                                iActivity.putExtra("coordinates",coordinates);
                                iActivity.putExtra("title", title);
                                startActivity(iActivity);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserAPI> call, Throwable t) {
                            Toast.makeText(addParking.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    mService.addParking(parTitle.getText().toString(), parAdress.getText().toString(), String.valueOf(parSpots.getText()), String.valueOf(parAvSpots.getText()), String.valueOf(stadium),String.valueOf(currentLocation.getLatitude())+" "+String.valueOf(currentLocation.getLongitude()), String.valueOf(isPayedSpinner.getSelectedItemPosition()), encoded_string).enqueue(new Callback<UserAPI>() {
                        @Override
                        public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
                            UserAPI result=response.body();
                            if(result.isError()){
                                Toast.makeText(addParking.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(addParking.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                                Intent iActivity = new Intent(addParking.this,parkingActivity.class);
                                iActivity.putExtra("stadium",stadium);
                                iActivity.putExtra("coordinates",coordinates);
                                iActivity.putExtra("title", title);
                                startActivity(iActivity);
                                finish();
                            }
                        }
                        @Override
                        public void onFailure(Call<UserAPI> call, Throwable t) {
                            Toast.makeText(addParking.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            bitmap = BitmapFactory.decodeFile(mTempPhotoPath);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);

            byte[] pictureInBytes = stream.toByteArray();
            encoded_string = Base64.encodeToString(pictureInBytes, 0);
            Toast.makeText(this, "Fotografia adicionada com sucesso", Toast.LENGTH_SHORT).show();
            parkingPicture.setClickable(true);
        } else {
            parkingPicture.setClickable(true);
            Toast.makeText(this, "Fotografia não adicionada", Toast.LENGTH_SHORT).show();
        }
    }

    private void startCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            photoFile = createTempImageFile(this);

            if (photoFile != null) {
                mTempPhotoPath = photoFile.getAbsolutePath();
                if (Build.VERSION.SDK_INT < 24) {

                    //create Uri with 'file://' prefix
                    tempImgUri = Uri.fromFile(photoFile);
                } else {

                    //create Uri with 'content://' prefix
                    tempImgUri = FileProvider.getUriForFile(this,
                            "pt.ubi.di.pdm.hojabola.provider",
                            photoFile);
                }
                mImgUriString = tempImgUri.toString();
                // Add the URI so the camera can store the image
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempImgUri);
                startActivityForResult(takePictureIntent, 10);
            }
        }
    }

    public void handleTheme(String team) {
        if (team.equals("Aves")) {
            setTheme(R.style.aves);
        } else if (team.equals("Belenenses")) {
            setTheme(R.style.belenenses);
        } else if (team.equals("Benfica")) {
            setTheme(R.style.benfica);
        } else if (team.equals("Boavista")) {
            setTheme(R.style.boavista);
        } else if (team.equals("Braga")) {
            setTheme(R.style.braga);
        } else if (team.equals("Famalicao")) {
            setTheme(R.style.famalicao);
        } else if (team.equals("Porto")) {
            setTheme(R.style.porto);
        } else if (team.equals("Ferreira")) {
            setTheme(R.style.ferreira);
        } else if (team.equals("Maritimo")) {
            setTheme(R.style.maritimo);
        } else if (team.equals("Moreirense")) {
            setTheme(R.style.moreirense);
        } else if (team.equals("Gil Vicente")) {
            setTheme(R.style.gilvicente);
        } else if (team.equals("Portimonense")) {
            setTheme(R.style.portimonense);
        } else if (team.equals("Rio Ave")) {
            setTheme(R.style.rioave);
        } else if (team.equals("Santa Clara")) {
            setTheme(R.style.santaclara);
        } else if (team.equals("Sporting")) {
            setTheme(R.style.sporting);
        } else if (team.equals("Tondela")) {
            setTheme(R.style.tondela);
        } else if (team.equals("Setubal")) {
            setTheme(R.style.setubal);
        } else if (team.equals("Guimaraes")) {
            setTheme(R.style.guimaraes);
        }
    }

    public boolean onSupportNavigateUp() {
        finish();
        Intent iActivity = new Intent(this, parkingActivity.class);
        iActivity.putExtra("stadium",stadium);
        iActivity.putExtra("coordinates",coordinates);
        iActivity.putExtra("title", title);
        startActivity(iActivity);
        return true;
    }

    public void onBackPressed() {
        finish();
        Intent iActivity = new Intent(this, parkingActivity.class);
        iActivity.putExtra("stadium",stadium);
        iActivity.putExtra("coordinates",coordinates);
        iActivity.putExtra("title", title);
        startActivity(iActivity);
    }

    public void getCurrentLocation() {

        FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        currentLocation= location;
                    }
                });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] result){
        super.onRequestPermissionsResult(requestCode, permissions, result);
        if(requestCode==LOCATION_PERMISSION && result[0]==0 && result[1]==0){
            getCurrentLocation();
        } else {
            Toast.makeText(this, "Sem Permissão para obter coordenadas", Toast.LENGTH_SHORT).show();
        }


    }

    public void handlePopUpSpinnersBackground(String team) {
        if (team.equals("Aves")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.aves);
        } else if (team.equals("Belenenses")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.belenenses);
        } else if (team.equals("Benfica")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.benfica);
        } else if (team.equals("Boavista")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.boavista);
        } else if (team.equals("Braga")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.braga);
        } else if (team.equals("Famalicao")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.famalicao);
        } else if (team.equals("Porto")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.porto);
        } else if (team.equals("Ferreira")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.ferreira);
        } else if (team.equals("Maritimo")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.maritimo);
        } else if (team.equals("Moreirense")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.moreirense);
        } else if (team.equals("Gil Vicente")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.gilvicente);
        } else if (team.equals("Portimonense")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.portimonense);
        } else if (team.equals("Rio Ave")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.rioave);
        } else if (team.equals("Santa Clara")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.santaclara);
        } else if (team.equals("Sporting")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.sporting);
        } else if (team.equals("Tondela")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.tondela);
        } else if (team.equals("Setubal")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.setubal);
        } else if (team.equals("Guimaraes")) {
            isPayedSpinner.setPopupBackgroundResource(R.color.guimaraes);
        }
    }

    public static File createTempImageFile(Context context) {
        File mediaStorageDir = context.getExternalCacheDir();
        if (!(mediaStorageDir != null && mediaStorageDir.exists())) {
            if (!(mediaStorageDir != null && mediaStorageDir.mkdirs())) {
                return null;
            }
        }
        return createImageFile(mediaStorageDir);
    }

    public static File createImageFile(File directory) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss.SSS", Locale.getDefault()).format(new Date());
        return new File(directory.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }
}




