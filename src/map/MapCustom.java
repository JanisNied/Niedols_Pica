package map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;

import com.github.kevinsawicki.http.HttpRequest;
import com.graphhopper.util.shapes.GHPoint3D;

import main.Settings;
import states.MainView;

@SuppressWarnings("serial")
public class MapCustom extends JXMapViewer {
	private Set<SwingWaypoint> waypoints;
	private WaypointPainter<SwingWaypoint> swingWaypointPainter;
	private GeoPosition start = new GeoPosition(56.53536283021426, 21.026817730163003);
	private GeoPosition end;
	private double lastDeliveryfee;
	private MouseInputListener mm, action;
	private ZoomMouseWheelListenerCenter zoom;
	
    public EventLocationSelected getEvent() {
        return event;
    }
    public List<RoutingData> getRoutingData() {
        return routingData;
    }

    public void setRoutingData(List<RoutingData> routingData) {
        this.routingData = routingData;
        repaint();
    }

    private List<RoutingData> routingData;
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
        mm = new PanMouseInputListener(this);
        zoom = new ZoomMouseWheelListenerCenter(this);
        addMouseListener(mm);
        addMouseMotionListener(mm);
        addMouseWheelListener(zoom);
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
	                addHomeWaypoint(geoPosition);
                }
            }
        });
        for (SwingWaypoint w : waypoints) {
        	add(w.getButton());
        }
    }
    private void addHomeWaypoint(GeoPosition pos) {
       FullRoute route = Routing.createRoute(start, pos);
       if (!route.getRoutingData().isEmpty()) {
	       removeAll();
	       repaint();
	       revalidate();
	       waypoints = new HashSet<SwingWaypoint>(Arrays.asList(new SwingWaypoint("Pizzeria", new GeoPosition(56.53536283021426, 21.026817730163003)), new SwingWaypoint("Home", pos)));
	       swingWaypointPainter.setWaypoints(waypoints);
	       List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
	       painters.add(swingWaypointPainter);
	       setOverlayPainter(swingWaypointPainter);
	       for (SwingWaypoint w : waypoints) {
	       		add(w.getButton());
	       }
	       double fee = (route.getDistance()*0.001)*Settings.deliveryfee;
	       MainView.customer.setDeliveryFee(fee);
	       setLastDeliveryfee(fee);
	       System.out.print("[Delivery]"+(route.getDistance()*0.001)+"km * "+Settings.deliveryfee+"(per km) = "+fee);
	       MainView.updateCart();
	       setRoutingData(route.getRoutingData());
	       MainView.addressbox.setText(getLocation(pos));
	       MainView.mapMenuFull = false;
	       MainView.animatorDataMap.start();
	       setEnd(pos);
	       removeMouseListener(mm);
	       removeMouseMotionListener(mm);
	       removeMouseWheelListener(zoom);
       }
    }
    public GeoPosition getStart() {
    	return start;
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
        if (routingData != null && !routingData.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Path2D p2 = new Path2D.Double();
            first = true;
            for (RoutingData d : routingData) {
                draw(p2, d);
            }
            g2.setColor(new Color(28, 23, 255));
            g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(p2);
            g2.dispose();
        }
    }

    private boolean first = true;

    private void draw(Path2D p2, RoutingData d) {
        d.getPointList().forEach(new Consumer<GHPoint3D>() {
            @Override
            public void accept(GHPoint3D t) {
                Point2D point = convertGeoPositionToPoint(new GeoPosition(t.getLat(), t.getLon()));
                if (first) {
                    first = false;
                    p2.moveTo(point.getX(), point.getY());
                } else {
                    p2.lineTo(point.getX(), point.getY());
                }
            }
        });
}
	public double getLastDeliveryfee() {
		return lastDeliveryfee;
	}
	public void setLastDeliveryfee(double lastDeliveryfee) {
		this.lastDeliveryfee = lastDeliveryfee;
	}
	public GeoPosition getEnd() {
		return end;
	}
	public void setEnd(GeoPosition end) {
		this.end = end;
	}
}
