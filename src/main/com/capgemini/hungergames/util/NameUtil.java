package main.com.capgemini.hungergames.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class NameUtil {
    // If a human gets a gender instead of a custom Man and Woman object, remove this Enum
    public enum Gender {
        MALE("male.txt"), FEMALE("female.txt");

        private String path;

        Gender(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    private static final String NAME_FOLDER = "names/";

    public static String generateName(Gender gender) throws IOException, URISyntaxException {
        List<String> names = getNames(gender);
        int len = names.size();
        int pos = (int)(len * Math.random());
        return names.get(pos);
    }

    public static List<String> getNames(Gender gender) throws IOException, URISyntaxException {
        String file = NAME_FOLDER + gender.getPath();
        return readFileLines(file);
    }

    // Take from: https://stackoverflow.com/a/15749281
    private static List<String> readFileLines(String path) throws URISyntaxException, IOException {
        ClassLoader loader = NameUtil.class.getClassLoader();
        URL url = loader.getResource(path);

        Path filePath = Paths.get(url.toURI());
        return Files.readAllLines(filePath, StandardCharsets.UTF_8);
    }
}
