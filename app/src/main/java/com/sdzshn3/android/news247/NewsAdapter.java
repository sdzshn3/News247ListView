package com.sdzshn3.android.news247;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    static class ViewHolder{
        ImageView thumbnailView;
        TextView sectionView;
        TextView titleView;
        TextView firstNameView;
        TextView lastNameView;
        TextView separator;
    }

    public NewsAdapter(Context context, ArrayList<News> newsList) {
        super(context, 0, newsList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        News currentNews = getItem(position);

        ViewHolder holder = new ViewHolder();
        holder.thumbnailView = listItemView.findViewById(R.id.thumbnailImageView);
        holder.sectionView = listItemView.findViewById(R.id.sectionTextView);
        holder.titleView = listItemView.findViewById(R.id.titleTextView);
        holder.firstNameView = listItemView.findViewById(R.id.firstNameTextView);
        holder.lastNameView = listItemView.findViewById(R.id.lastNameTextView);
        holder.separator = listItemView.findViewById(R.id.separator);


        String thumbnailUrl = currentNews.getThumbnail();
        Picasso.get().load(thumbnailUrl).into(holder.thumbnailView);

        Typeface semiBoldText = Typeface.createFromAsset(getContext().getAssets(), "Montserrat-SemiBold.ttf");
        Typeface regularText = Typeface.createFromAsset(getContext().getAssets(), "Montserrat-Regular.ttf");

        holder.sectionView.setTypeface(regularText);
        holder.sectionView.setText(currentNews.getSectionName());

        holder.titleView.setTypeface(semiBoldText);
        holder.titleView.setText(currentNews.getTitle());



        String firstName;
        String lastName;
        try {
            firstName = currentNews.getFirstName();
            lastName = currentNews.getLastName();
        }catch (NullPointerException e){
            firstName = "";
            lastName = "";
        }
        if(firstName.equals("") && lastName.equals("")){
            holder.firstNameView.setVisibility(View.GONE);
            holder.lastNameView.setVisibility(View.GONE);
            holder.separator.setVisibility(View.GONE);
        } else {
            if(!firstName.equals("")){
                holder.firstNameView.setText(firstName);
            }
            if(!lastName.equals("")){
                holder.lastNameView.setText(lastName);
            }
            holder.separator.setVisibility(View.VISIBLE);
        }

        TextView publishedAtView = listItemView.findViewById(R.id.publishedAtTextView);
        publishedAtView.setText(currentNews.getPublishedAt().replace("T", " at ").replace("Z", " "));

        return listItemView;
    }
}
