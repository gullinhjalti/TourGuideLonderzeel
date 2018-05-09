package be.runesoft.dev.tourguidelonderzeel;

public class Venue {
    private String mVenueName;
    private String mVenueDesc;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mVenueImageId;

    Venue(int imageId, String venueName, String venueDesc){
        mVenueImageId = imageId;
        mVenueName = venueName;
        mVenueDesc = venueDesc;
    }
    Venue(String venueName, String venueDesc){
        mVenueName = venueName;
        mVenueDesc = venueDesc;
    }

    public boolean hasImage(){
        return mVenueImageId != NO_IMAGE_PROVIDED;
    }

    public int getImageId(){
        return mVenueImageId;
    }

    public String getVenueName(){
        return mVenueName;
    }

    public String getmVenueDesc(){
        return mVenueDesc;
    }
}
