package org.minerift.api.deprecated;

import java.util.HashMap;
import java.util.Map;

public class Tree {

    protected Map<String, Node> parentNodes;

    public Tree() {
        this.parentNodes = new HashMap<>();
    }

    public void addTopNode(Node node) {
        parentNodes.put(node.getId(), node);
    }

    public void addNode(String path, Node node) {
        Node nodeFromPath = getNode(path);
        if(nodeFromPath == null) {
            // Path is invalid
            return;
        }
        nodeFromPath.addChild(node.getId(), node);
    }

    public void removeNode(String path, String id) {
        Node nodeFromPath = getNode(path);
        if(nodeFromPath == null) {
            // Path is invalid
            return;
        }
        nodeFromPath.removeChild(id);
    }

    // TODO Consider this
    public Node getNode(String path) {
        // Remove any dots at end of string without a node id specified
        while(path.charAt(path.length() - 1) == '.') {
            path = path.substring(0, path.length() - 1);
        }

        // Check if path is empty afterwards
        if(path.isEmpty()) {
            return null;
        }

        // Parse top node identifier from path
        String nodeId = path.substring(0, path.indexOf('.'));
        Node node = parentNodes.get(nodeId);
        if(node == null) return null;
        removeIdFromPath(path, nodeId);

        while(path.indexOf('.') != -1) {
            nodeId = path.substring(0, path.indexOf('.'));
            node = node.getChildren().get(nodeId);

            if(node == null) return null;

            // remove id from path for iteration
            removeIdFromPath(path, nodeId);
        }

        return node;
    }

    public Map<String, Node> getParentNodes() {
        return parentNodes;
    }

    private String removeIdFromPath(String path, String selectedCategory) {
        try {
            path.replaceFirst(path.substring(0, selectedCategory.length() + 1), "");
        } catch(StringIndexOutOfBoundsException ex) {
            // End of path; no need to process
            //path.replaceFirst(path.substring(0, selectedCategory.length()), "");
        }

        return path;
    }
}
