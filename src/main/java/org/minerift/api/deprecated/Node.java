package org.minerift.api.deprecated;

import java.util.HashMap;
import java.util.Map;

public class Node {

    // This node
    private String key;
    private Object value;

    // Hierarchy
    private Node parent;
    private Map<String, Node> children;

    public Node(Node parent, Map<String, Node> children) {
        this.parent = parent;
        this.children = children == null ? new HashMap<>() : children;
    }

    public Node(Map<String, Node> children) {
        this(null, children);
    }

    public Node(Node parent, Object value) {
        this(parent, new HashMap<>());
    }

    public Node(Object value) {
        this(null, null);
    }

    public Node getParent() {
        return parent;
    }

    public Map<String, Node> getChildren() {
        return children;
    }

    public String getId() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void addChild(String key, Node node) {
        children.putIfAbsent(key, node);
    }

    public void setChild(String key, Node node) {
        children.put(key, node);
    }

    public void removeChild(String key) {
        children.remove(key);
    }

    public boolean isParent() {
        return parent == null;
    }
}
