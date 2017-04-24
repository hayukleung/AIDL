package com.hayukleung.aidl.server;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ServerActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_server);
    Intent intent = new Intent();
    intent.setAction("com.hayukleung.aidl.server.CommonServer");
    intent.setPackage(getPackageName());
    startService(intent);
  }
}
