/*
 * @Author: 
 * @Date: 2021-02-18 10:55:56
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2021-02-18 21:11:11
 * @Description: 
 */
package com.dogotsn.duojia4android;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        setContentView(R.layout.activity_main);
        findViewById(R.id.rn_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doJump();
            }
        });
    }

    private void doJump() {
        Bundle bundle = new Bundle();
        bundle.putString("routerRoot", "Home");
        bundle.putString("routeParams", "{}");
        RNInstanceActivity.start(MainActivity.this, null, bundle);
    }


}