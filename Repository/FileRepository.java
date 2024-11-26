package Repository;

import java.io.*;
import java.util.*;
import java.util.function.Function;

/**
 * File-based implementation of the IRepository interface.
 * Stores entities in a CSV file format.
 *
 * @param <T> The type of entity the repository manages.
 */
public class FileRepository<T> implements IRepository<T> {
    private final String filePath;
    private final Function<T, String> idExtractor;
    private final Function<String, T> deserializer;
    private final Function<T, String> serializer;

    /**
     * Constructs a new FileRepository.
     *
     * @param filePath     The file where entities are stored.
     * @param idExtractor  A function to extract the ID from an entity.
     * @param deserializer A function to convert a CSV line into an entity.
     * @param serializer   A function to convert an entity into a CSV line.
     */
    public FileRepository(String filePath, Function<T, String> idExtractor,
                          Function<String, T> deserializer, Function<T, String> serializer) {
        this.filePath = filePath;
        this.idExtractor = idExtractor;
        this.deserializer = deserializer;
        this.serializer = serializer;
    }

    private List<T> loadFromFile() {
        List<T> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(deserializer.apply(line));
            }
        } catch (FileNotFoundException e) {
            // If file does not exist, create it
            try {
                new File(filePath).createNewFile();
            } catch (IOException ioException) {
                System.err.println("Error creating file: " + ioException.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return data;
    }

    private void saveToFile(List<T> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T entity : data) {
                writer.write(serializer.apply(entity));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void create(T entity) {
        List<T> data = loadFromFile();
        if (data.stream().anyMatch(e -> idExtractor.apply(e).equals(idExtractor.apply(entity)))) {
            throw new IllegalArgumentException("Entity with ID " + idExtractor.apply(entity) + " already exists.");
        }
        data.add(entity);
        saveToFile(data);
    }

    @Override
    public T read(String id) {
        return loadFromFile().stream()
                .filter(entity -> idExtractor.apply(entity).equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(T entity) {
        List<T> data = loadFromFile();
        String id = idExtractor.apply(entity);
        data.removeIf(e -> idExtractor.apply(e).equals(id));
        data.add(entity);
        saveToFile(data);
    }

    @Override
    public void delete(String id) {
        List<T> data = loadFromFile();
        data.removeIf(e -> idExtractor.apply(e).equals(id));
        saveToFile(data);
    }

    @Override
    public List<T> getAll() {
        return loadFromFile();
    }
}
