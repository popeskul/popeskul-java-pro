package org.example.hw14;

public class FileData {
    private String name;
    private String path;
    private long size;

    public FileData(String name, String path, long size) {
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    // toString
    @Override
    public String toString() {
        return "FileData{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                '}';
    }
}
