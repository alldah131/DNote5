package com.dahlstore.dnote5;

        import android.view.View;
        import android.widget.ImageView;

public class Memo {

    public String header, bodyText;
    public ImageView imageView;

    public Memo(String header, String bodyText, ImageView imageView){
        this.header = header;
        this.bodyText =  bodyText;
        this.imageView = imageView;
    }
}