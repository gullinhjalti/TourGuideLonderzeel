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

        venues.add(new Venue(R.drawable.fitality, "Fitality", "Gym and bar. Provides lessons, free and guided workouts. ", "Blauwenhoek 78", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.liggende_wip, "Bergbos-Bergop-De-Kring", "Archery club, shooting for ranged targets at 17 meters (so-called 'liggende wip').", "Bergkapelstraat 55", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.judo, "Judoclub Kumikata", "Judo dojo, training by Bart Van Eynde sensei.", "Linde 60", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.smash, "TC Smash vzw", "Tennis club with several courts. Frequent tourneys ae held for all ages.", "Holle Eikstraat 2", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.celle, "Muay Thai Gym Celle", "Thai boxing gym. trainings are held in the back of café De Palm", "Markt 28", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.staande_wip, "Schuttersvrienden Londerzeel", "Archery club, shooting for raised targets at 28 meters (so-called 'staande wip').", "Markt 17", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.fc_sint_jozef, "FC Sint-Jozef", "Local football club.", "Meerstraat 318", getResources().getString(R.string.placeholder_long)));
        venues.add(new Venue(R.drawable.karate, "Karate Blancke", "Karate dojo, training by Hedwig Blancke sensei.", "Kloosterstraat 36", getResources().getString(R.string.placeholder_long)));


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
