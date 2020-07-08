package UserInterface;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Labels {
    // might be useful to put in the same loop for performances loops createStringForLabel and createLabel
    public static List<String> createStringForLabel(List<HashMap<String, Integer>> urlViewList) {
        List<String> urlList = new ArrayList<>();
        for (HashMap<String, Integer> urlView : urlViewList) {
            String stringUrlView = urlView.toString();
            stringUrlView = stringUrlView.replaceFirst("\\{", "");
            stringUrlView = stringUrlView.replaceFirst("}$", "");
            stringUrlView = replaceLast(stringUrlView, "  Time in minutes: ");
            System.out.println(stringUrlView);
            urlList.add(stringUrlView);
        }
        return urlList;
    }

    public static List<Label> createLabel(List<String> stringForLabel) {
        List<Label> labelList = new ArrayList<>();
        for (String urlTimeString : stringForLabel) {
            Label tempLabel = new Label(urlTimeString);
            labelList.add(tempLabel);
        }
        return labelList;
    }

    public static GridPane loadLabels(List<Label> labelList, GridPane secondaryLayout) {
        int count = 0;
        for (Label label : labelList) {
            label.setFont(new Font("Arial", 16));
            secondaryLayout.add(label, 0, count);
            count++;
        }
        return secondaryLayout;
    }
    public static String replaceLast(String inputString, String replace){
        String reverse_replace = new StringBuffer(replace).reverse().toString();
        String reverse = new StringBuffer(inputString).reverse().toString();
        reverse = reverse.replaceFirst("=", reverse_replace); // you need to reverse needle and replacement string aswell!
        return new StringBuffer(reverse).reverse().toString();
    }
}
