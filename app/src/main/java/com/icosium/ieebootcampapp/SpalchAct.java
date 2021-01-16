
package com.icosium.ieebootcampapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class SpalchAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread=new Thread(){
            @Override
            public void run(){
                try{

                    sleep( 2000);

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                       Intent intent=new Intent(SpalchAct.this,HomeAct.class);
                       startActivity(intent);
                       finish();

                }
            }
        };
    thread.start();
    }

}

