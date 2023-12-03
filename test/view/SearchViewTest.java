package view;

import app.Main;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.Assert.assertNotNull;

public class SearchViewTest {

    String message;

    public JTextField getCityField() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SearchView sv = (SearchView) jp2.getComponent(0);
        JPanel city = (JPanel) sv.getComponent(1);
        return (JTextField) city.getComponent(1); // this should be the city field
    }

    public JTextField getFilterField() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SearchView sv = (SearchView) jp2.getComponent(0);
        JPanel city = (JPanel) sv.getComponent(2);
        return (JTextField) city.getComponent(1); // this should be the filter field
    }

    public JButton getSearchButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SearchView sv = (SearchView) jp2.getComponent(0);
        JPanel buttons = (JPanel) sv.getComponent(4);
        return (JButton) buttons.getComponent(0); // this should be the search button
    }

    public JButton getFilterButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SearchView sv = (SearchView) jp2.getComponent(0);
        JPanel buttons = (JPanel) sv.getComponent(2);
        return (JButton) buttons.getComponent(2); // this should be the get filter button
    }

    public JButton getSavedPlacesButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SearchView sv = (SearchView) jp2.getComponent(0);
        JPanel buttons = (JPanel) sv.getComponent(5);
        return (JButton) buttons.getComponent(0); // this should be the get saved places button
    }

    public JButton getHistoryButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SearchView sv = (SearchView) jp2.getComponent(0);
        JPanel buttons = (JPanel) sv.getComponent(5);
        return (JButton) buttons.getComponent(1); // this should be the get history button
    }

    public JButton getClearHistoryButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SearchView sv = (SearchView) jp2.getComponent(0);
        JPanel buttons = (JPanel) sv.getComponent(5);
        return (JButton) buttons.getComponent(2); // this should be the clear history button
    }


    @Test
    public void testMainCompiles() {
        Main.main(null);
    }

    @Test
    public void testSearchButtonPresent() {
        Main.main(null);
        JButton searchButton = getSearchButton();
        assert searchButton.getText().equals("Search");
    }

    @Test
    public void testSpecificSearch() {
        Main.main(null);
        JButton searchButton = getSearchButton();
        JTextField city = getCityField();
        JTextField filter = getFilterField();
        city.setText("Toronto");
        filter.setText("museum");
        searchButton.doClick();
    }

    @Test
    public void testGetFilterButtonPresent() {
        Main.main(null);
        JButton searchButton = getFilterButton();
        assert searchButton.getText().equals("Get Filter");
    }

    @Test
    public void testGetFilterClick() {
        Main.main(null);
        JButton searchButton = getFilterButton();
        searchButton.doClick();
    }

    @Test
    public void testGetSavedPlacesButtonPresent() {
        Main.main(null);
        JButton savedPlaces = getSavedPlacesButton();
        assert savedPlaces.getText().equals("Saved Places");
    }

    @Test
    public void testClickGetSavedPlaces() {
        Main.main(null);
        JButton savedPlaces = getSavedPlacesButton();
        createCloseTimer().start();
        savedPlaces.doClick();
        assert message.equals("No saved places found.");
    }

    @Test
    public void testGetHistoryButtonPresent() {
        Main.main(null);
        JButton getHistory = getHistoryButton();
        assert getHistory.getText().equals("Get history");
    }

    @Test
    public void testClickGetHistory() {
        Main.main(null);
        JButton getHistory = getHistoryButton();
        createCloseTimer().start();
        getHistory.doClick();
        assert message.equals("No history found.");
    }

    @Test
    public void testClearHistoryButtonPresent() {
        Main.main(null);
        JButton clearHistoryButton = getClearHistoryButton();
        assert clearHistoryButton.getText().equals("Clear history");
    }

    @Test
    public void testClickClearHistory() {
        Main.main(null);
        JButton clearHistoryButton = getClearHistoryButton();
        createCloseTimer().start();
        clearHistoryButton.doClick();
        assert message.equals("User history is deleted.");
    }

    private Timer createCloseTimer() {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog)window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
                            System.out.println("message = " + s);

                            // store the information we got from the JDialog
                            message = s;
//                            ClearUsersTest.popUpDiscovered = true;

                            System.out.println("disposing of..." + window.getClass());
                            window.dispose();
                        }
                    }
                }
            }

        };

        Timer t = new Timer(1000, close);
        t.setRepeats(false);
        return t;
    }
}
