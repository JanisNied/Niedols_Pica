package map;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jxmapviewer.viewer.GeoPosition;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.util.GHUtility;
import com.graphhopper.util.Instruction;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.Translation;

public class Routing {
    public static FullRoute createRoute(GeoPosition start, GeoPosition end) {
        GraphHopper hopper = createGraphHopperInstance("./core/files/latvia.osm.pbf");
        FullRoute route = routing(hopper, start, end);
        hopper.close();
        return route;
    }

    static GraphHopper createGraphHopperInstance(String ghLoc) {
        GraphHopper hopper = new GraphHopper();
        hopper.setOSMFile(ghLoc);
        hopper.setGraphHopperLocation("routingcache/routing-graph-cache");
        hopper.setEncodedValuesString("car_access, car_average_speed");
        hopper.setProfiles(new Profile("car").setCustomModel(GHUtility.loadCustomModelFromJar("car.json")));
        hopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));
        hopper.importOrLoad();
        return hopper;
    }

    public static FullRoute routing(GraphHopper hopper, GeoPosition start, GeoPosition end) {
        GHRequest req = new GHRequest(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude()).
                setProfile("car").
                setLocale(Locale.US);
        List<RoutingData> list = new ArrayList<>();
        double distance = 0.00;
        try {
	        GHResponse rsp = hopper.route(req);
	        ResponsePath path = rsp.getBest();
	        Translation tr = hopper.getTranslationMap().getWithFallBack(Locale.UK);
	        InstructionList il = path.getInstructions();
	        for (Instruction instruction : il) {
	        	list.add(new RoutingData(instruction.getDistance(), instruction.getTurnDescription(tr), instruction.getPoints()));
	        }
	        distance = path.getDistance();
        } catch (RuntimeException e) {
        	System.out.println("[GRAPHHOPPER] Invalid Location!");
        }
        return new FullRoute(list, distance);
    }
}