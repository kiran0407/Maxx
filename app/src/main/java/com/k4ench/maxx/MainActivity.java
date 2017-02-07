package com.k4ench.maxx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    private SQLiteHandler db;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.phone);
        e2=(EditText)findViewById(R.id.pswrd);
        b1=(Button)findViewById(R.id.btn);
        db = new SQLiteHandler(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t1=e1.getText().toString();
                String t2=e2.getText().toString();
                if(t1.isEmpty() || t2.isEmpty()){
                    Toast.makeText(MainActivity.this,"please enter the fields",Toast.LENGTH_LONG).show();
                }
                else{


                    getallme(t1,t2);
                }
            }
        });



    }
    public void getallme(final String t1, final String t2)
    {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, MyUrl_Controller.loginform, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObj = new JSONObject(response);
                    boolean success = responseObj.getBoolean("success");
                    //Toast.makeText(MainActivity.this,"jikx",Toast.LENGTH_LONG).show();
                    if (success)
                    {


                        String name = responseObj.getString("name");
                        String number = responseObj.getString("number");
                        String email = responseObj.getString("email");
                        String typ = responseObj.getString("type");
                        //db.addUser(name,email,number,typ);
                        Intent intent=new Intent(MainActivity.this,Nav.class);
                        intent.putExtra("name",name);
                       intent.putExtra("num",number);
                        intent.putExtra("email",email);
                        intent.putExtra("type",typ);
                        startActivity(intent);

                    }
else {
Toast.makeText(MainActivity.this,"something went wrong please try again!!!",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
              Map<String,String> params = new HashMap<String,String>();
                params.put("na1",t1);
                params.put("na2",t2);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
