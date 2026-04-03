package com.example.q4_photogallery;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> paths;

    public ImageAdapter(Context c, ArrayList<String> p) { context = c; paths = p; }

    @Override
    public int getCount() { return paths.size(); }
    @Override
    public Object getItem(int i) { return paths.get(i); }
    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView iv = new ImageView(context);
        iv.setImageBitmap(BitmapFactory.decodeFile(paths.get(i)));
        iv.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return iv;
    }
}