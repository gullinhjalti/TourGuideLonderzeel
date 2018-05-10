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
public class CultureFragment extends Fragment {


    public CultureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.venue_list, container, false);

        final ArrayList<Venue> venues = new ArrayList<>();

        //add venues here
        venues.add(new Venue(R.drawable.pastorie_sj, "Presbytery of Sint-Jozef", "The presbytery of Londerzeel Sint-Jozef.", "Bloemstraat 2"));
        venues.add(new Venue(R.drawable.olijfje, "Mural 'Olijfje'", "This mural, a commercial for electric cooking stoves, dates from 1938.", "Stationsstraat 47"));
        venues.add(new Venue(R.drawable.brouwerij_palm, "PALM Belgian Craft Brewers", "This well known local brewery can be visited, a reservation is needed.", "Steenhuffeldorp 3"));
        venues.add(new Venue(R.drawable.gemeentehuis, "City Hall", "Designed by architect Hansotte. Constructed in 1872.", "Markt 1"));
        venues.add(new Venue(R.drawable.kerk_londerzeel, "Sint-Kristoffelkerk", "This 13th century church, dedicated to saint Christopher, lies at the heart of Londerzeel.", "Markt"));
        venues.add(new Venue(R.drawable.groenelong, "Green lung", "The presbyterian gardens have been turned into a public park, the peaceful 'green lung' of Londerzeel.", "Brusselsestraat 25"));
        venues.add(new Venue(R.drawable.merelaantje, "Flour Mill ''t Merelaantje'", "This 1933 stake mill was built to be the very first windmill to generate electricity in Europe. Can be visited on reservation.", "Holle Eikstraat 34"));
        venues.add(new Venue(R.drawable.diepenstyn, "Castle Diepensteyn", "This former medieval moated castle, once the site of a lordship, is now a stud farm and hop field for the Palm brewery.", "Diepensteyn 1"));

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
