package com.example.android.ronald_1202150002_modul3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by user on 2/24/2018.
 */

class AirAdapter extends RecyclerView.Adapter<AirAdapter.AirViewHolder>{
    private ArrayList<Air> mAirData;
    private GradientDrawable mGradientDrawable;
    private Context mContext;

    AirAdapter(Context context, ArrayList<Air> airData){
        this.mAirData = airData;
        this.mContext = context;

        //Prepare gray placeholder
        mGradientDrawable = new GradientDrawable();
        mGradientDrawable.setColor(Color.GRAY);

        //Make the placeholder same size as the images
        Drawable drawable = ContextCompat.getDrawable
                (mContext,R.drawable.ades);
        if(drawable != null) {
            mGradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }
    @Override
    public AirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AirViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false), mGradientDrawable);
    }

    @Override
    public void onBindViewHolder(AirViewHolder holder, int position) {
        Air currentAir = mAirData.get(position);
        
        holder.bindTo(currentAir);
    }

    @Override
    public int getItemCount() {
        return mAirData.size();
    }

    static class AirViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mAirImage;
        private Context mContext;
        private Air mCurrentAir;
        private GradientDrawable mGradientDrawable;

        AirViewHolder(Context context, View itemView, GradientDrawable gradientDrawable) {
            super(itemView);
            mTitleText = (TextView)itemView.findViewById(R.id.title);
            mInfoText = (TextView)itemView.findViewById(R.id.subTitle);
            mAirImage = (ImageView)itemView.findViewById(R.id.airImage);

            mContext = context;
            mGradientDrawable = gradientDrawable;

            //Set the OnClickListener to the whole view
            itemView.setOnClickListener(this);
        }

        public void bindTo(Air currentAir) {
            mTitleText.setText(currentAir.getTitle());
            mInfoText.setText(currentAir.getInfo());

            //Get the current sport
            mCurrentAir = currentAir;



            //Load the images into the ImageView using the Glide library
            Glide.with(mContext).load(currentAir.
                    getImageResource()).placeholder(mGradientDrawable).into(mAirImage);
        }

        @Override
        public void onClick(View view) {
            Intent detailIntent = Air.starter(mContext, mCurrentAir.getTitle(),
                    mCurrentAir.getImageResource());


            //Start the detail activity
            mContext.startActivity(detailIntent);
        }
    }


}
