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
public class PopularFragment extends Fragment {


    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.venue_list, container, false);

        final ArrayList<Venue> venues = new ArrayList<>();

        venues.add(new Venue(R.drawable.de_passage, "De Passage", "24/7 automated shop. Sells a variety of items such as candy," +
                " packaged meals and cold beverages.", "Mechelsestraat 58", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.depalm, "Café De Palm", "A hip bar in the heart of Londerzeel with a terrace and view of the market square. €€", "Markt 28", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.brouwerij_palm, "PALM Belgian Craft Brewers", "This well known local brewery can be visited, a reservation is needed.", "Steenhuffeldorp 3", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.fitality, "Fitality", "Gym and bar. Provides lessons, free and guided workouts. ", "Blauwenhoek 78", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.sweet_vanilla, "Sweet Vanilla", "Ice cream, waffles, crepes,... €€", "Markt 17", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.smash, "TC Smash vzw", "Tennis club with several courts. Frequent tourneys ae held for all ages.", "Holle Eikstraat 2", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.groenelong, "Green lung", "The presbyterian gardens have been turned into a public park, the peaceful 'green lung' of Londerzeel.", "Brusselsestraat 25", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.cafe_sintjozef, "Café Sint Jozef", "The most authentic Flemish café you'll find. €", "Sint-Jozefstraat 40", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.fc_sint_jozef, "FC Sint-Jozef", "Local football club.", "Meerstraat 318", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.merelaantje, "Flour Mill ''t Merelaantje'", "This 1933 stake mill was built to be the very first windmill to generate electricity in Europe. Can be visited on reservation.", "Holle Eikstraat 34", getResources().getString(R.string.placeholder_long)));



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
                showMap(venueAdd, venue.getVenueName(), venue.getVenueLongDesc());
            }
        });

        return rootView;
    }

    public void showMap(String address, String name, String longDesc) {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        intent.putExtra(MainActivity.EXTRA_VENUE_ADDRESS, address);
        intent.putExtra(MainActivity.EXTRA_VENUE_NAME, name);
        intent.putExtra(MainActivity.EXTRA_VENUE_LONG_DESC, longDesc);
        startActivity(intent);
    }

}
