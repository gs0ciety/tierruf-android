package com.gs0ciety.tierruf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gs0ciety.tierruf.R;
import com.gs0ciety.tierruf.model.LanguageItem;

import java.util.List;

public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.ViewHolder> {

    private Context context;
    private List<LanguageItem> languageItemList;

    public LanguageListAdapter(final Context context, final List<LanguageItem> languageItemList) {
        this.context = context;
        this.languageItemList = languageItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_languages, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        // set image based on selected text
        viewHolder.countryFlag.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),
                languageItemList.get(i).getCountryFlagResId(), null));
        viewHolder.countryName.setText(languageItemList.get(i).getCountryName());
        viewHolder.countryFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Change language
            }
        });
    }

    @Override
    public int getItemCount() {
        return languageItemList != null ? languageItemList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView countryFlag;
        final TextView countryName;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            countryFlag = itemView.findViewById(R.id.grid_item_animal_picture);
            countryName = itemView.findViewById(R.id.grid_item_animal_name);
        }
    }
}