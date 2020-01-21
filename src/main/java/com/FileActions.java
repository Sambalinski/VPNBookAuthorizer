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

    //Getting the parent directory of the executable jar file
    //Получение родительского каталога исполняемого jar-файла
    static Path getCurrentDirectory() throws URISyntaxException {
        return Paths.get(FileActions.class.getProtectionDomain().getCodeSource().getLocation().toURI());
    }

    //Read the contents of the file
    //Считываем содержимое файла
    static List<String> readAllLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //Replace line with  username & password
    //Заменяем строку без указания на строку с указанием на файл с логином и паролем
    static boolean replacer(ArrayList<String> list, String auth) {
        boolean changed = false;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i).trim();
            if (s.equals("auth-user-pass")) {
                changed = true;
                String replacement = s.concat(" ").concat(auth);
                list.add(i, replacement);
            }
        }
        return changed;
    }

    //Write the changed list to a file
    //Запись изменённого списка в файл
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
