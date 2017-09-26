package com.example.buh.randomuserparser;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Buh on 22.09.2017.
 */

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String name = sharedPref.getString("name","");
        String gender = sharedPref.getString("gender","");
        String location = sharedPref.getString("location","");
        String phone = sharedPref.getString("phone", "");
        String email = sharedPref.getString("email","");
        String nat = sharedPref.getString("nat","");
        String image = sharedPref.getString("image","");
        TextView name1 = (TextView)findViewById(R.id.txtName);
        TextView gender1 = (TextView)findViewById(R.id.gender);
        TextView location1 = (TextView)findViewById(R.id.location);
        TextView phone1 = (TextView)findViewById(R.id.phone);
        TextView email1 = (TextView)findViewById(R.id.email);
        TextView nat1= (TextView)findViewById(R.id.nat);
        ImageView image1 = (ImageView) findViewById(R.id.imageView);
        name1.setText(name);
        gender1.setText(gender);
        location1.setText(location);
        phone1.setText(phone);
        email1.setText(email);
        nat1.setText(nat);
        Picasso.with(this).load(image).into(image1);


    }
}
