package be.runesoft.dev.tourguidelonderzeel;

public class Venue {
    private String mVenueName;
    private String mVenueDesc;
    private String mVenueAddress;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mVenueImageId;

    Venue(int imageId, String venueName, String venueDesc, String venueAddress) {
        mVenueImageId = imageId;
        mVenueName = venueName;
        mVenueDesc = venueDesc;
        mVenueAddress = venueAddress;
    }

    Venue(String venueName, String venueDesc, String venueAddress) {
        mVenueName = venueName;
        mVenueDesc = venueDesc;
        mVenueAddress = venueAddress;
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

    public String getVenueDesc() {
        return mVenueDesc;
    }

    public String getVenueAddress() {
        return mVenueAddress;
    }
}
