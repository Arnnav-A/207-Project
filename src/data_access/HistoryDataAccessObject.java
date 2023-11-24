package data_access;

import use_case.clear_history.ClearDataAccessInterface;
import use_case.get_history.GetHistoryDataAccessInterface;
import use_case.save_history.SaveDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HistoryDataAccessObject implements GetHistoryDataAccessInterface, ClearDataAccessInterface, SaveDataAccessInterface {
    private final File historyFileCSV;

    public HistoryDataAccessObject(String historyFilePath) {
        this.historyFileCSV = new File(historyFilePath);
    }

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

    @Override
    public void clearHistory() { // Will be changing to boolean depending on success
        try {
            FileWriter fileWriter = new FileWriter(historyFileCSV);
            fileWriter.flush();
            fileWriter.close(); // return true
        } catch (IOException e) {
            throw new RuntimeException(e); // return False
        }
    }

    @Override
    public void save(LocalDateTime now, String city, String filter) { // Will be changing to boolean depending on success
        try {
            FileWriter filewriter = new FileWriter(historyFileCSV, true);
            String data = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "," + city + "," + filter;
            filewriter.write(data);
            filewriter.flush();
            filewriter.close(); // return true
        } catch (IOException e) {
            throw new RuntimeException(e); // return false
        }
    }
}
