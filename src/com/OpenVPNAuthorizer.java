package com;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class OpenVPNAuthorizer {
    public static void main(String[] args) throws IOException, URISyntaxException {
        final Path path = FileActions.getCurrentDirectory().getParent();
        String auth = "vpnbook_auth.txt";

        Files.walk(path)
                .filter(s -> s.toString().endsWith(".ovpn") && s.getFileName().toString().startsWith("vpnbook"))
                .forEach(s -> {
                    ArrayList<String> list = (ArrayList<String>) FileActions.readAllLines(s);
                    boolean changed = FileActions.replacer(list, auth);
                    if (changed) // If the list has been changed - write to the file. Если список был изменён - выполняем запись в файл
                        FileActions.write(list, s.toString());
                });
    }
}
