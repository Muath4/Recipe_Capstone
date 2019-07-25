package com.example.recipe_capstone;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.recipe_capstone.dataFromApi.RecipeResponse;
import com.example.recipe_capstone.dataFromApi.Services;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

import static androidx.core.app.NotificationCompat.PRIORITY_LOW;
import static com.example.recipe_capstone.MainActivity.INTENT_OF_RESULTS;
import static com.example.recipe_capstone.MainActivity.KEY_OF_INTENT_TO_INTENT_SERVICE;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
        setIntentRedelivery(true);
    }

    private Notification getNotification() {
        String channel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            channel = createChannel();
        else {
            channel = "";
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channel).setSmallIcon(android.R.drawable.ic_menu_mylocation).setContentTitle("Searching");


        return mBuilder
                .setPriority(PRIORITY_LOW)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
    }

    @NonNull
    @TargetApi(26)
    private synchronized String createChannel() {
        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        String name = "Searching";
        int importance = NotificationManager.IMPORTANCE_LOW;

        NotificationChannel mChannel = new NotificationChannel("Searching", name, importance);

        mChannel.enableLights(true);
        mChannel.setLightColor(Color.BLUE);
        if (mNotificationManager != null) {
            mNotificationManager.createNotificationChannel(mChannel);
        } else {
            stopSelf();
        }
        return name;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(1, getNotification());
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Services service = retrofit.create(Services.class);
        assert intent != null;
        Call<RecipeResponse> call = service.search(intent.getStringExtra(KEY_OF_INTENT_TO_INTENT_SERVICE));
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Intent i = new Intent(getApplicationContext(), ResultsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(INTENT_OF_RESULTS, response.body());
                startActivity(i);
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<RecipeResponse> call, Throwable t) {

            }
        });


    }


}
