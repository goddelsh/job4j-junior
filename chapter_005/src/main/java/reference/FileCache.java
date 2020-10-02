package reference;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class FileCache {

    private Map<String, Cache<String>> cacheMap;

    public FileCache() {
        cacheMap = new HashMap<>();
    }

    String getFile(String path) {
        String result;
        if (cacheMap.containsKey(path)) {
            result = cacheMap.get(path).getObject();
            if (result == null) {
                String file = this.loadFile(path);
                cacheMap.get(path).putObject(file);
                result = cacheMap.get(path).getObject();
            }
        } else {
            var cacheObject = new Cache<String>();
            String file = this.loadFile(path);
            cacheObject.putObject(file);
            cacheMap.put(path, cacheObject);
            result = cacheObject.getObject();
        }
        return result;
    }



    String loadFile(String path) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(line -> result.append(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
