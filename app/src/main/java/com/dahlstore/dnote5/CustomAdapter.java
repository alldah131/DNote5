package com.dahlstore.dnote5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
public class CustomAdapter extends BaseAdapter {


    public String imagePath;
    public static final int DEFAULT_IMAGE_RESOURCE = R.drawable.dog;
    ArrayList<Memo> memos = new ArrayList<>();

    public void add(Memo memo) {
        this.memos.add(memo);
    }

    public void delete(int position) {
        memos.remove(position);
    }

    @Override
    public Memo getItem(int position) {
        return memos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return memos.size();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        imagePath = imagePath;
    }

    public boolean hasImage() {

        return getImagePath() != null && !getImagePath().isEmpty();
    }

    public Drawable getThumbnail(Context context) {

        return getScaledImage(context, 128, 128);
    }

    public Drawable getImage(Context context) {

        return getScaledImage(context, 512, 512);
    }
    private Drawable getScaledImage(Context context, int reqWidth, int reqHeight) {

        // If profile has a Image.
        if (hasImage()) {

            // Decode the input stream into a bitmap.
            Bitmap bitmap = FileUtils.getResizedBitmap(getImagePath(), reqWidth, reqHeight);

            // If was successfully created.
            if (bitmap != null) {

                // Return a drawable representation of the bitmap.
                return new BitmapDrawable(context.getResources(), bitmap);
            }
        }

        // Return the default image drawable.
        return context.getResources().getDrawable(DEFAULT_IMAGE_RESOURCE);
    }


    class MyViewHolder {
        public TextView header, bodyText;
        public ImageView imageView;

        public MyViewHolder(View view) {
            header = (TextView) view.findViewById(R.id.header);
            bodyText = (TextView) view.findViewById(R.id.bodyText);
            imageView = (ImageView) view.findViewById(R.id.imageIcon);

        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        MyViewHolder viewHolder;
        if(null == convertView){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.custom_row, parent, false);
            viewHolder = new MyViewHolder(convertView);
            viewHolder.header.setTag(position);

            convertView.setTag(viewHolder);

        }
        else{
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        Memo memo = getItem(position);
        viewHolder.header.setText(memo.header);
        viewHolder.bodyText.setText(memo.bodyText);
        if (memo.imageView.getDrawable() instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) memo.imageView.getDrawable();
            viewHolder.imageView.setImageBitmap(bitmapDrawable.getBitmap());
        }

        return convertView;
    }
}