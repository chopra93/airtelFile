package controller;

import service.impl.FileProcessingServiceImpl;

import java.util.List;
import java.util.Set;

/**
 * @author chopra
 * 30/03/18
 */
public class FileProcessingController {

    private static  final FileProcessingServiceImpl fileProcessingServiceImpl = FileProcessingServiceImpl.getInstance();

    private static final FileProcessingController fileProcessingController = new FileProcessingController();

    public static FileProcessingController getInstance(){
        System.out.println("file processing COntroller created");
        return fileProcessingController;
    }

    private FileProcessingController(){
    }

    public void processFile(List<String> fileNames){
        Set<String> result = fileProcessingServiceImpl.processFile(fileNames);
        if (result==null){
            System.out.print("Error Occured");
        }
        else{
            for (String str: result){
                System.out.println(str);
            }
        }
    }


}
