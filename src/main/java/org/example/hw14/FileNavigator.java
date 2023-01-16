package org.example.hw14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileNavigator {
    private Map<String, List<FileData>> listMap = new HashMap<>();

    public Map<String, List<FileData>> getList() {
        return listMap;
    }

    public void add(String path, FileData fileData) throws IllegalArgumentException {
        if (!path.equals(fileData.getPath())) {
            throw new IllegalArgumentException("Path and fileData path are not consistent");
        }

        List<FileData> fileList = listMap.computeIfAbsent(path, k -> new ArrayList<>());
        fileList.add(fileData);
    }

    public List<FileData> find(String path) {
        return listMap.get(path);
    }

    public List<FileData> filterBySize(long size) {
        return listMap.values().stream()
                .flatMap(List::stream)
                .filter(f -> f.getSize() <= size)
                .collect(Collectors.toList());
    }

    public List<FileData> sortBySize(SortType sortType) {
        return listMap.values().stream()
                .flatMap(List::stream)
                .sorted((f1, f2) -> {
                    if (sortType == SortType.ASCENDING) {
                        return Long.compare(f1.getSize(), f2.getSize());
                    } else {
                        return Long.compare(f2.getSize(), f1.getSize());
                    }
                })
                .collect(Collectors.toList());
    }

    public void remove(String path) {
        listMap.remove(path);
    }
}
