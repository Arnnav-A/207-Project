package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.place_info.PlaceInfoState;
import interface_adapter.place_info.PlaceInfoViewModel;
import interface_adapter.save_places.SavePlacesController;
import okhttp3.Address;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PlaceInfoView extends JPanel implements  ActionListener, PropertyChangeListener {

    public final String viewname = "place_info";
    private final PlaceInfoViewModel placeInfoViewModel;
    private final ViewManagerModel viewManagerModel;

    JLabel placeName;
    JLabel address;
    JLabel coordinates;
    JLabel city;
    String hyperlink;
    JLabel linkName;

    JButton back;
    JButton save;

    public PlaceInfoView(PlaceInfoViewModel placeInfoViewModel, ViewManagerModel viewManagerModel, SavePlacesController savePlacesController) {
        this.placeInfoViewModel = placeInfoViewModel;
        this.viewManagerModel = viewManagerModel;
        this.placeInfoViewModel.addPropertyChangeListener(this);

        placeName = new JLabel();
        address = new JLabel();
        coordinates = new JLabel();
        city = new JLabel();
        placeName.setAlignmentY(CENTER_ALIGNMENT);
        address.setAlignmentY(CENTER_ALIGNMENT);

        linkName = new JLabel();
        linkName.setText("Find on Google Maps");
        linkName.setForeground(Color.BLUE.darker());
        linkName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel buttons = new JPanel();
        back = new JButton(placeInfoViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        save = new JButton(placeInfoViewModel.SAVE_BUTTON_LABEL);
        buttons.add(save);

        linkName.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    try {
                        Desktop.getDesktop().browse(new URI(hyperlink));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            viewManagerModel.setActiveView("listing");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save)) {
                            savePlacesController.execute(placeInfoViewModel.getState().getPlaceName());
                            JOptionPane.showMessageDialog(save.getTopLevelAncestor(), placeInfoViewModel.SAVE_MESSAGE);
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(placeName);
        this.add(city);
        this.add(address);
        this.add(coordinates);
        this.add(linkName);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PlaceInfoState placeInfoState = (PlaceInfoState) evt.getNewValue();
        placeName.setText("Name: " + placeInfoState.getPlaceName() + ".");
        city.setText("City: " + placeInfoState.getCity() + ".");
        address.setText("Address: " + placeInfoState.getAddress() + ".");
        coordinates.setText("Coordinates: " + placeInfoState.getCoordinates() + ".");
        hyperlink = "https://www.google.ca/maps/search/"
                + (placeInfoState.getPlaceName() + "+" + placeInfoState.getAddress()).replaceAll(" ", "+").replaceAll(",", "+");
    }
}
