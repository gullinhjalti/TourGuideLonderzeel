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
public class ShoppingFragment extends Fragment {


    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.venue_list, container, false);

        final ArrayList<Venue> venues = new ArrayList<>();

        //add venues here
        venues.add(new Venue(R.drawable.de_passage, "De Passage", "24/7 automated shop. Sells a variety of items such as candy," +
                " packaged meals and cold beverages.", "Mechelsestraat 58", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.de_linde, "Meubelen De Linde", "Furniture store. You'll find furniture for every room and" +
                " every style.", "Linde 45", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.sarens, "Sarens", "Household appliances. From the kitchen to the garden and back, small and" +
                " large appliances can be found here.", "Mechelsestraat 31", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.colruyt, "Colruyt", "Supermarket. Offering everything a supermarket should, at great value.", "Molenstraat 39", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.prima, "Prima", "A cosy, old-timey grocery store, family owned.", "Sint Jozefstraat 31", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.top_office, "Top Office Shop", "Office supply store. Offers pens, paper, printing and copying" +
                " facilities and office furniture.", "Molenstraat 11", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.selexion, "Selexion", "Electronics store.", "Markt 4", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.nanu_fashion, "Nanu Fashion", "Ladies' fashion.", "Dorpsstraat 44", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.t_krantje, "'t Krantje", "Newsagent's.", "Klein Holland 4", getResources().getString(R.string.placeholder_long)));


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