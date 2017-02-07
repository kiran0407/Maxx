package com.k4ench.maxx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Detail extends AppCompatActivity {
String name,numb;
    EditText inv,exp;
    Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
inv=(EditText)findViewById(R.id.invoc);
        exp=(EditText)findViewById(R.id.exp);
        sub=(Button)findViewById(R.id.submit);
        /*name=getIntent().getStringExtra("nam");
        numb=getIntent().getStringExtra("num");
        Toast.makeText(Detail.this,name,Toast.LENGTH_LONG).show();
        Toast.makeText(Detail.this,numb,Toast.LENGTH_LONG).show();*/
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String invoc=inv.getText().toString();
                String expnd=exp.getText().toString();
                if(invoc.equals("")&&expnd.equals("")){
                    Toast.makeText(Detail.this,"please enter the fields",Toast.LENGTH_LONG).show();
                }
                else{
                    insertme(invoc,expnd);
                    Toast.makeText(Detail.this,"successfully added to database",Toast.LENGTH_LONG).show();
                }
            }

            private void insertme(final String invoc, final String expnd) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://kiran0407.tk/ins.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String,String> params =new HashMap<String, String>();

                        params.put("invoc",invoc);
                        params.put("exp",expnd);
                        return params;

                    }
                };
                AppController.getInstance().addToRequestQueue(stringRequest);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }

}
