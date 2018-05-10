package be.runesoft.dev.tourguidelonderzeel;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
                " packaged meals and cold beverages.", "Mechelsestraat 58"));
        venues.add(new Venue(R.drawable.de_linde, "Meubelen De Linde", "Furniture store. You'll find furniture for every room and" +
                "every style.", "Linde 45"));
        venues.add(new Venue(R.drawable.sarens, "Sarens", "Household appliances. From the kitchen to the garden and back, small and" +
                "large appliances can be found here.", "Mechelsestraat 31"));
        venues.add(new Venue(R.drawable.colruyt, "Colruyt", "Supermarket. Offering everything a supermarket should, at great value.", "Molenstraat 39"));
        venues.add(new Venue(R.drawable.top_office, "Top Office Shop", "Office supply store. Offers pens, paper, printing and copying" +
                " facilities and office furniture.", "Molenstraat 11"));
        venues.add(new Venue(R.drawable.selexion, "Selexion", "Electronics store.", "Markt 4"));
        venues.add(new Venue(R.drawable.nanu_fashion, "Nanu Fashion", "Ladies' fashion.", "Dorpsstraat 44"));
        venues.add(new Venue(R.drawable.t_krantje, "'t Krantje", "Newsagent's.", "Klein Holland 4"));


        ListView listView = rootView.findViewById(R.id.list);

        //set the correct background color
        VenueArrayAdapter venueArrayAdapter = new VenueArrayAdapter(getActivity(), venues, R.color.list_item);

        listView.setAdapter(venueArrayAdapter);

        return rootView;
    }

}
