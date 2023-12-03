package view;

import interface_adapter.getFilter.GetFilterController;
import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.clear_history.ClearHistoryState;
import interface_adapter.clear_history.ClearHistoryViewModel;
import interface_adapter.get_history.GetHistoryController;
import interface_adapter.get_history.GetHistoryState;
import interface_adapter.get_history.GetHistoryViewModel;
import interface_adapter.get_saved_places.GetSavedController;
import interface_adapter.get_saved_places.GetSavedState;
import interface_adapter.get_saved_places.GetSavedViewModel;
import interface_adapter.save_history.SaveController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "search";
    private final SearchViewModel searchViewModel;
    final JTextField cityNameInputField = new JTextField(24);
    final JTextField filterInputField = new JTextField(15);
    private final JLabel errorField = new JLabel();
    private final SearchController searchController;
    private final GetFilterController getFilterController;
    final JButton search;
    final JButton getFilter;
    final JButton getHistory;
    final JButton clearHistory;
    final JButton getPlaces;
    private final JLabel picLabel = new JLabel();

    public SearchView(SearchViewModel searchViewModel, SearchController searchController, GetFilterController getFilterController, SaveController saveController,
                      GetHistoryController getHistoryController, ClearHistoryController clearHistoryController, ClearHistoryViewModel clearHistoryViewModel, GetHistoryViewModel getHistoryViewModel,
                      GetSavedController getSavedController, GetSavedViewModel getSavedViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchController = searchController;
        this.getFilterController = getFilterController;
        this.searchViewModel.addPropertyChangeListener(this);

        try {
            BufferedImage myPicture = ImageIO.read(new File("src/assets/title.png"));
            ImageIcon image = new ImageIcon(myPicture.getScaledInstance(288,96,Image.SCALE_DEFAULT));
            picLabel.setIcon(image);
        } catch (IOException ignored) {}
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        picLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel(searchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);

        LabelTextPanel cityNameInfo = new LabelTextPanel(
                new JLabel(searchViewModel.CITY_NAME_LABEL), cityNameInputField);
        LabelTextPanel filterInfo = new LabelTextPanel(

        new JLabel(searchViewModel.FILTER_LABEL), filterInputField);
        JPanel buttons = new JPanel();
        search = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        getFilter = new JButton(searchViewModel.GET_FILTER_LABEL);
        getHistory = new JButton(searchViewModel.GET_HISTORY_BUTTON_LABEL);
        clearHistory = new JButton(searchViewModel.CLEAR_HISTORY_BUTTON_LABEL);
        getPlaces = new JButton(searchViewModel.GET_SAVED_PLACES_BUTTON_LABEL);

        JPanel searchButton = new JPanel();
        searchButton.add(search);

        filterInfo.add(getFilter);

        buttons.add(getPlaces);
        buttons.add(getHistory);
        buttons.add(clearHistory);

        errorField.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorField.setAlignmentY(Component.CENTER_ALIGNMENT);
        errorField.setForeground(Color.RED);

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            SearchState currentState = searchViewModel.getState();

                            searchController.execute(
                                    currentState.getCityName(),
                                    currentState.getFilter()
                            );
                            saveController.execute(
                                    currentState.getCityName(),
                                    currentState.getFilter()
                            );
                        }
                    }
                }
        );

        getHistory.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getHistory)) {
                            getHistoryController.execute();
                            GetHistoryState currentState = getHistoryViewModel.getState();
                            ArrayList<ArrayList<String>> history = currentState.getHistory();
                            String message = "No history found.";
                            if (history.isEmpty()) {
                                JOptionPane.showMessageDialog(getHistory.getTopLevelAncestor(), message);
                            } else {
                                StringBuilder historyString = new StringBuilder();
                                for (ArrayList<String> historyEntry : history) {
                                    historyString.append(historyEntry.toString()).append("\n");
                                }
                                JOptionPane.showMessageDialog(getHistory.getTopLevelAncestor(), historyString);
                            }
                        }
                    }
                }
        );

        getFilter.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getFilter)) {
                            getFilterController.execute();
                        }
                    }
                }
        );

        clearHistory.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(clearHistory)) {
                            clearHistoryController.execute();
                            ClearHistoryState currentState = clearHistoryViewModel.getState();
                            JOptionPane.showMessageDialog(clearHistory.getTopLevelAncestor(), currentState.getClearMessage());
                        }
                    }
                }
        );

        getPlaces.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getPlaces)) {
                            getSavedController.execute();
                            GetSavedState state = getSavedViewModel.getState();
                            ArrayList<String> places = state.getPlaces();
                            String message = "No history found.";
                            if (places.isEmpty()) {
                                JOptionPane.showMessageDialog(getPlaces.getTopLevelAncestor(), message);
                            } else {
                                StringBuilder placesString = new StringBuilder();
                                for (String place : places) {
                                    placesString.append(place).append("\n");
                                }
                                JOptionPane.showMessageDialog(getPlaces.getTopLevelAncestor(), placesString);
                            }
                        }
                    }
                }
        );

        cityNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState curr = searchViewModel.getState();
                        curr.setCityName(cityNameInputField.getText() + e.getKeyChar());
                        searchViewModel.setState(curr);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        filterInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState curr = searchViewModel.getState();
                        curr.setFilter(filterInputField.getText() + e.getKeyChar());
                        searchViewModel.setState(curr);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        this.add(picLabel);
        this.add(cityNameInfo);
        this.add(filterInfo);
        this.add(errorField);
        this.add(searchButton);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState) evt.getNewValue();
        setFields(state);
        if (state.getNotice() != null) {
            JOptionPane.showMessageDialog(this, state.getNotice());
        }
    }


    private void setFields (SearchState state){
        cityNameInputField.setText(state.getCityName());
        filterInputField.setText(state.getFilter());
        errorField.setText(state.getError());
    }

}

