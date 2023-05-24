package com.example.exchangerates.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.exchangerates.R;

import java.util.List;

/* класс адаптера для карточек с людьми */
public class PersonsAdapter extends BaseAdapter {

    private Context context;

    /* список людей */
    private List<String> personPhotos;

    /* список имен */
    private List<String> personNames;

    /* список достижений */
    private List<String> whatDoList;

    public PersonsAdapter(Context context,
                          List<String> personPhotos,
                          List<String> personNames,
                          List<String> whatDoList) {
        this.context = context;
        this.personPhotos = personPhotos;
        this.personNames = personNames;
        this.whatDoList = whatDoList;
    }

    @Override
    public int getCount() {
        return personPhotos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        @SuppressLint({"ViewHolder", "InflateParams"})
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_person, null
        );

        ImageView photo = view.findViewById(R.id.photoOnCard);
        TextView name = view.findViewById(R.id.nameOnCard);
        TextView whatDo = view.findViewById(R.id.whatDoOnCard);

//        ImageView favourite = view.findViewById(R.id.favouriteOnCard);

        Glide.with(context)
                .load(personPhotos.get(position))
                .into(photo);

        name.setText(personNames.get(position));
        whatDo.setText(whatDoList.get(position));
        return view;
    }
}