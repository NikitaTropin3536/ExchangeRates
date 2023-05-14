package com.example.exchangerates.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.exchangerates.R;

import java.util.List;

public class BiographyPagerAdapter extends PagerAdapter {

    private Context context;
    private List<String> biographies;

    private List<String> names;

    public BiographyPagerAdapter(Context context,
                                 List<String> biographies,
                                 List<String> names) {
        this.context = context;
        this.biographies = biographies;
        this.names = names;
    }

    @Override
    public int getCount() {
        return biographies.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View view = inflater.inflate(R.layout.slide_biography, container, false);
//
//        TextView biographyName = (TextView) view.findViewById(R.id.nameBiography);
//
//        TextView biography = (TextView) view.findViewById(R.id.textBiography);
//
//        biographyName.setText(
//                names.get(position)
//        );
//
//        biography.setText(
//            biographies[position]
//        );
//
//        container.addView(view);
//
//        return view;
//    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position,
                            @NonNull Object object) {
        container.removeView((View) object);
    }
}