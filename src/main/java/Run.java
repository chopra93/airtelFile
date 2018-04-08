import controller.FileProcessingController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chopra
 * 30/03/18
 */
public class Run {
    public static void main(String[] args) {
        FileProcessingController controller = FileProcessingController.getInstance();
        List<String> fileLocationList = new ArrayList<String>();
        fileLocationList.add("file1.txt");
        fileLocationList.add("file2.txt");
        fileLocationList.add("file3.txt");
        fileLocationList.add("file4.txt");
        controller.processFile(fileLocationList);

    }
}
