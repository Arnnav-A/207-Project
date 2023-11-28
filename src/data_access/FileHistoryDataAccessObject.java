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

public class FileHistoryDataAccessObject implements GetHistoryDataAccessInterface, ClearHistoryDataAccessInterface, SaveDataAccessInterface {
    private final File historyFileCSV;

    public FileHistoryDataAccessObject(String historyFilePath) {
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
    public void clearHistory() {
        try {
            FileWriter fileWriter = new FileWriter(historyFileCSV);
            fileWriter.flush();
            fileWriter.close(); // return true
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
