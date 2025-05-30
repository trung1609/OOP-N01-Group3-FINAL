package com.example.qlsv_oop_group3.general;

import java.util.ArrayList;
import java.util.List;

public class ObjectList<T extends ObjectGeneral> {
    private List<T> objects;

    public ObjectList() {
        this.objects = new ArrayList<>();
    }

    // Create
    public void create(T object) {
        try {
            if (object == null) {
                throw new IllegalArgumentException("Object cannot be null");
            }
            objects.add(object);
        } catch (Exception e) {
            throw new RuntimeException("Error creating object: " + e.getMessage());
        }
    }

    // Read
    public T read(int id) {
        try {
            return objects.stream()
                    .filter(obj -> obj.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Object not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error reading object: " + e.getMessage());
        }
    }

    // Update
    public void update(T object) {
        try {
            if (object == null) {
                throw new IllegalArgumentException("Object cannot be null");
            }

            int index = -1;
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).getId() == object.getId()) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                throw new RuntimeException("Object not found with id: " + object.getId());
            }

            objects.set(index, object);
        } catch (Exception e) {
            throw new RuntimeException("Error updating object: " + e.getMessage());
        }
    }

    // Delete
    public void delete(int id) {
            boolean removed = objects.removeIf(obj -> obj.getId() == id);
            if (!removed) {
                throw new RuntimeException("Không tìm thấy id: " + id);
            }
    }

    // Get all objects
    public List<T> getAll() {
        try {
            return new ArrayList<>(objects);
        } catch (Exception e) {
            throw new RuntimeException("Error getting all objects: " + e.getMessage());
        }
    }
}