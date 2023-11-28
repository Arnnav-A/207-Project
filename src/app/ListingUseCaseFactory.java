package app;

import data_access.FilePlaceInfoDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.listing_results.ListingResultsViewModel;
import interface_adapter.place_info.PlaceInfoController;
import interface_adapter.place_info.PlaceInfoPresenter;
import interface_adapter.place_info.PlaceInfoViewModel;
import use_case.place_info.PlaceInfoInputBoundary;
import use_case.place_info.PlaceInfoInteractor;
import use_case.place_info.PlaceInfoOutputBoundary;
import view.ListingView;

import javax.swing.*;
import java.io.IOException;

public class ListingUseCaseFactory {

    private ListingUseCaseFactory() {}

    public static ListingView create(ListingResultsViewModel listingResultsViewModel,
                                     ViewManagerModel viewManagerModel,
                                     FilePlaceInfoDataAccessObject placeInfoDataAccessObject,
                                     PlaceInfoViewModel placeInfoViewModel) {
        try {
            PlaceInfoController placeInfoController = createPlaceInfoUseCase(viewManagerModel,
                    placeInfoDataAccessObject, placeInfoViewModel);
            return new ListingView(listingResultsViewModel, viewManagerModel, placeInfoController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static PlaceInfoController createPlaceInfoUseCase(ViewManagerModel viewManagerModel,
                                                              FilePlaceInfoDataAccessObject placeInfoDataAccessObject,
                                                              PlaceInfoViewModel placeInfoViewModel) throws IOException {
        PlaceInfoOutputBoundary placeInfoOutputBoundary = new PlaceInfoPresenter(placeInfoViewModel, viewManagerModel);

        PlaceInfoInputBoundary placeInfoInteractor = new PlaceInfoInteractor(placeInfoDataAccessObject, placeInfoOutputBoundary);

        return new PlaceInfoController(placeInfoInteractor);

    }
}
