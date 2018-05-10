package be.runesoft.dev.tourguidelonderzeel;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class VenueArrayAdapter extends ArrayAdapter<Venue> {
    private int mBgColor;

    VenueArrayAdapter(Activity context, ArrayList<Venue> venues, int bgColor) {
        super(context, 0, venues);
        mBgColor = bgColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        final Venue currentVenue = getItem(position);
        if (currentVenue != null) {
            ImageView illustrativeImage = listItemView.findViewById(R.id.illustrative_image);
            if (currentVenue.hasImage()) {
                illustrativeImage.setVisibility(View.VISIBLE);
                illustrativeImage.setImageResource(currentVenue.getImageId());
            } else {
                illustrativeImage.setVisibility(View.GONE);
            }
            TextView nameTextView = listItemView.findViewById(R.id.name_text_view);
            nameTextView.setText(currentVenue.getVenueName());
            nameTextView.setBackgroundResource(mBgColor);
            TextView descTextView = listItemView.findViewById(R.id.descr_text_view);
            descTextView.setText(currentVenue.getVenueDesc());
            descTextView.setBackgroundResource(mBgColor);
        }
        return listItemView;
    }
}
