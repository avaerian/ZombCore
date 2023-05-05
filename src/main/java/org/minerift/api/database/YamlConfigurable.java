package org.minerift.api.database;

import org.minerift.api.database.annotation.Saveable;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class YamlConfigurable {

    private static final Yaml yaml = new Yaml();
    private static final String SECTION_SEPARATOR = ".";

    public static YamlConfigurable load(File file) {
        Map<String, Object> map;
        try {
            map = yaml.load(new FileReader(file));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }

        return new YamlConfigurable(file, map == null ? new HashMap<>() : map);
    }

    private File file;
    private Map<String, Object> map;

    private YamlConfigurable(File file, Map<String, Object> map) {
        this.file = file;
        this.map = map;
    }

    public void write(String path, Object value) {
        // Parse sections
        List<String> sections = new ArrayList<>(Arrays.asList(path.split(SECTION_SEPARATOR)));
        String key = sections.get(sections.size() - 1);

        // Go to destination
        Map<String, Object> section = goTo(sections, true);

        // Write value to path
        section.put(key, value);
    }

    public <T extends Configurable> void write(T configurable) {

        // Get fields
        Field[] fields = configurable.getClass().getDeclaredFields();

        // Iterate through @Saveable fields
        for(Field field : fields) {
            try {
                Saveable tag = field.getAnnotation(Saveable.class);
                if(tag != null) { // If tag exists
                    field.setAccessible(true);
                    write(tag.path(), field.get(configurable));
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }

    public <T> T read(String path) {
        // Parse sections
        List<String> sections = new ArrayList<>(Arrays.asList(path.split(SECTION_SEPARATOR)));
        String key = sections.get(sections.size() - 1);

        // Go to destination
        Map<String, Object> section = goTo(sections, false);

        // Read value from path
        return section != null ? (T) section.get(key) : null;
    }

    public void removeSection(String path) {
        List<String> sections = new ArrayList<>(Arrays.asList(path.split(SECTION_SEPARATOR)));
        String key = sections.get(sections.size() - 1);

        // Go to destination
        Map<String, Object> section = goTo(sections, false);
        if(section == null) {
            return;
        }

        section.remove(key);
    }

    private Map<String, Object> goTo(List<String> sections, boolean createSections) {
        // Remove key (last section is key)
        sections.remove(sections.size() - 1);

        // Iterate through sections
        Map<String, Object> context = map;
        for(String section : sections) {
            if(!context.containsKey(section)) {
                if(createSections) {
                    context.put(section, new HashMap<String, Object>());
                } else {
                    return null;
                }
            }
            context = (Map<String, Object>) context.get(section);
        }

        return context;
    }

    public boolean pull(boolean logException) {
        try {
            this.map = yaml.load(new FileReader(file));
        } catch (FileNotFoundException ex) {
            if(logException) {
                ex.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public boolean push(boolean logException) {
        try {
            yaml.dump(map, new FileWriter(file));
        } catch (IOException ex) {
            if(logException) {
                ex.printStackTrace();
            }
            return false;
        }
        return true;
    }
}
