package info.gerardoramirez.android.taskmanager;

import info.gerardoramirez.android.taskmanager.views.AddressOverlay;

import java.io.IOException;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;


public class AddLocationMapActivity extends MapActivity {

	public static final String ADDRESS_RESULT = "address";
	private static final int zoomLevel = 12;
	
	private EditText addressText;
	private Button mapLocationButton;
	private Button useLocationButton;
	private MapView mapView;
	private Address address;
	private AlertDialog emptyTextDialog;
	private MyLocationOverlay myLocationOverlay;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.add_location);
		setUpViews();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		myLocationOverlay.enableMyLocation();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		myLocationOverlay.disableMyLocation();
	}
	
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	protected boolean isLocationDisplayed() {
		return true;
	}
	
	//TODO remove an address if you add an address a second time
	protected void mapCurrentAddress() {
		String addressString = addressText.getText().toString();
		
		Geocoder g = new Geocoder(this);
		List<Address> addresses = null;
		
		try {
			addresses = g.getFromLocationName(addressString, 1);
			if(null != addresses && addresses.size() > 0) {
				address = addresses.get(0);
				
				List<Overlay> mapOverlays = mapView.getOverlays();
				AddressOverlay addressOverlay = new AddressOverlay(address);
				mapOverlays.add(addressOverlay);
				mapOverlays.add(myLocationOverlay);
				mapView.invalidate();
				
				final MapController mapController = mapView.getController();
				mapController.animateTo(addressOverlay.getGeopoint(), new Runnable() {
					public void run() {
						mapController.setZoom(zoomLevel);
					}
				});
				useLocationButton.setEnabled(true);
			}
			else {
				createEmptyAddressDialog();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	private void setUpViews() {
		addressText = (EditText)findViewById(R.id.task_address);	
		mapLocationButton = (Button)findViewById(R.id.map_location_button);
		useLocationButton = (Button)findViewById(R.id.use_this_location_button);
		mapView = (MapView)findViewById(R.id.map);
		myLocationOverlay = new MyLocationOverlay(this, mapView);
		myLocationOverlay.enableMyLocation();
		mapView.getOverlays().add(myLocationOverlay);
		mapView.invalidate();
		
		useLocationButton.setEnabled(false);
		mapView.setBuiltInZoomControls(true);
	
		mapLocationButton.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				mapCurrentAddress();
			}
		});
		
		useLocationButton.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				if(null != address) {
					Intent intent = new Intent();
					intent.putExtra(ADDRESS_RESULT, address);
					setResult(RESULT_OK, intent);
				}
				finish();
			}
		});		
		
	}
	
	public void createEmptyAddressDialog() {

			emptyTextDialog = new AlertDialog.Builder(this)
			.setTitle(R.string.oops_title)
			.setMessage(R.string.empty_text_message)
			.setPositiveButton(R.string.ok, new AlertDialog.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					emptyTextDialog.dismiss();	
				}
			})
			.create();
			emptyTextDialog.show();

	}
	
	
}
