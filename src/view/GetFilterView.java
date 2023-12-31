package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.getFilter.GetFilterState;
import interface_adapter.getFilter.GetFilterViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;


public class GetFilterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "getFilter";
    private GetFilterViewModel getFilterViewModel;
    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;
    DefaultComboBoxModel<String> modelParentFilter = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modelSubFilter_1 = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modelSubFilter_2 = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modelSubFilter_3 = new DefaultComboBoxModel<>();
    JComboBox<String> parentFilter = new JComboBox<>();
    JComboBox<String> subFilter_1 = new JComboBox<>();
    JComboBox<String> subFilter_2 = new JComboBox<>();
    JComboBox<String> subFilter_3 = new JComboBox<>();

    final JButton apply;
    final JButton reset;
    private final JLabel picLabel = new JLabel();

    public GetFilterView(GetFilterViewModel getFilterViewModel,
                         SearchViewModel searchViewModel,
                         ViewManagerModel viewManagerModel) {
        this.getFilterViewModel = getFilterViewModel;
        this.getFilterViewModel.addPropertyChangeListener(this);
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;

        parentFilter.setModel(modelParentFilter);
        subFilter_1.setModel(modelSubFilter_1);
        subFilter_2.setModel(modelSubFilter_2);
        subFilter_3.setModel(modelSubFilter_3);

        try {
            BufferedImage myPicture = ImageIO.read(new File("src/assets/title.png"));
            ImageIcon image = new ImageIcon(myPicture.getScaledInstance(504,168, Image.SCALE_DEFAULT));
            picLabel.setIcon(image);
        } catch (IOException ignored) {}
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        picLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        apply = new JButton(getFilterViewModel.APPLY_BUTTON_LABEL);
        reset = new JButton(getFilterViewModel.RESET_BUTTON_LABEL);
        buttons.add(reset);
        buttons.add(apply);

        apply.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(apply)) {
                            GetFilterState state = getFilterViewModel.getState();
                            System.out.println("Click " + evt.getActionCommand());
                            SearchState searchState = searchViewModel.getState();
                            String filterChose = filterOutput(state);
                            System.out.println(filterChose);
                            if (!filterChose.equals("Please select a Filter")) {
                                searchState.setFilter(filterChose);
                                searchViewModel.firePropertyChanged();
                                viewManagerModel.setActiveView("search");
                                viewManagerModel.firePropertyChanged();
                            } else {
                                state.setNotice("Please select a Filter!");
                                getFilterViewModel.firePropertyChanged();
                            }
                        }
                    }
                }
        );

        reset.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(reset)) {
                            System.out.println("Click " + evt.getActionCommand());
                            GetFilterState state = getFilterViewModel.getState();
                            state.setSelectedParentFilter("Please select a Filter");
                            state.setSelectedSubFilter1(null);
                            state.setSelectedSubFilter2(null);
                            state.setSelectedSubFilter3(null);
                            getFilterViewModel.setState(state);
                            getFilterViewModel.setParentFilter();
                            getFilterViewModel.resetSelection();
                            resetChoice();
                            getFilterViewModel.firePropertyChanged();
                        }
                    }
                }
        );

        parentFilter.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getSource() == parentFilter) {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                GetFilterState state = getFilterViewModel.getState();
                                String selectedItem = (String) parentFilter.getSelectedItem();
                                assert selectedItem != null;
                                if (!selectedItem.equals("Please select a Filter")) {
                                    System.out.println("Choose: " + selectedItem);
                                    state.setSelectedParentFilter(selectedItem);
                                    getFilterViewModel.setState(state);
                                    getFilterViewModel.setSelectedParentFilter();
                                    getFilterViewModel.parentFilterSelectionChanged();
                                    getFilterViewModel.firePropertyChanged();
                                } else {

                                    state.setSelectedParentFilter(null);
                                    state.setSubFilter1(new String[]{"Please select a Filter"});
                                    state.setSubFilter2(new String[]{"Please select a Filter"});
                                    state.setSubFilter3(new String[]{"Please select a Filter"});
                                    getFilterViewModel.setState(state);
                                    updateSubFilter1Options(state);
                                    updateSubFilter2Options(state);
                                    updateSubFilter3Options(state);
                                }
                            }
                        }
                    }
                });

        subFilter_1.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getSource() == subFilter_1) {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                String selectedItem = (String) subFilter_1.getSelectedItem();
                                GetFilterState state = getFilterViewModel.getState();
                                assert selectedItem != null;
                                if (!selectedItem.equals("Please select a Filter")) {
                                    System.out.println("Choose: " + selectedItem);
                                    state.setSelectedSubFilter1(selectedItem);
                                    getFilterViewModel.setState(state);
                                    getFilterViewModel.setSelectedSubFilter1(selectedItem);
                                    getFilterViewModel.subFilter1SelectionChanged();
                                    getFilterViewModel.firePropertyChanged();
                                } else {

                                    state.setSelectedSubFilter1(null);

                                    state.setSubFilter2(new String[]{"Please select a Filter"});
                                    state.setSubFilter3(new String[]{"Please select a Filter"});
                                    getFilterViewModel.setState(state);
                                    updateSubFilter2Options(state);
                                    updateSubFilter3Options(state);
                                }
                            }
                        }
                    }
                });

        subFilter_2.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getSource() == subFilter_2) {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                GetFilterState state = getFilterViewModel.getState();
                                String selectedItem = (String) subFilter_2.getSelectedItem();
                                assert selectedItem != null;
                                if (!selectedItem.equals("Please select a Filter")) {
                                    System.out.println("Choose: " + selectedItem);
                                    state.setSelectedSubFilter2(selectedItem);
                                    getFilterViewModel.setState(state);
                                    getFilterViewModel.setSelectedSubFilter2(selectedItem);
                                    getFilterViewModel.subFilter2SelectionChanged();
                                    getFilterViewModel.firePropertyChanged();
                                } else {

                                    state.setSelectedSubFilter2(null);
                                    state.setSubFilter3(new String[]{"Please select a Filter"});
                                    getFilterViewModel.setState(state);
                                    updateSubFilter3Options(state);
                                }
                            }
                        }
                    }
                });

        subFilter_3.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getSource() == subFilter_3) {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                String selectedItem = (String) subFilter_3.getSelectedItem();
                                assert selectedItem != null;
                                if (!selectedItem.equals("Please select a Filter")) {
                                    System.out.println("Choose: " + selectedItem);
                                    GetFilterState currState = getFilterViewModel.getState();
                                    currState.setSelectedSubFilter3(selectedItem);
                                    getFilterViewModel.setState(currState);
                                    getFilterViewModel.setSelectedSubFilter3(selectedItem);
                                    getFilterViewModel.subFilter3SelectionChanged();
                                    getFilterViewModel.firePropertyChanged();
                                }
                            }
                        }
                    }
                });

        this.add(picLabel);
        this.add(parentFilter);
        this.add(subFilter_1);
        this.add(subFilter_2);
        this.add(subFilter_3);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetFilterState getFilterState = (GetFilterState) evt.getNewValue();
        if (getFilterState.getNotice() != null) {
            JOptionPane.showMessageDialog(this, getFilterState.getNotice());
            getFilterState.setNotice(null);
        } else if (modelParentFilter.getSize() == 0) {
            updateParentOptions(getFilterState);
            updateSubFilter1Options(getFilterState);
            updateSubFilter2Options(getFilterState);
            updateSubFilter3Options(getFilterState);
        } else {
            checkChanged(getFilterState);
        }
    }


    private void checkChanged(GetFilterState state) {
        if (state.getParentFilter().length > 1) {
            for (int i = 0; i < state.getParentFilter().length; i++) {
                if (!state.getParentFilter()[i].equals(modelParentFilter.getElementAt(i))) {
                    modelParentFilter.removeAllElements();
                    updateParentOptions(state);
                }
            }
        } else {
            modelSubFilter_1.removeAllElements();
            updateSubFilter1Options(state);
        }
        if (state.getSubFilter_1().length > 1) {
            for (int i = 0; i < state.getSubFilter_1().length; i++) {
                if (!state.getSubFilter_1()[i].equals(modelSubFilter_1.getElementAt(i))) {
                    modelSubFilter_1.removeAllElements();
                    updateSubFilter1Options(state);
                }
            }
        } else {
            modelSubFilter_1.removeAllElements();
            updateSubFilter1Options(state);
        }

        if (state.getSubFilter_2().length > 1) {
            for (int i = 0; i < state.getSubFilter_2().length; i++) {
                if (!state.getSubFilter_2()[i].equals(modelSubFilter_2.getElementAt(i))) {
                    modelSubFilter_2.removeAllElements();
                    updateSubFilter2Options(state);
                }
            }
        } else {
            modelSubFilter_2.removeAllElements();
            updateSubFilter2Options(state);
        }

        if (state.getSubFilter_3().length > 1) {
            for (int i = 0; i < state.getSubFilter_3().length; i++) {
                if (!state.getSubFilter_3()[i].equals(modelSubFilter_3.getElementAt(i))) {
                    modelSubFilter_3.removeAllElements();
                    updateSubFilter3Options(state);
                }
            }
        } else {
            modelSubFilter_3.removeAllElements();
            updateSubFilter3Options(state);
        }
    }

    private void updateParentOptions(GetFilterState state) {
        for (int i = 0; i < state.getParentFilter().length; i++) {
            modelParentFilter.addElement(state.getParentFilter()[i]);
        }
    }

    private void updateSubFilter1Options(GetFilterState state) {
        modelSubFilter_1.removeAllElements();
        for (int i = 0; i < state.getSubFilter_1().length; i++) {
            modelSubFilter_1.addElement(state.getSubFilter_1()[i]);
        }
    }

    private void updateSubFilter2Options(GetFilterState state) {
        modelSubFilter_2.removeAllElements();
        for (int i = 0; i < state.getSubFilter_2().length; i++) {
            modelSubFilter_2.addElement(state.getSubFilter_2()[i]);
        }
    }

    private void updateSubFilter3Options(GetFilterState state) {
        modelSubFilter_3.removeAllElements();
        for (int i = 0; i < state.getSubFilter_3().length; i++) {
            modelSubFilter_3.addElement(state.getSubFilter_3()[i]);
        }
    }

    private String filterOutput(GetFilterState state) {
        //String parentFilterName = getFilterViewModel.getSelectedParentFilter();
        String parentFilterName = state.getSelectedParentFilter();
        String subFilter1Name = state.getSelectedSubFilter1();
        String subFilter2Name = state.getSelectedSubFilter2();
        String subFilter3Name = state.getSelectedSubFilter3();

        if (subFilter3Name != null) {
            return String.join(".", parentFilterName, subFilter1Name, subFilter2Name, subFilter3Name);
        } else if (subFilter2Name != null) {
            return String.join(".", parentFilterName, subFilter1Name, subFilter2Name);
        } else if (subFilter1Name != null) {
            return String.join(".", parentFilterName, subFilter1Name);
        } else if (parentFilterName != null){
            return parentFilterName;
        } else {
            return "Please select a Filter";
        }
    }

    private void resetChoice() {
        modelParentFilter.removeAllElements();
        modelSubFilter_1.removeAllElements();
        modelSubFilter_2.removeAllElements();
        modelSubFilter_3.removeAllElements();
    }
}





