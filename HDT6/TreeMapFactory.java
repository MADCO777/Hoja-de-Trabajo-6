package HDT6;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapFactory implements MapFactory {
    @Override
    public Map<String, Pokemon> createMap() {
        return new TreeMap<>();
    }
}