package com.gs0ciety.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gs0ciety.activity.R;
import com.gs0ciety.interfaces.ButtonPanelFragmentInterface;
import com.gs0ciety.interfaces.MainActivityInterface;
import com.gs0ciety.model.LanguageItem;

import java.util.List;

public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.ViewHolder> {

    private Context context;
    private List<LanguageItem> languageItemList;
    private ButtonPanelFragmentInterface buttonPanelFragmentInterface;

    public LanguageListAdapter(final Context context,
                               final List<LanguageItem> languageItemList,
                               final ButtonPanelFragmentInterface buttonPanelFragmentInterface) {
        this.context = context;
        this.languageItemList = languageItemList;
        this.buttonPanelFragmentInterface = buttonPanelFragmentInterface;
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
        viewHolder.linearLayoutItemLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPanelFragmentInterface.changeLanguage(languageItemList.get(i).getCountryNameShort());
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