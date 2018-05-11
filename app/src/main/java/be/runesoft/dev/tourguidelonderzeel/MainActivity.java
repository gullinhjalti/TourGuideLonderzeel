package be.runesoft.dev.tourguidelonderzeel;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_VENUE_ADDRESS = "be.runesoft.dev.tourguidelonderzeel.VENUE_ADDRESS";
    public static final String EXTRA_VENUE_NAME = "be.runesoft.dev.tourguidelonderzeel.VENUE_NAME";
    public static final String EXTRA_VENUE_LONG_DESC = "be.runesoft.dev.tourguidelonderzeel.VENUE_LONG_DESC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        VenueFragmentPagerAdapter mFP = new VenueFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(mFP);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
