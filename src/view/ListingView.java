package view;

import entity.Place;
import interface_adapter.listing_results.ListingResultsState;
import interface_adapter.listing_results.ListingResultsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ListingView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "listing";
    private final ListingResultsViewModel listingResultsViewModel;

    JList<String> places;
    DefaultListModel<String> model;

    JLabel city;
    JLabel filter;


    public ListingView(ListingResultsViewModel listingResultsViewModel) {
        this.listingResultsViewModel = listingResultsViewModel;
        this.listingResultsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Results");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        model = new DefaultListModel<>();
        places = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(places);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ListingResultsState listingState = (ListingResultsState) evt.getNewValue();
        for (Place place : listingState.getListing().getPoints()) {
            model.addElement(place.getName());
        }

    }
}

