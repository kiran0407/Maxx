package com.k4ench.maxx;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Sub_catg extends AppCompatActivity {

    EditText e1;
    ImageButton ib;
    Button b3;
    ArrayList<File> list;
    private ProgressDialog dialog;
    ListView listv;
    Context context=this;
    String name,numb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_catg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        e1=(EditText)findViewById(R.id.editText2);
        ib=(ImageButton)findViewById(R.id.imageButton2);

        String topic=getIntent().getStringExtra("data");
        String id=getIntent().getStringExtra("data1");
         name=getIntent().getStringExtra("nam");
         numb=getIntent().getStringExtra("num");

        // Toast.makeText(Sub_catg.this,"id",Toast.LENGTH_LONG).show();
         setTitle(topic);
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait...");
        listv=(ListView)findViewById(R.id.listv1);

        String url="http://kiran0407.tk/sub.php?catg_id="+id;
       // Toast.makeText(Sub_catg.this,url,Toast.LENGTH_LONG).show();


        new JSONTask().execute(url);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString().equals("")){

                    Toast.makeText(Sub_catg.this,"Please enter a keyword",Toast.LENGTH_LONG).show();

                }
                else{


                    String t3=e1.getText().toString();
                    String url="http://kiran0407.tk/search.php?search="+t3;
                    Toast.makeText(Sub_catg.this,url,Toast.LENGTH_LONG).show();
                    new JSONTask().execute(url);



                }
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public class JSONTask extends AsyncTask<String,String, List<ListItem> > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<ListItem> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();
                String line ="";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("success");
                List<ListItem> movieModelList = new ArrayList<>();
                Gson gson = new Gson();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);

                        ListItem listItems = gson.fromJson(finalObject.toString(), ListItem.class);
                        movieModelList.add(listItems);


                }

                return movieModelList;

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return  null;

        }

        @Override
        protected void onPostExecute(final List<ListItem> movieModelList) {
            super.onPostExecute(movieModelList);
            dialog.dismiss();
            if(movieModelList != null) {
                MovieAdapter adapter = new MovieAdapter(getApplicationContext(), R.layout.list_item, movieModelList);
                listv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ListItem category=movieModelList.get(position);
                        Intent i1=new Intent(Sub_catg.this,Detail.class);
                        i1.putExtra("name",name);
                        i1.putExtra("num",numb);
                        startActivity(i1);
                    }
                });
            }
            else {
                Toast.makeText(Sub_catg.this,"error please try again...",Toast.LENGTH_SHORT).show();
            }

        }

    }
    public class MovieAdapter extends ArrayAdapter {

        private List<ListItem> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;
        MovieAdapter(Context context, int resource, List<ListItem> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            this.context =context;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        }
        @Override
        public int getViewTypeCount() {

            return 1;
        }

        @Override
        public int getItemViewType(int position) {

            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final ViewHolder holder  ;

            if(convertView == null){
                convertView = inflater.inflate(resource,null);
                holder = new ViewHolder();
                holder.menuname=(TextView) convertView.findViewById(R.id.tv);
               // holder.idt=(TextView) convertView.findViewById(R.id.tv1);
                convertView.setTag(holder);

            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            ListItem listItem= movieModelList.get(position);
            //Picasso.with(context).load(listItem.getImage()).fit().error(R.drawable.backnom).fit().into(holder.menuimage);
            holder.menuname.setText(listItem.getName());
           // holder.idt.setText(listItem.getShort_desc());
            return convertView;

        }

        class ViewHolder{

            private TextView menuname,idt;


        }



    }
}
