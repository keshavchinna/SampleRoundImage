package com.example.list;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity {

  private String[] listview_names = {"Molly", "Sprite", "Androw", "Pop"};

  static Context mcontext;

  private static int[] listview_images = {R.drawable.images, R.drawable.images1, R.drawable.images2, R.drawable.image3};

  private ListView lv;

  private static ArrayList<String> array_sort;
  private static ArrayList<Integer> image_sort;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    lv = (ListView) findViewById(android.R.id.list);

    array_sort = new ArrayList<String>(Arrays.asList(listview_names));
    image_sort = new ArrayList<Integer>();
    for (int index = 0; index < listview_images.length; index++) {
      image_sort.add(listview_images[index]);
    }

    setListAdapter(new bsAdapter(this));

    lv.setOnItemClickListener(new OnItemClickListener() {

      public void onItemClick(AdapterView<?> arg0,
                              View arg1, int position, long arg3) {
        Toast.makeText(getApplicationContext(), array_sort.get(position),
            Toast.LENGTH_SHORT).show();
        callDetailsActivity(arg1, position);
      }
    });

  }

  private void callDetailsActivity(View arg1, int position) {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    ImageView imageView = (ImageView) arg1.findViewById(R.id.imageview);
    imageView.buildDrawingCache();
    imageView.getDrawingCache().compress(Bitmap.CompressFormat.PNG, 100, stream);
    byte[] byteArray = stream.toByteArray();
    Intent intent = new Intent(getApplicationContext(), Details.class);
    intent.putExtra("user_name", array_sort.get(position));
    intent.putExtra("image", byteArray);
    startActivity(intent);
  }

  public static class bsAdapter extends BaseAdapter {
    Activity cntx;

    public bsAdapter(Activity context) {
      // TODO Auto-generated constructor stub
      this.cntx = context;

    }

    public int getCount() {
      // TODO Auto-generated method stub
      return array_sort.size();
    }

    public Object getItem(int position) {
      // TODO Auto-generated method stub
      return array_sort.get(position);
    }

    public long getItemId(int position) {
      // TODO Auto-generated method stub
      return array_sort.size();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
      View row = null;

      LayoutInflater inflater = cntx.getLayoutInflater();
      row = inflater.inflate(R.layout.list_item, null);

      ImageView im = (ImageView) row.findViewById(R.id.imageview);
      TextView tv = (TextView) row.findViewById(R.id.title);

      tv.setText(array_sort.get(position));
      //  im.setImageDrawable(getResources().getDrawable(image_sort.get(position)));
      // im.setImageBitmap(getRoundedShape(decodeFile(R.drawable.india),150));
      im.setImageBitmap(getRoundedShape(decodeFile(cntx, listview_images[position]), 200));
      // im.setBackgroundDrawable(new BitmapDrawable(getResources(), bmp));
      return row;
    }

    public static Bitmap decodeFile(Context context, int resId) {
      try {
        // decode image size
        mcontext = context;
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(mcontext.getResources(), resId, o);
        // Find the correct scale value. It should be the power of 2.
        final int REQUIRED_SIZE = 200;
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
          if (width_tmp / 2 < REQUIRED_SIZE
              || height_tmp / 2 < REQUIRED_SIZE)
            break;
          width_tmp /= 2;
          height_tmp /= 2;
          scale++;
        }
        // decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeResource(mcontext.getResources(), resId, o2);
      } catch (Exception e) {
      }
      return null;
    }
  }

  public static Bitmap getRoundedShape(Bitmap scaleBitmapImage, int width) {
    // TODO Auto-generated method stub
    int targetWidth = width;
    int targetHeight = width;
    Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
        targetHeight, Bitmap.Config.ARGB_8888);

    Canvas canvas = new Canvas(targetBitmap);
    Path path = new Path();
    path.addCircle(((float) targetWidth - 1) / 2,
        ((float) targetHeight - 1) / 2,
        (Math.min(((float) targetWidth),
            ((float) targetHeight)) / 2),
        Path.Direction.CCW);
    canvas.clipPath(path);
    Bitmap sourceBitmap = scaleBitmapImage;
    canvas.drawBitmap(sourceBitmap,
        new Rect(0, 0, sourceBitmap.getWidth(),
            sourceBitmap.getHeight()),
        new Rect(0, 0, targetWidth,
            targetHeight), null);
    return targetBitmap;
  }
}

	


