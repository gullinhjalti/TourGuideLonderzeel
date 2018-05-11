package be.runesoft.dev.tourguidelonderzeel;

public class Venue {
    private String mVenueName;
    private String mVenueDesc;
    private String mVenueAddress;
    private String mVenueLongDesc;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mVenueImageId;

    Venue(int imageId, String venueName, String venueDesc, String venueAddress, String venueLongDesc) {
        mVenueImageId = imageId;
        mVenueName = venueName;
        mVenueDesc = venueDesc;
        mVenueAddress = venueAddress;
        mVenueLongDesc = venueLongDesc;
    }

    Venue(String venueName, String venueDesc, String venueAddress, String venueLongDesc) {
        mVenueName = venueName;
        mVenueDesc = venueDesc;
        mVenueAddress = venueAddress;
        mVenueLongDesc = venueLongDesc;
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

    public String getVenueLongDesc() {
        return mVenueLongDesc;
    }
}
