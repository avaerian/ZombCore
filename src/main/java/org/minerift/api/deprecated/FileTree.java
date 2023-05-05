package org.minerift.api.deprecated;

import org.bukkit.Bukkit;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileTree extends Tree {

    private static final Yaml yaml = new Yaml();
    private static final byte TEMP_EMPTY = 0;

    private File file;

    public FileTree(File file) {
        this.file = file;

        // Iterate through each key and try and get other keys (as if it were a category)
        //Map<String, Object>


    }

    public void writeValue(String path, Node node) {
        Node nodeFromPath = getNode(path);
        if(node == null) {
            return;
        }
        //nodeFromPath
    }

    public void writeValue(String path, String key, Object value) {
        //writeValue(path, new Node(key, value));
        new NodeIterator(this, path) {
            @Override
            public void forEachNode(Node node, String[] sections, int index) {

                // Check if child node exists in path
                String nextNodeId = new ArrayList<>(Arrays.asList(sections)).get(index + 1);
                if(nextNodeId != null) {
                    Node nextNode = node.getChildren().get(nextNodeId);
                    if(nextNode == null) {
                        // Next node doesn't exist; create node
                        // TODO nextNode = new Node();
                        node.getChildren().put(nextNodeId, nextNode);
                    }
                }
            }
        }.start();
    }

    public Object readValue(String path) {
        Node node = getNode(path);
        if(node == null) {
            return null;
        }
        return node.getValue();
    }

    /**
     * Update file with changes made to node tree
     * @author AvaerianDev
     */
    public void save() {

        Map<Integer, List<Node>> levelMap = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();

        // Construct level map
        for(Node parentNode : parentNodes.values()) {
            constructLevelMap(0, parentNode, levelMap);
        }

        // Construct data map from level map


        // Dump data map to file
        try {
            yaml.dump(dataMap, new FileWriter(file));
        } catch(IOException ex) {
            ex.printStackTrace();
            Bukkit.getLogger().severe("Unable to dump data to file " + file.getName() + "!");
        }
    }

    private void constructLevelMap(int level, Node node, Map<Integer, List<Node>> levelMap) {

        // Add selected node to levelMap
        List<Node> levelNodes = levelMap.get(level) == null ? new ArrayList<>() : levelMap.get(level);
        levelNodes.add(node);

        // Branch off and do the same for other nodes
        for(Node childNode : node.getChildren().values()) {
            constructLevelMap(level + 1, childNode, levelMap);
        }
    }

    private void constructDataMap(Map<String, Object> dataMap) {

    }

    public File getFile() {
        return file;
    }

}
