package UserInterface;

import dbconnection.DataBaseContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseParser {

    // organizes data into a hashmap with lists of hashmaps
    public static HashMap<String, List<HashMap<String, Integer>>> createHashMapDataStructure(List<DataBaseContainer> listContainer) {
        HashMap<String, List<HashMap<String, Integer>>> urlInfo = new HashMap<>();
        for (DataBaseContainer container : listContainer) {
            // instantiates all needed collections
            List<HashMap<String, Integer>> tempListContainers = new ArrayList<>();
            HashMap<String, Integer> tempContainer = new HashMap<>();
            List<String> splitUrl = container.getSplitUrl();
            String endUrl = getUrlEndPoint(splitUrl);
            tempContainer.put(endUrl, container.getVisit_duration_secs());
            // checks to see the key already exits then updates the list
            if (urlInfo.containsKey(splitUrl.get(2))) {
                List<HashMap<String, Integer>> notUpdatedList = urlInfo.get(splitUrl.get(2));
                notUpdatedList.add(tempContainer);
            }
            // if it does not contain the the key it creates a new entry
            else {
                tempListContainers.add(tempContainer);
                urlInfo.put(splitUrl.get(2), tempListContainers);
            }
        }
        return urlInfo;
    }

    public static String getUrlEndPoint(List<String> strList) {
        String endPoint = "";
        for (int i = 2; i <= strList.size() - 1; i++) {
            endPoint = endPoint.concat(strList.get(i) + "/");
        }
        return endPoint;
    }

    // This starts a connection you may need to rewrite but it seems to run fast enough
    public static List<HashMap<String, Integer>> getResults(String t1, List<DataBaseContainer> ListContainer) {
        HashMap<String, List<HashMap<String, Integer>>> urlViewsMap = createHashMapDataStructure(ListContainer);
        return urlViewsMap.get(t1);
    }
}
