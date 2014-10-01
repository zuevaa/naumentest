package com.zaa.naumentest.client.place;

import com.zaa.naumentest.client.presenter.Err500Presenter;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.place.PresenterPlace;

// Place для 500 ошибки

public class Err500Place extends PresenterPlace<Err500Presenter> {

    private final Err500Presenter editPresenter;
    public Err500Place(Err500Presenter editPresenter) {
        this.editPresenter = editPresenter;
    }
    @Override
    public Err500Presenter getPresenter() {
        return editPresenter;
    }

    @Override
    protected void preparePresenter(PlaceRequest request, Err500Presenter presenter) {
    }

    @Override
    protected PlaceRequest prepareRequest(PlaceRequest request, Err500Presenter presenter) {
        return request;
    }

    @Override
    public String getName() {
        return "500";
    }

    
}
