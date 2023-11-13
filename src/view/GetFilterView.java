package view;

import interface_adapter.getFilter.GetFilterState;
import interface_adapter.getFilter.GetFilterViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetFilterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "getFilter";
    private GetFilterViewModel getFilterViewModel;
    final JComboBox<String> parentFilter = new JComboBox<>();
    final JComboBox<String> subFilter_1 = new JComboBox<>();
    final JComboBox<String> subFilter_2 = new JComboBox<>();
    final JComboBox<String> subFilter_3 = new JComboBox<>();
    private final JLabel errorField = new JLabel();
    final JButton apply;
    final JButton close;

    public GetFilterView(GetFilterViewModel getFilterViewModel) {
        this.getFilterViewModel = getFilterViewModel;
        this.getFilterViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("GetAllFilters");
        JFrame application = new JFrame("Place Finder");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        JPanel buttons = new JPanel();

        apply = new JButton(getFilterViewModel.APPLY_BUTTON_LABEL);
        close = new JButton(getFilterViewModel.CLOSE_BUTTON_LABEL);
        buttons.add(apply);
        buttons.add(close);



        apply.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        //String parentFilter = parentFilter.getSelected;
                        if (evt.getSource().equals(apply)) {

                        }
                    }
                }
        );


        this.add(parentFilter);
        this.add(subFilter_1);
        this.add(subFilter_2);
        this.add(subFilter_3);
        this.add(apply);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetFilterState getFilterState = (GetFilterState) evt.getNewValue();
//        for (Place place : listingState.getListing().getPoints()) {
//            model.addElement(place.getName());
    }
}




