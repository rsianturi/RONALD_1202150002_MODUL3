package com.example.android.ronald_1202150002_modul3;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Air> mAirData;
    private RecyclerView mRecyclerView;
    private AirAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);


        //Set the Layout Manager

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,gridColumnCount));


        //Initialize the ArrayList that will contain the data
        mAirData = new ArrayList<>();

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = new AirAdapter(this, mAirData);
        mRecyclerView.setAdapter(mAdapter);
        initializeData();
        int swipeDirs;
        if(gridColumnCount > 1){

            swipeDirs = 0;

        } else {

            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        }
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN
                        | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                //Swap the items and notify the adapter
                Collections.swap(mAirData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mAirData.remove(viewHolder.getAdapterPosition());

                //Notify the adapter
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }

    private void initializeData(){
        String[] airList = getResources().getStringArray(R.array.air_titles);
        String[] airInfo = getResources().getStringArray(R.array.air_info);
        TypedArray airImageResources = getResources().obtainTypedArray(R.array.air_images);

        for(int i=0; i<airList.length; i++){
            mAirData.add(new Air(airList[i], airInfo[i],
                    airImageResources.getResourceId(i,0)));
        }
        airImageResources.recycle();
    }

    public void resetAir(View view) {
        initializeData();
    }
}
