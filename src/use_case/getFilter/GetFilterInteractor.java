package use_case.getFilter;

import java.util.ArrayList;

public class GetFilterInteractor implements GetFilterInputBoundary{
    final GetFilterDataAccessInterface userDataAccessObject;

    final GetFilterOutputBpundary userPresenter;

    public GetFilterInteractor(GetFilterDataAccessInterface userDataAccessObject,
                               GetFilterOutputBpundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }


    @Override
    public void execute() {
        ArrayList<String> filters = userDataAccessObject.getAllFilters();
        StringBuilder names = new StringBuilder("Here's all the filters: \n");
        for (String name: filters) {names.append(name+"\n");}
        userPresenter.prepareView(String.valueOf(names));
    }
}
