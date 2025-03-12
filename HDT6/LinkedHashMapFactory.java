package HDT6;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapFactory implements MapFactory {
    @Override
    public Map<String, Pokemon> createMap() {
        return new LinkedHashMap<>();
    }
}