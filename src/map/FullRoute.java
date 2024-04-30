package map;

import java.util.List;

public class FullRoute {
	private List<RoutingData> data;
	private double distance;
	
	public FullRoute(List<RoutingData> data, double distance) {
		this.data = data;
		this.distance = distance;
	}
	public List<RoutingData> getRoutingData() {
		return data;
	}
	public void setRoutingData(List<RoutingData> routingData) {
		this.data = routingData;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
}
