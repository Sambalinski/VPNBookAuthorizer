package com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileActions {
    
    /**
     * Get the parent directory of the executable jar file
     * Получить родительский каталог исполняемого jar-файла
     *
     * @return - path
     * @throws URISyntaxException
     */
    static Path getCurrentDirectory() throws URISyntaxException {
        return Paths.get(FileActions.class.getProtectionDomain().getCodeSource().getLocation().toURI());
    }
    
    
    /**
     * Read the contents of the file
     * Считать содержимое файла
     *
     * @param path
     * @return
     */
    static List<String> readAllLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * Replace line with  username & password
     * Заменить строку без указания на строку с указанием на файл с логином и паролем
     *
     * @param list
     * @param auth
     * @return
     */
    static boolean replacer(ArrayList<String> list, String auth) {
        boolean changed = false;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i).trim();
            if (s.equals("auth-user-pass")) {
                changed = true;
                String replacement = s.concat(" ").concat(auth);
                list.set(i, replacement);
            }
        }
        return changed;
    }
    
    
    /**
     * Write the changed list to a file
     * Запись изменённого списка в файл
     *
     * @param list
     * @param fileName
     */
    static void write(List<String> list, String fileName) {
        File file = new File(fileName);
        file.setWritable(true);
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String string : list) {
                fileWriter.write(string);
                fileWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
