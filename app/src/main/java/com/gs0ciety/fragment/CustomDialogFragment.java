package com.gs0ciety.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gs0ciety.activity.R;
import com.gs0ciety.adapter.GameImageAdapter;
import com.gs0ciety.model.AnimalItem;

import java.util.LinkedList;
import java.util.List;

public class CustomDialogFragment extends DialogFragment {

    private static final String KEY_ANIMAL_LIST = "animal_list";
    private List<AnimalItem> animalItemList;

    public static CustomDialogFragment createCustomDialogFragment(final List<AnimalItem> animalItemList) {
        final Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_ANIMAL_LIST, (LinkedList<AnimalItem>) animalItemList);
        CustomDialogFragment fragment = new CustomDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    /** The system calls this to get the DialogFragment's layout, regardless
     of whether it's being displayed as a dialog or an embedded fragment. */
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        final View view = inflater.inflate(R.layout.fragment_play_sound, container, false);
        final Bundle bundle = new Bundle();
        animalItemList = (List<AnimalItem>) bundle.getSerializable(KEY_ANIMAL_LIST);
        RecyclerView animalRecycler = view.findViewById(R.id.recycler_animal);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        animalRecycler.setLayoutManager(layoutManager);
        final GameImageAdapter gameImageAdapter = new GameImageAdapter(getContext(), animalItemList);
        animalRecycler.setAdapter(gameImageAdapter);
        return view;
    }

    /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}

