package com.gs0ciety.tierruf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gs0ciety.tierruf.R;
import com.gs0ciety.tierruf.interfaces.ButtonPanelBehaviors;
import com.gs0ciety.tierruf.model.LanguageItem;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.ViewHolder> {

    private Context context;
    private List<LanguageItem> languageItemList;
    private ButtonPanelBehaviors buttonPanelBehaviors;

    public LanguageListAdapter(final Context context,
                               final List<LanguageItem> languageItemList,
                               final ButtonPanelBehaviors buttonPanelBehaviors) {
        this.context = context;
        this.languageItemList = languageItemList;
        this.buttonPanelBehaviors = buttonPanelBehaviors;
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
        viewHolder.countryFlag.setImageResource(languageItemList.get(i).getCountryFlagResId());
        viewHolder.countryName.setText(StringUtils.capitalize(languageItemList.get(i).getCountryName()));
        viewHolder.linearLayoutItemLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPanelBehaviors.changeLanguage(languageItemList.get(i).getCountryNameShort());
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
        final LinearLayout linearLayoutItemLanguage;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            countryFlag = itemView.findViewById(R.id.grid_item_animal_picture);
            countryName = itemView.findViewById(R.id.grid_item_animal_name);
            linearLayoutItemLanguage = itemView.findViewById(R.id.linear_layout_item_language);
        }
    }
}