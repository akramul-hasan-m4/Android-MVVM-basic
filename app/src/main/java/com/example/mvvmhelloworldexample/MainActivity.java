package com.example.mvvmhelloworldexample;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmhelloworldexample.databinding.ActivityMainBinding;
import com.example.mvvmhelloworldexample.model.User;
import com.example.mvvmhelloworldexample.recyclerview.RecyclerActivity;

public class MainActivity extends AppCompatActivity {

    private User user;
    private MyClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //ActivityMainBinding comes from activity layout name
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        simpleMvvmBind(binding);
        handleClickEvent(binding);

    }

    private void simpleMvvmBind(ActivityMainBinding binding){
        user = new User();
        user.setName("Akramul Hasan");
        user.setEmail("akramul@example.info");
        user.setProfileImage("https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg");

        binding.setUser(user);
    }

    private void imageBinding(){
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User();
        user.setProfileImage("https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg");
        binding.setUser(user);
    }

    private void handleClickEvent(ActivityMainBinding binding){
        handlers = new MyClickHandlers(this);
        binding.setHandlers(handlers);
    }

    public class MyClickHandlers {

        Context context;

        public MyClickHandlers(Context context) {
            this.context = context;
        }

        public void onButtonClicked(View view) {
            user.setName("Ati Limited");
            user.setEmail("Ati@atilimited.net");
        }

        public void onButtonClickWithParam(View view, User user) {
            Toast.makeText(getApplicationContext(), "Button clicked! Name: " + user.getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
            startActivity(intent);
        }
    }

}
