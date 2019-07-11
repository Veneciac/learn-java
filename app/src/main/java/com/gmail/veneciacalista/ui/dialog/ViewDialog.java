package com.gmail.veneciacalista.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gmail.veneciacalista.R;

public class ViewDialog {

    Activity act;
    Dialog dialog;
    public ViewDialog(Activity activity) {
        this.act = activity;
    }

    public void showDialog() {

        dialog  = new Dialog(act);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.loading);

        ImageView gifImageView = dialog.findViewById(R.id.ivLoading);

        Glide.with(act)
            .asGif()
            .load(R.drawable.loading)
            .into(gifImageView);

        dialog.show();
    }

    public void hideDialog(){
        dialog.dismiss();
    }

}
