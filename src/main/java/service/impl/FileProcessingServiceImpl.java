package service.impl;

import service.IFileProcessingService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chopra
 * 30/03/18
 */
public class FileProcessingServiceImpl implements IFileProcessingService {

    private static volatile FileProcessingServiceImpl fileProcessingService = null;

    private FileProcessingServiceImpl(){

    }

    public static FileProcessingServiceImpl getInstance() {
        System.out.println("file processing service impl created");
        if(fileProcessingService == null){
            synchronized (FileProcessingServiceImpl.class){
                if(fileProcessingService == null){
                    fileProcessingService = new FileProcessingServiceImpl();
                }
            }
        }
        return fileProcessingService;
    }

    private String processWord(String word){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<word.length();i++){
            int assciVal = (int)word.charAt(i);
            if((assciVal<=90 && assciVal>=65)||(assciVal<=122 && assciVal>=97)){
                builder.append(word.charAt(i));
            }
        }
        return builder.toString().toLowerCase();
    }

    public Set<String> processFile(List<String> filePath){

        int len = filePath.size();
        if (len<2){
            return null;
        }
        else {
            FileReader reader = null;
            try {
                Set<String> finalResult = new HashSet<String>();

                Set<String> prev = new HashSet<String>();
                Set<String> curr = new HashSet<String>();


                reader = new FileReader(filePath.get(0));
                BufferedReader bufferedReader = new BufferedReader(reader);

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] stringArray = line.split(" ");
                    for (int i = 0; i < stringArray.length; i++) {
                        prev.add(processWord(stringArray[i]));
                    }
                }


                for (int i = 1; i < len; i++) {

                    reader = new FileReader(filePath.get(i));
                    bufferedReader = new BufferedReader(reader);
                    line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] stringArray = line.split(" ");
                        for (int j = 0; j < stringArray.length; j++) {
                            String processedWord = processWord(stringArray[j]);
                            curr.add(processedWord);
                        }
                    }

                    finalResult.addAll(prev);
                    for (String str:prev){
                        if (!curr.contains(str)){
                            finalResult.remove(str);
                        }
                    }
                    prev.clear();
                    curr.clear();
                    prev.addAll(finalResult);
                    finalResult.clear();

                }

                return prev;
            }
            catch (IOException e){
                return null;
            }
        }
    }
}
