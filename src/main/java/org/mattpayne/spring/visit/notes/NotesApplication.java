package org.mattpayne.spring.visit.notes;

import org.mattpayne.spring.visit.notes.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class NotesApplication implements CommandLineRunner {

    @Autowired
    private UrlService urlService;

    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        processFileAddUrlSaveAsWeGo("src/main/resources/sample.data");
    }

    private void processFileAddUrlSaveAsWeGo(String fileName) throws IOException {
        List<String> list = getLinesFromFile(fileName);
        for (int i = 0; i < list.size(); i += 2) {
            String urlStr = list.get(i);
            List<String> tagList = new ArrayList<>();
            for (String str: list.get(i+1).split(" ")) {
                tagList.add(str);
            }
            urlService.addUrl(urlStr,tagList);
        }

    }

    private List<String> getLinesFromFile(String fileName) throws IOException {
        List<String> list = Files.readAllLines(new File(fileName).toPath(), Charset.defaultCharset());
        list = list.stream().filter(line -> line.trim().length() > 0).collect(Collectors.toList());
        return list;
    }
}
