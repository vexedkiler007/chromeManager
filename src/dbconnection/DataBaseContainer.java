package dbconnection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseContainer {
    final private String url;
    final private int visit_duration_secs;
    final int visit_count;
    final private List<String> splitUrl;

    DataBaseContainer(String url, int visit_duration_secs, int visit_count) {
        this.url = url;
        this.visit_duration_secs = visit_duration_secs;
        this.visit_count = visit_count;
        splitUrl = new ArrayList<String>(Arrays.asList(url.split("/"))) {
        };
    }

    public String getUrl() {
        return this.url;
    }

    public int getVisit_duration_secs() {
        return this.visit_duration_secs;
    }

    public int getVisit_count() {
        return this.visit_count;
    }

    public List<String> getSplitUrl() {
        return this.splitUrl;
    }
}
