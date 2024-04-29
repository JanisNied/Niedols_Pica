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
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;

import com.github.kevinsawicki.http.HttpRequest;

@SuppressWarnings("serial")
public class MapCustomOSM extends JXMapViewer {
	private static final int EARTH_RADIUS_KM = 6371;
	private Set<SwingWaypoint> waypoints;
	private WaypointPainter<SwingWaypoint> swingWaypointPainter;
	private GeoPosition start = new GeoPosition(56.53536283021426, 21.026817730163003);
	
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
        setAddressLocation(start);
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
	                addHomeWaypoint(geoPosition);
                }
            }
        });
        for (SwingWaypoint w : waypoints) {
        	add(w.getButton());
        }
    }
    private void addHomeWaypoint(GeoPosition pos) {
       removeAll();
       repaint();
       revalidate();
       waypoints = new HashSet<SwingWaypoint>(Arrays.asList(new SwingWaypoint("Pizzeria", new GeoPosition(56.53536283021426, 21.026817730163003)), new SwingWaypoint("Home", pos)));
       swingWaypointPainter.setWaypoints(waypoints);
       List<GeoPosition> track = Arrays.asList(start, pos);
       RoutePainter routePainter = new RoutePainter(track);
       List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
       painters.add(routePainter);
       painters.add(swingWaypointPainter);
       CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
       setOverlayPainter(painter);
       for (SwingWaypoint w : waypoints) {
       		add(w.getButton());
       }
       System.out.println(calculateDistance(start, pos));
    }
    public double calculateDistance(GeoPosition pos1, GeoPosition pos2) {
        double lat1 = Math.toRadians(pos1.getLatitude());
        double lon1 = Math.toRadians(pos1.getLongitude());
        double lat2 = Math.toRadians(pos2.getLatitude());
        double lon2 = Math.toRadians(pos2.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
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
