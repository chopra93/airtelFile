package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author chopra
 * 30/03/18
 */
public interface IFileProcessingService {
    Set<String> processFile(List<String> filePath);
}
