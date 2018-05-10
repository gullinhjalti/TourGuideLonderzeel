package be.runesoft.dev.tourguidelonderzeel;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportsFragment extends Fragment {


    public SportsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.venue_list, container, false);

        final ArrayList<Venue> venues = new ArrayList<>();

        //TODO: add venues here
        venues.add(new Venue(R.drawable.sweet_vanilla, "Sweet Vanilla", "Ice cream, waffles, crepes,... €€", "Markt 17"));

        ListView listView = rootView.findViewById(R.id.list);

        //set the correct background color
        VenueArrayAdapter venueArrayAdapter = new VenueArrayAdapter(getActivity(), venues, R.color.list_item);

        listView.setAdapter(venueArrayAdapter);
        //when an item is clicked we should get a map with a marker for the venue
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Venue venue = venues.get(position);
                String venueAdd = venue.getVenueAddress() + ", Londerzeel, Belgium";
                showMap(venueAdd, venue.getVenueName());
            }
        });

        return rootView;
    }

    public void showMap(String address, String name) {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        intent.putExtra(MainActivity.EXTRA_VENUE_ADDRESS, address);
        intent.putExtra(MainActivity.EXTRA_VENUE_NAME, name);
        startActivity(intent);
    }

}
