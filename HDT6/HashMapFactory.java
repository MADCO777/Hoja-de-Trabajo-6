package HDT6;

import java.util.HashMap;
import java.util.Map;

public class HashMapFactory implements MapFactory {
    @Override
    public Map<String, Pokemon> createMap() {
        return new HashMap<>();
    }
}