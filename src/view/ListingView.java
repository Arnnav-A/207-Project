package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.listing_results.ListingResultsState;
import interface_adapter.listing_results.ListingResultsViewModel;
import interface_adapter.place_info.PlaceInfoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ListingView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "listing";
    private final ListingResultsViewModel listingResultsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PlaceInfoController placeInfoController;

    JList<String> places_names;
    DefaultListModel<String> model;

    JLabel specific;
    JLabel search;
    JButton back;

    public ListingView(ListingResultsViewModel listingResultsViewModel, ViewManagerModel viewManagerModel, PlaceInfoController placeInfoController) {
        this.listingResultsViewModel = listingResultsViewModel;
        this.placeInfoController = placeInfoController;
        this.viewManagerModel = viewManagerModel;
        this.listingResultsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(listingResultsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);

        search = new JLabel();
        search.setAlignmentX(Component.CENTER_ALIGNMENT);
        search.setAlignmentY(Component.CENTER_ALIGNMENT);
        search.setForeground(Color.blue);

        model = new DefaultListModel<>();
        places_names = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(places_names);

        specific = new JLabel();

        JPanel buttons = new JPanel();
        back = new JButton(listingResultsViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            model.clear();
                            viewManagerModel.setActiveView("search");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        places_names.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        if (evt.getClickCount() == 2) {
                            String name = places_names.getSelectedValue();
                            ListingResultsState state = listingResultsViewModel.getState();
                            state.setPlaceName(name);
                            listingResultsViewModel.setState(state);
                            placeInfoController.execute(state.getPlaceName());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(search);
        this.add(scrollPane);
        this.add(buttons);
        this.add(specific);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ListingResultsState listingState = (ListingResultsState) evt.getNewValue();
        search.setText("City: " + listingState.getCity() + " / Filter: " + listingState.getFilter());
        for (String placeName : listingState.getPlacesNames()) {
            model.addElement(placeName);
        }
    }
}

