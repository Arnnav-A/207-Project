package data_access;

import use_case.clear_history.ClearHistoryDataAccessInterface;
import use_case.get_history.GetHistoryDataAccessInterface;
import use_case.save_history.SaveDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The class to save, get and clear search history using a CSV file.
 */
public class FileHistoryDataAccessObject implements GetHistoryDataAccessInterface, ClearHistoryDataAccessInterface, SaveDataAccessInterface {
    private final File historyFileCSV;

    /**
     * Constructor to create an instance of the File History Data Access Object
     * @param historyFilePath
     */
    public FileHistoryDataAccessObject(String historyFilePath) {
        this.historyFileCSV = new File(historyFilePath);
    }

    /**
     * Method to get all history saved in the CSV
     * @return An array list of history data, which itself is an array list. The history array list, if not empty, has 3 strings
     * where the first is the timestamp, followed by the city, followed by the filter.
     */
    @Override
    public ArrayList<ArrayList<String>> getHistory() {
        ArrayList<ArrayList<String>> history = new ArrayList<>();
        try {
            Scanner reader = new Scanner(historyFileCSV);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                ArrayList<String> historyLine = new ArrayList<>(List.of(data.split(",")));
                history.add(historyLine);
            }
            return history;
        } catch (FileNotFoundException e) {
            return history;
        }
    }

    /**
     * Method to clear the history CSV
     */
    @Override
    public void clearHistory() {
        try {
            FileWriter fileWriter = new FileWriter(historyFileCSV);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to save a search history in the CSV
     * @param now The timestamp at which the search was made
     * @param city The city used for search
     * @param filter The filter used for search
     */
    @Override
    public void save(LocalDateTime now, String city, String filter) {
        try {
            FileWriter filewriter = new FileWriter(historyFileCSV, true);
            String data = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "," + city + "," + filter + '\n';
            filewriter.write(data);
            filewriter.flush();
            filewriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
