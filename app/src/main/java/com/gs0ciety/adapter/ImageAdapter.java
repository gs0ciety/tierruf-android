package com.gs0ciety.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gs0ciety.activity.R;
import com.gs0ciety.model.AnimalItem;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private List<AnimalItem> animalItemList;

    public ImageAdapter(final Context context, final List<AnimalItem> animalItemLists) {
        this.context = context;
        this.animalItemList = animalItemLists;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            // get layout from mobile.xml
            final View view = inflater.inflate(R.layout.item_animal, null);

            // set value into textview
            final TextView textView = view.findViewById(R.id.grid_item_animal_text);
            textView.setText(animalItemList.get(position).getName());

            // set image based on selected text
            final ImageView imageView = view.findViewById(R.id.grid_item_animal_picture);
            imageView.setImageDrawable(context.getResources().getDrawable(animalItemList.get(position).getImageResId()));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final MediaPlayer mediaPlayer = MediaPlayer.create(context, animalItemList.get(position).getAudioResId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(final MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
            });
            return view;
        } else {
            return convertView;
        }
    }

    @Override
    public int getCount() {
        return animalItemList == null || animalItemList.isEmpty() ? 0 : animalItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
