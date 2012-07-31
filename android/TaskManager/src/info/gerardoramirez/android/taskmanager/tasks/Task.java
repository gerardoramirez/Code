package info.gerardoramirez.android.taskmanager.tasks;

import android.location.Address;

public class Task {

	private String name;
	private boolean complete;
	private long id;
	private String address;
	private double latitude;
	private double longitude;

	public Task (String taskName) {
		setName(taskName);
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setAddress(Address a) {
		if(null == a) {
			address = null;
			latitude = longitude = 0;
		} else {
			int maxAddressLine = a.getMaxAddressLineIndex();
			StringBuffer sb = new StringBuffer("");
			for(int i=0; i<maxAddressLine; i++) {
				sb.append(a.getAddressLine(i) + " ");
			}
			address = sb.toString();
			latitude = a.getLatitude();
			longitude = a.getLongitude();
		}		
	}
	
	public boolean hasAddress() {
		return null != address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public boolean hasLocation() {
		return (latitude != 0) && (longitude != 0);
	}

	public void toggleComplete() {
		complete = !complete;
	}

}
