package com.example.imageswitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
    private ImageSwitcher switcher;

    int[] imageIds = new int[]
    {
        R.drawable.bomb5 , R.drawable.bomb6 , R.drawable.bomb7 
        , R.drawable.bomb8 , R.drawable.bomb9 , R.drawable.bomb10
        , R.drawable.bomb11 , R.drawable.bomb12 , R.drawable.bomb13
        , R.drawable.bomb14 , R.drawable.bomb15 , R.drawable.bomb16
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        List<Map<String, Object>> listItems = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < imageIds.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("image", imageIds[i]);
            listItems.add(listItem);
        }
        
        switcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        switcher.setFactory(new ViewFactory() {
            
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                return imageView;
            }
        });
        
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.cell, new String[] {"image"}, new int[] {R.id.image1});
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                switcher.setImageResource(imageIds[position]);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
