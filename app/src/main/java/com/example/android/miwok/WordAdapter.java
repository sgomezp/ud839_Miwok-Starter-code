package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sgomezp on 13/05/2017.
 */

/*
* {@link WordAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link Word} objects.
* */

public class WordAdapter extends ArrayAdapter<Word> {

    /** Resource ID for the Background color for this list of words */
    private int mColorResourceId;

    /** Resource ID for the sound file for this list of words */



    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param words A List of Word objects to display in a list
     */


    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {

        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.

        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the miwokTextView
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokTextView);
        // Get the Default Translation word from the current Word object and
        // set this text on the name TextView
        miwokTextView.setText(currentWord.getDefaultTranslation());


        // Find the TextView in the list_item.xml layout with the englishTextView
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.englishTextView);
        // Get the miwok word from the current Word object and
        // set this text on the name TextView
        defaultTextView.setText(currentWord.getMiwokTranslation());



        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        // Check if an image is provided for this word or not
        if (currentWord.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentWord.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }

        // Set the color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);


        // Return the whole list item layout (containing 2 TextViews  and  an image)
        // so that it can be shown in the ListView
        return listItemView;
    }

}