package view;

import interface_adapter.applyFilter.ApplyFilterController;
import interface_adapter.getFilter.GetFilterState;
import interface_adapter.getFilter.GetFilterViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class GetFilterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "getFilter";
    private GetFilterViewModel getFilterViewModel;
    DefaultComboBoxModel<String> modelParentFilter = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modelSubFilter_1 = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modelSubFilter_2 = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modelSubFilter_3 = new DefaultComboBoxModel<>();
    JComboBox<String> parentFilter = new JComboBox<>();
    JComboBox<String> subFilter_1 = new JComboBox<>();
    JComboBox<String> subFilter_2 = new JComboBox<>();
    JComboBox<String> subFilter_3 = new JComboBox<>();

    final JButton apply;

    public GetFilterView(GetFilterViewModel getFilterViewModel,
                         SearchViewModel searchViewModel){
                         // ApplyFilterController applyFilterController) {
        this.getFilterViewModel = getFilterViewModel;
        this.getFilterViewModel.addPropertyChangeListener(this);

        parentFilter.setModel(modelParentFilter);
        subFilter_1.setModel(modelSubFilter_1);
        subFilter_2.setModel(modelSubFilter_2);
        subFilter_3.setModel(modelSubFilter_3);

        JPanel buttons = new JPanel();

        apply = new JButton(getFilterViewModel.APPLY_BUTTON_LABEL);
        buttons.add(apply);

        apply.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(apply)) {
                            System.out.println("Click " + evt.getActionCommand());
                            SearchState searchState = searchViewModel.getState();
                            String filterChose = filterOutput();
                            System.out.println(filterChose);
                            searchState.setFilter(filterChose);
                        }
                    }
                }
        );

        parentFilter.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        // Ensure the event source is our JComboBox
                        if (e.getSource() == parentFilter) {
                            // Check if the item state changed to SELECTED
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                // Retrieve the selected item
                                String selectedItem = (String) parentFilter.getSelectedItem();
                                if (!selectedItem.equals("Please select a Filter")) {
                                    System.out.println("Choose: " + selectedItem);
                                    GetFilterState currState = getFilterViewModel.getState();
                                    currState.setSelectedParentFilter(selectedItem);
                                    getFilterViewModel.setState(currState);
                                    getFilterViewModel.setParentFilter();
                                    getFilterViewModel.parentFilterSelectionChanged();
                                    getFilterViewModel.firePropertyChanged();
                                }
                            }
                        }
                    }
                });

        subFilter_1.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        // Ensure the event source is our JComboBox
                        if (e.getSource() == subFilter_1) {
                            // Check if the item state changed to SELECTED
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                // Retrieve the selected item
                                String selectedItem = (String) subFilter_1.getSelectedItem();
                                assert selectedItem != null;
                                if (!selectedItem.equals("Please select a Filter")) {
                                    System.out.println("Choose: " + selectedItem);
                                    GetFilterState currState = getFilterViewModel.getState();
                                    currState.setSelectedSubFilter1(selectedItem);
                                    getFilterViewModel.setState(currState);
                                    getFilterViewModel.setSelectedSubFilter1(selectedItem);
                                    getFilterViewModel.subFilter1SelectionChanged();
                                    getFilterViewModel.firePropertyChanged();
                                }
                            }
                        }
                    }
                });

        subFilter_2.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        // Ensure the event source is our JComboBox
                        if (e.getSource() == subFilter_2) {
                            // Check if the item state changed to SELECTED
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                // Retrieve the selected item
                                String selectedItem = (String) subFilter_2.getSelectedItem();
                                assert selectedItem != null;
                                if (!selectedItem.equals("Please select a Filter")) {
                                    System.out.println("Choose: " + selectedItem);
                                    GetFilterState currState = getFilterViewModel.getState();
                                    currState.setSelectedSubFilter2(selectedItem);
                                    getFilterViewModel.setState(currState);
                                    getFilterViewModel.setSelectedSubFilter2(selectedItem);
                                    getFilterViewModel.subFilter2SelectionChanged();
                                    getFilterViewModel.firePropertyChanged();
                                }
                            }
                        }
                    }
                });

        subFilter_3.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        // Ensure the event source is our JComboBox
                        if (e.getSource() == subFilter_3) {
                            // Check if the item state changed to SELECTED
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                // Retrieve the selected item
                                String selectedItem = (String) subFilter_3.getSelectedItem();
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
        if (modelParentFilter.getSize() == 0) {
            updateParentOptions(getFilterState);
            updateSubFilter1Options(getFilterState);
            updateSubFilter2Options(getFilterState);
            updateSubFilter3Options(getFilterState);
        } else {
            checkChanged(getFilterState);
        }
    }


    private void checkChanged(GetFilterState state) {
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

    private String filterOutput() {
        GetFilterState state = getFilterViewModel.getState();
        String parentFilterName = state.getSelectedParentFilter();
        String subFilter1Name = state.getSelectedSubFilter1();
        String subFilter2Name = state.getSelectedSubFilter2();
        String subFilter3Name = state.getSelectedSubFilter3();
        if (subFilter3Name != null) {
            return subFilter3Name;
        } else if (subFilter2Name != null) {
            return subFilter2Name;
        } else if (subFilter1Name != null) {
            return subFilter1Name;
        } else {
            return parentFilterName;
        }
    }
}




