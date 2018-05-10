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
public class FoodDrinkFragment extends Fragment {


    public FoodDrinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.venue_list, container, false);

        final ArrayList<Venue> venues = new ArrayList<>();

        //add venues here
        venues.add(new Venue(R.drawable.depalm, "Café De Palm", "A hip bar in the heart of Londerzeel with a terrace and view of the market square. €€", "Markt 28"));
        venues.add(new Venue(R.drawable.tschuurke, "Brasserie 't Schuurke", "Simple Flemish dishes in a rustic setting. €€", "Leopold Van Hoeymissenstraat 34"));
        venues.add(new Venue(R.drawable.cafe_sintjozef, "Café Sint Jozef", "The most authentic Flemish café you'll find. €", "Sint-Jozefstraat 40"));
        venues.add(new Venue(R.drawable.bellavista, "Bella Vista", "Italian restaurant with an authentic look, feel and, most important, taste. €€€.", "Molenhoek 21"));
        venues.add(new Venue(R.drawable.bij_plekker, "Bij Plekker", "A modern pub with a friendly, welcoming atmosphere. €", "Breendonkstraat 96"));
        venues.add(new Venue(R.drawable.brouwershuis, "'t Brouwershuis", "A more 'chique' experience with Belgian and European quisines. €€€", "Sint-Niklaasstraat 2"));
        venues.add(new Venue(R.drawable.bergbos, "Bergbos", "Bar and party room. €€", "Bergkapelstraat 55"));
        venues.add(new Venue(R.drawable.leireken, "Bistro Leireken", "A very unique little bistro in an repurposed train. €€", "Brouwerijstraat 29"));


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
