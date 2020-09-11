package api.resource;

import java.util.HashMap;

public class ResourceManager {

    private static ResourceManager ourInstance = new ResourceManager();

    public static ResourceManager getInstance() {
        return ourInstance;
    }

    private HashMap<Resource.KEY, Resource> resources;

    private ResourceManager() {
    }
}
