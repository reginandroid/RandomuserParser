package com.example.buh.randomuserparser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private static String url = "https://api.randomuser.me/?results=25";
    private static final String TAG_RESULT= "results";
    private static final String TAG_NAME = "name";
    private static final String TAG_SURNAME = "last";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_LOCATION= "location";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_IMAGE = "picture";
    private static final String TAG_IMAGE_PICTURE =  "thumbnail";
    private static final String TAG_NAT = "nat";
    private static final String TAG_EMAIL = "email";
    ArrayList<Item> list;
    JSONArray result = null;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JSONParse().execute();

    }



    private class JSONParse extends AsyncTask<String, String, String>{
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
        @Override
        protected String doInBackground(String... strings) {
            JsonParser jParser = new JsonParser();
           String json = jParser.getJSONFromUrl(url);

            return json;
        }
        @Override
        protected void onPostExecute(String json){
            pDialog.dismiss();
            list = new ArrayList<>();
            try {
                JSONObject jsonObj = new JSONObject(json);
               result = jsonObj.getJSONArray(TAG_RESULT);

                for (int i = 0; i<result.length(); i++){
                JSONObject c = result.getJSONObject(i);
                    JSONObject name = c.getJSONObject(TAG_NAME);
                    String firstname = name.getString("first");
                    String surname = name.getString("last");

                    String gender = c.getString(TAG_GENDER);
                    JSONObject location = c.getJSONObject(TAG_LOCATION);
                    StringBuilder strlocation = new StringBuilder();
                   String result_location =  (strlocation.append(location.getString("street")).append(", ").append(
                            location.getString("city")).append(", ").append(
                                    location.getString("state")).append(", ").append(
                                            location.getString("postcode"))).toString();
                    JSONObject image_url = c.getJSONObject(TAG_IMAGE);
                    String image = image_url.getString(TAG_IMAGE_PICTURE);
                    String email = c.getString(TAG_EMAIL);
                    String phone = c.getString(TAG_PHONE);
                    String nat = c.getString(TAG_NAT);
                    list.add(new Item(firstname, surname, gender, result_location, email, phone, nat, image));
                    Log.d("my", list.get(i).getName() + " " + list.get(i).getSurname() + " " + list.get(i).getEmail());
//
                }
                RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
                recList.setHasFixedSize(true);
                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recList.setLayoutManager(llm);


                adapter = new Adapter(getApplicationContext(), list);
                recList.setAdapter(adapter);
                ItemClickSupport.addTo(recList)
                        .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                            @Override
                            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                                Item item = list.get(position);
                                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("name", item.getName().toUpperCase() +" "+ item.getSurname().toUpperCase());
                    editor.putString("gender", item.getGender());
                    editor.putString("location", item.getLocation());
                    editor.putString("email", item.getEmail());
                    editor.putString("phone", item.getPhone());
                    editor.putString("nat", item.getNat());
                                editor.putString("image", item.getImage());

                    editor.commit();
                             Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                                startActivity(i);
                            }
                        });


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
