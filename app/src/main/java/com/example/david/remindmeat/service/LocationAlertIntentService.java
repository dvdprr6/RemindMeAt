package com.example.david.remindmeat.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.example.david.remindmeat.R;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LocationAlertIntentService extends IntentService {
    private static final String IDENTIFIER = "LocationAlertIS";

    public LocationAlertIntentService(){
        super(IDENTIFIER);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if(geofencingEvent.hasError()){
            Log.e(IDENTIFIER, "" + getErrorString(geofencingEvent.getErrorCode()));
            return;
        }

        Log.i(IDENTIFIER, geofencingEvent.toString());

        int geofencingTransition = geofencingEvent.getGeofenceTransition();

        if(geofencingTransition == Geofence.GEOFENCE_TRANSITION_ENTER || geofencingTransition == Geofence.GEOFENCE_TRANSITION_DWELL){
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            String transitionDetails = getGeofencingTransitionInfo(triggeringGeofences);

            String transitionType = getTransitionString(geofencingTransition);

            notifyLocationAlert(transitionType, transitionDetails);
        }
    }

    private String getGeofencingTransitionInfo(List<Geofence> triggeringGeofences){
        List<String> locationNames = new ArrayList();
        for(Geofence geofence : triggeringGeofences){
            locationNames.add(getLocationName(geofence.getRequestId()));
        }

        String triggeringLocationString = TextUtils.join(", ", locationNames);

        return triggeringLocationString;
    }

    private String getLocationName(String key){
        String[] strs = key.split("-");

        String locationName = null;

        if(strs != null && strs.length == 2){
            double lat = Double.parseDouble(strs[0]);
            double lng = Double.parseDouble(strs[1]);

            locationName = getLocationNameGeocoder(lat, lng);
        }

        if(locationName != null){
            return locationName;
        }else{
            return key;
        }
    }

    private String getLocationNameGeocoder(double lat, double lng){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;

        try{
            addresses = geocoder.getFromLocation(lat, lng, 1);
        }catch(Exception ioException){
            Log.e("", "Error in getting location name for the location");
        }

        if(addresses == null || addresses.size() == 0){
            Log.d("", "no location name");
            return null;
        }else{
            Address address = addresses.get(0);
            ArrayList<String> addressInfo = new ArrayList();
            for(int i = 0; i <= address.getMaxAddressLineIndex(); i++){
                addressInfo.add(address.getAddressLine(i));
            }

            return TextUtils.join(System.getProperty("line.separator"), addressInfo);
        }
    }

    private String getErrorString(int errorCode){
        switch(errorCode){
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return "Geofence not avilable";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return "Geofence too many_geofences";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return "Geofence too many pending_intents";
            default:
                return "geofence error";
        }
    }

    private String getTransitionString(int transitionType){
        switch(transitionType){
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return "location entered";
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return "located exited";
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                return "dwell at location";
            default:
                return "location transition";
        }
    }

    private void notifyLocationAlert(String locTransitionType, String locationDetails) {

        String CHANNEL_ID = "Zoftino";
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle(locTransitionType)
                        .setContentText(locationDetails);

        builder.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(0, builder.build());
    }
}
