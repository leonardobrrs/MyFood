package br.ufal.ic.p2.myfood.services;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataPersistence {

    private final String fileName;

    public DataPersistence(String fileName) {
        this.fileName = fileName;
    }

    public void save(Map<?, ?> data) {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName))) {
            encoder.writeObject(data);
        } catch (Exception ignored) {
        }
    }

    public Map<?, ?> load() {
        File file = new File(fileName);
        if (!file.exists()) {
            return new HashMap<>();
        }

        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName))) {
            return (Map<?, ?>) decoder.readObject();
        } catch (Exception ignored) {
        }

        return new HashMap<>();
    }
}