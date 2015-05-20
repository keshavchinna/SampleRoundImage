package com.example.list;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ehc on 20/5/15.
 */
public class HomeActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home);
    TextView textView = (TextView) findViewById(R.id.home);
    Intent intent = getIntent();
    String action = intent.getAction();
    Uri data = intent.getData();
    if (data != null)
      textView.setText(data.toString());
  }
}
