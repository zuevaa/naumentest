package com.zaa.naumentest.client.place;

import com.zaa.naumentest.client.presenter.Err404Presenter;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.place.PresenterPlace;

// Place для 404 ошибки

public class Err404Place extends PresenterPlace<Err404Presenter> {

    private final Err404Presenter editPresenter;
    public Err404Place(Err404Presenter editPresenter) {
        this.editPresenter = editPresenter;
    }
    @Override
    public Err404Presenter getPresenter() {
        return editPresenter;
    }

    @Override
    protected void preparePresenter(PlaceRequest request, Err404Presenter presenter) {
    }

    @Override
    protected PlaceRequest prepareRequest(PlaceRequest request, Err404Presenter presenter) {
        return request;
    }

    @Override
    public String getName() {
        return "404";
    }

    
}
