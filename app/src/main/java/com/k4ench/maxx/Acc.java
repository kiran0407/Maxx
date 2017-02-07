package com.k4ench.maxx;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Acc extends AppCompatActivity {
ImageButton ib1,ib;
    String password;
    String pass="admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);
        ib1=(ImageButton)findViewById(R.id.imageButton1);
        ib=(ImageButton)findViewById(R.id.imageButton);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Acc.this,MainActivity.class));
            }
        });
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Acc.this,R.style.myBackgroundStyle);
                alertDialog.setTitle("Enter Password");
                // alertDialog.setMessage("Enter Password");

                final EditText input = new EditText(Acc.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setIcon(R.drawable.pass);

                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                password = input.getText().toString();

                                if (pass.equals(password)) {
                                    Toast.makeText(getApplicationContext(),
                                            "Password Matched", Toast.LENGTH_SHORT).show();
                                    Intent myIntent1 = new Intent(view.getContext(),
                                            Admin.class);
                                    startActivityForResult(myIntent1, 0);
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Wrong Password!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }
        });
    }
}
