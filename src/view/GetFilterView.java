package view;

import interface_adapter.getFilter.GetFilterState;
import interface_adapter.getFilter.GetFilterViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.*;
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

    public GetFilterView(GetFilterViewModel getFilterViewModel, SearchView searchView) {
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
                        //String parentFilter = parentFilter.getSelected;
                        if (evt.getSource().equals(apply)) {
                            // SearchState currState = searchView.;
                            System.out.println("Click " + evt.getActionCommand());

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
                        if (e.getSource() == parentFilter) {
                            // Check if the item state changed to SELECTED
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                // Retrieve the selected item
                                String selectedItem = (String) parentFilter.getSelectedItem();
                                //getFilterViewModel.
                            }
                        }
                    }
                });

        subFilter_2.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        // Ensure the event source is our JComboBox
                        if (e.getSource() == parentFilter) {
                            // Check if the item state changed to SELECTED
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                // Retrieve the selected item
                                String selectedItem = (String) parentFilter.getSelectedItem();

                            }
                        }
                    }
                });

        subFilter_3.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        // Ensure the event source is our JComboBox
                        if (e.getSource() == parentFilter) {
                            // Check if the item state changed to SELECTED
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                // Retrieve the selected item
                                String selectedItem = (String) parentFilter.getSelectedItem();

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
        for (int i = 0; i < getFilterState.getParentFilter().length; i++) {
            modelParentFilter.addElement(getFilterState.getParentFilter()[i]);
        }
        for (int i = 0; i < getFilterState.getSubFilter_1().length; i++) {
            modelSubFilter_1.addElement(getFilterState.getSubFilter_1()[i]);
        }
        for (int i = 0; i < getFilterState.getSubFilter_2().length; i++) {
            modelSubFilter_2.addElement(getFilterState.getSubFilter_2()[i]);
        }
        for (int i = 0; i < getFilterState.getSubFilter_3().length; i++) {
            modelSubFilter_3.addElement(getFilterState.getSubFilter_3()[i]);
        }

    }

    private void setFields(GetFilterState getFilterState) {
        parentFilter = new JComboBox<>(getFilterState.getParentFilter());
        subFilter_1 = new JComboBox<>(getFilterState.getSubFilter_1());
        subFilter_2 = new JComboBox<>(getFilterState.getSubFilter_2());
        subFilter_3 = new JComboBox<>(getFilterState.getSubFilter_3());
    }
}




