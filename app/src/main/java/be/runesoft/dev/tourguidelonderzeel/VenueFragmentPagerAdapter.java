package be.runesoft.dev.tourguidelonderzeel;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

public class VenueFragmentPagerAdapter extends FragmentPagerAdapter {
    private Activity mActivity;

    VenueFragmentPagerAdapter(FragmentManager fm, Activity activity){
        super(fm);
        mActivity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PopularFragment();
            case 1:
                return new ShoppingFragment();
            case 2:
                return new CultureFragment();
            case 3:
                return new SportsFragment();
            case 4:
                return new FoodDrinkFragment();
            default:
            return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        switch (position) {
            case 0:
                return mActivity.getString(R.string.popular_title);
            case 1:
                return mActivity.getString(R.string.shopping_title);
            case 2:
                return mActivity.getString(R.string.culture_title);
            case 3:
                return mActivity.getString(R.string.sports_title);
            case 4:
                return mActivity.getString(R.string.food_drink_title);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
