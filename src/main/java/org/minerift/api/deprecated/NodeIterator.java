package org.minerift.api.deprecated;

public abstract class NodeIterator {

    private Tree tree;
    private String path;
    private boolean isRunning;

    public NodeIterator(Tree tree, String path) {
        this.tree = tree;
        this.path = path;
        this.isRunning = false;
    }

    public abstract void forEachNode(Node node, String[] sections, int index);

    public void start() {

        // Initial data
        this.isRunning = true;
        String[] sections = path.split(".");
        int index = 0;

        // Initial node iteration
        Node node = tree.getParentNodes().get(sections[index]);
        if(node == null) { return; }
        forEachNode(node, sections, index);
        index++;

        // Iterate for rest of nodes
        while(index < sections.length) {
            if(isRunning) {
                node = node.getChildren().get(sections[index]);
                if(node == null) { return; }
                forEachNode(node, sections, index);
                index++;
            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}