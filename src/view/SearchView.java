package view;

import interface_adapter.clear.ClearController;
import interface_adapter.clear.ClearState;
import interface_adapter.clear.ClearViewModel;
import interface_adapter.get_history.GetHistoryController;
import interface_adapter.save_history.SaveController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search";
    private final SearchViewModel searchViewModel;
    // create InputField for typing the city name and filter.
    final JTextField cityNameInputField = new JTextField(15);
    final JTextField filterInputField = new JTextField(15);
    private final JLabel errorField = new JLabel();
    // create searchController in order to pass input data.
    private final SearchController searchController;
    // create button for search.
    final JButton search;
    final JButton getHistory;
    final JButton clearHistory;


    public SearchView(SearchViewModel searchViewModel, SearchController searchController, SaveController saveController,
                      GetHistoryController getHistoryController, ClearController clearController, ClearViewModel clearViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchController = searchController;
        this.searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("PlaceFinder");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        // create labels for the city name and filter.

        LabelTextPanel cityNameInfo = new LabelTextPanel(
                new JLabel("City"), cityNameInputField);
        LabelTextPanel filterInfo = new LabelTextPanel(
                new JLabel("Filter"), filterInputField);
        // create panel for the search, get history, and clear history buttons.
        JPanel buttons = new JPanel();
        search = new JButton(searchViewModel.SEARCH_BUTTON_LABEL);
        getHistory = new JButton(searchViewModel.GET_HISTORY_BUTTON_LABEL);
        clearHistory = new JButton(searchViewModel.CLEAR_HISTORY_BUTTON_LABEL);
        buttons.add(search);
        buttons.add(getHistory);
        buttons.add(clearHistory);

        errorField.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorField.setAlignmentY(Component.CENTER_ALIGNMENT);
        errorField.setForeground(Color.RED);

        // add Action Listener to check if the event is search.
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
        // add Action Listener to check if the event is getHistory.
        getHistory.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getHistory)) {

                            getHistoryController.execute();
                        }
                    }
                }
        );

        // add Action Listener to check if the event is clearHistory.
        clearHistory.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(clearHistory)) {
                            clearController.execute();
                            ClearState currentState = clearViewModel.getState();
                            JOptionPane.showMessageDialog(clearHistory.getTopLevelAncestor(), currentState.getClearMessage());
                        }
                    }
                }
        );


        // add Key Listener to get what was entered in the input field.
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
        this.add(title);
        this.add(cityNameInfo);
        this.add(filterInfo);
        this.add(errorField);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {System.out.println("Click " + evt.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState) evt.getNewValue();
        setFields(state);
        }

    private void setFields(SearchState state) {
        cityNameInputField.setText(state.getCityName());
        filterInputField.setText(state.getFilter());
        errorField.setText(state.getError());
    }
}

