package com.example.assetssm;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class Fragment_mainTransport extends Fragment implements OnMapReadyCallback {

    private TextView txt_transport_routeDistance;
    private TextView txt_transportStreet;

    //maps
    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private LatLng mOrigin;
    private LatLng mDestination;
    private Polyline mPolyline;
    private String StreetName;

    private SharedPreferences sharedPref;

    //address
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maintransport, container, false);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());

        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setDisplayShowTitleEnabled(false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbarMain);
        TextView txt_toolbarTitle = toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText(R.string.transport);

        //maps
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.transport_mapFragment);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(this);

        //
        txt_transport_routeDistance = view.findViewById(R.id.txt_transport_routeDistance);
        txt_transportStreet = view.findViewById(R.id.txt_transportStreet);

        //inside maps
        //inside map
        android.widget.Button btn_driverCall = view.findViewById(R.id.btn_driverCall);
        btn_driverCall.setOnClickListener(view1 -> {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Contact Driver");
            alertDialog.setIcon(R.drawable.iconcall);
            alertDialog.setPositiveButton("Call", (dialog, which) ->
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", String.valueOf(99999999), null))));

            alertDialog.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            alertDialog.show();
        });

        return view;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == 100) {
            if (!verifyAllPermissions(grantResults)) {
                Toast.makeText(getActivity(), "No sufficient permissions", Toast.LENGTH_LONG).show();
            } else {
                getMyLocation();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private boolean verifyAllPermissions(int[] grantResults) {

        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void getMyLocation() {

        // Getting LocationManager object from System Service LOCATION_SERVICE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mLocationManager = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);
        }

        //when polyline appears
        LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mMap.clear();

                mOrigin = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mOrigin, 16));

                MarkerOptions mMarkerOptionsOrigin = new MarkerOptions().position(mOrigin).title("My location");
                BitmapDescriptor iconBus = BitmapDescriptorFactory.fromResource(R.drawable.iconsbus);

                mMarkerOptionsOrigin.icon(iconBus);

                mMap.addMarker(mMarkerOptionsOrigin);

                //distance
                float[] distance = new float[10];
                Location.distanceBetween(mOrigin.latitude, mOrigin.longitude, mDestination.latitude, mDestination.longitude, distance);
                float distanceString = distance[0] / 1000;
                @SuppressLint("DefaultLocale") String kms = String.format("%.2f", distanceString) + " Km's";
                txt_transport_routeDistance.setText(kms);

                //address
                Geocoder gcd = new Geocoder(getContext(), Locale.getDefault());
                List<Address> addresses = null;

                try {
                    try {
                        addresses = gcd.getFromLocation(mOrigin.latitude, mOrigin.longitude, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert addresses != null;
                    if (addresses.size() > 0) {
                        StreetName = addresses.get(0).getAddressLine(0);
                        String knownName = addresses.get(0).getFeatureName();
                        String postalCode = addresses.get(0).getPostalCode();

                        String finalAddress = "";
                        if (StreetName != null) {
                            finalAddress = finalAddress + " " + StreetName;
                            finalAddress.trim();
                            txt_transportStreet.setText(finalAddress);
                        }

                        if (knownName != null) {
                            finalAddress = finalAddress + " " + knownName;
                            finalAddress.trim();
                            txt_transportStreet.setText(finalAddress);
                        }

                        if (postalCode != null) {
                            finalAddress = finalAddress + " " + postalCode + " ";
                            finalAddress.trim();
                            txt_transportStreet.setText(finalAddress);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                if (mOrigin != null && mDestination != null)
                    drawRoute();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED) {
                mMap.setMyLocationEnabled(true);
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, mLocationListener);

                mDestination = new LatLng(24.8746, 67.0398);
                mMap.clear();
                MarkerOptions mMarkerOptions = new MarkerOptions().position(mDestination).title("Destination");
                mMap.addMarker(mMarkerOptions);
                if (mOrigin != null && mDestination != null)
                    drawRoute();

            } else {
                requestPermissions(new String[]{
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                }, 100);
            }
        }

    }


    private void drawRoute() {

        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(mOrigin, mDestination);

        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }


    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Key
        String key = "key=" + getString(R.string.google_maps_key);

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + key;

        // Output format
        String output = "json";

        // Building the url to the web service
        Log.d("gson", "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters);
        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception on download", e.toString());
        } finally {
            assert iStream != null;
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(getActivity()), R.raw.mapstyle));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }


        getMyLocation();
    }

    /**
     * A class to download data from Google Directions URL
     */
    @SuppressLint("StaticFieldLeak")
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("DownloadTask", "DownloadTask : " + data);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Directions in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                JSON_Directions parser = new JSON_Directions();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(Objects.requireNonNull(point.get("lat")));
                    double lng = Double.parseDouble(Objects.requireNonNull(point.get("lng")));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(3);
                lineOptions.color(getResources().getColor(R.color.colorAccentLight));
            }

            // Drawing polyline in the Google Map for the i-th route
            if (lineOptions != null) {
                if (mPolyline != null) {
                    mPolyline.remove();
                }
                mPolyline = mMap.addPolyline(lineOptions);

            } else
                Toast.makeText(getActivity(), "No route is found", Toast.LENGTH_LONG).show();
        }
    }


}
