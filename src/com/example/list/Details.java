package com.example.list;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ehc on 8/5/15.
 */
public class Details extends Activity implements View.OnClickListener {
  private ImageView mImageView;
  private TextView mOwnerName;
  private TextView mTime;
  private TextView mWalks;
  private Button mButton;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.details_layout);
    getViewIds();
    populateData();
    setAction();
  }

  private void getViewIds() {
    mImageView = (ImageView) findViewById(R.id.pet_image);
    mOwnerName = (TextView) findViewById(R.id.user_name);
    mButton = (Button) findViewById(R.id.tag_id);
  }

  private void populateData() {
    Intent intent = getIntent();
    mOwnerName.setText(intent.getStringExtra("user_name"));
    byte[] byteArray = getIntent().getByteArrayExtra("image");
    Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    mImageView.setImageBitmap(image);
  }

  private void setAction() {
    mButton.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
    startActivity(intent);
  }
}
