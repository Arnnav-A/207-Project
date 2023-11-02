package view;

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
    public String viewName = "search";
    private final SearchViewModel searchViewModel;
    // create InputField for typing the city name and filter.
    private final JTextField cityNameInputField = new JTextField(15);
    private final JTextField filterInputField = new JTextField(15);
    // create searchController in order to pass input data.
    private final SearchController searchController;
    // create button for search.
    private final JButton search;

    public SearchView(SearchViewModel searchViewModel, SearchController searchController) {
        this.searchViewModel = searchViewModel;
        this.searchController = searchController;
        searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        // create labels for the city name and filter.
        LabelTextPanel cityNameInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.CITY_NAME_LABEL), cityNameInputField);
        LabelTextPanel filterInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.FILTER_LABEL), filterInputField);
        // create panel for the search button.
        JPanel buttons = new JPanel();
        search = new JButton(SearchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        // add Action Listener to check if the event is search.
        search.addActionListener(
                e -> {
                    if (e.getSource().equals(search)) {
                        SearchState curr = searchViewModel.getState();
                        // searchController.execute();
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState) evt.getNewValue();
        if (state.getCityNameError() != null) {
            JOptionPane.showMessageDialog(this, state.getCityNameError());
        }
    }
}
