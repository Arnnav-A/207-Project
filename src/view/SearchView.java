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

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener{
    public String viewName = "search";
    private final SearchViewModel searchViewModel;
    private final JTextField citynameInputField = new JTextField(15);
    private final JTextField filterInputField = new JTextField(15);
    private final SearchController searchController;
    private final JButton search;

    public SearchView(SearchViewModel searchViewModel, SearchController searchController) {
        this.searchViewModel = searchViewModel;
        this.searchController = searchController;
        searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTextPanel citynameInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.CITYNAME_LABEL), citynameInputField);
        LabelTextPanel filterInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.FILTER_LABEL), filterInputField);

        JPanel buttons = new JPanel();
        search = new JButton(SearchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);

        search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(search)) {
                            SearchState curr = searchViewModel.getState();

                            // searchController.execute();

                        }
                    }
                }
        );
        citynameInputField.addKeyListener(
                new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState curr = searchViewModel.getState();
                        curr.setCityname(citynameInputField.getText() + e.getKeyChar());
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
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState) evt.getNewValue();
        if (state.getCitynameError() != null) {
            JOptionPane.showMessageDialog(this, state.getCitynameError());
        }
    }
}
