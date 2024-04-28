package map;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;

import com.github.kevinsawicki.http.HttpRequest;

@SuppressWarnings("serial")
public class MapCustom extends JXMapViewer {
	private Set<SwingWaypoint> waypoints;
	private WaypointPainter<SwingWaypoint> swingWaypointPainter; 
	
    public EventLocationSelected getEvent() {
        return event;
    }

    public void setEvent(EventLocationSelected event) {
        this.event = event;
    }

    private EventLocationSelected event;

    public void init() {
    	waypoints = new HashSet<SwingWaypoint>(Arrays.asList(new SwingWaypoint("Pizzeria", new GeoPosition(56.53536283021426, 21.026817730163003))));
        swingWaypointPainter = new SwingWaypointOverlayPainter();
        
        swingWaypointPainter.setWaypoints(waypoints);
        setOverlayPainter(swingWaypointPainter);
        
        setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo("", "https://b.tile.openstreetmap.fr/hot")));
        setAddressLocation(new GeoPosition(56.53536283021426, 21.026817730163003));
        setZoom(20);
        MouseInputListener mm = new PanMouseInputListener(this);
        addMouseListener(mm);
        addMouseMotionListener(mm);
        addMouseWheelListener(new ZoomMouseWheelListenerCenter(this));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isRightMouseButton(me)) {
	                int x = me.getX();
	                int y = me.getY();
	
	                GeoPosition geoPosition = convertPointToGeoPosition(new Point(x, y));
	                double latitude = geoPosition.getLatitude();
	                double longitude = geoPosition.getLongitude();
	                System.out.println("[MAP] "+latitude+", "+longitude);
	                System.out.println("[MAP] Location Info: "+getLocation(geoPosition));
	                removeHomeWaypoint();
	                addHomeWaypoint(geoPosition);
                }
            }
        });
        for (SwingWaypoint w : waypoints) {
        	add(w.getButton());
        }
    }
    private void addHomeWaypoint(GeoPosition pos) {
    	waypoints.add(new SwingWaypoint("Home", pos));
    	swingWaypointPainter.setWaypoints(waypoints);
    	setOverlayPainter(swingWaypointPainter);
    	for (SwingWaypoint w : waypoints) {
    			if (!w.getText().equals("Pizzeria"))
    				add(w.getButton());
        }
    }
    private void removeHomeWaypoint() {
    	for (SwingWaypoint w : waypoints) {
			if (w.getText().equals("Home"))
				remove(w.getButton());
    	}
    	repaint();
    	revalidate();
    }
    public String getLocation(GeoPosition pos) {
        String body = HttpRequest.get("https://nominatim.openstreetmap.org/reverse?lat=" + pos.getLatitude() + "&lon=" + pos.getLongitude() + "&format=json").body();
        JSONObject json = new JSONObject(body);
        try {
        	return json.getString("display_name");
        } catch(JSONException e) {}
        return "Unknown Location.";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
