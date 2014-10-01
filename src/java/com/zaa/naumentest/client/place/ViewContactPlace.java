package com.zaa.naumentest.client.place;

import com.zaa.naumentest.client.event.Err404Event;
import com.zaa.naumentest.client.presenter.ViewContactPresenter;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.place.PresenterPlace;

// Place для просмотра

public class ViewContactPlace extends PresenterPlace<ViewContactPresenter> {

    private final ViewContactPresenter editPresenter;
    private final EventBus eventBus;
    public ViewContactPlace(ViewContactPresenter editPresenter, EventBus eventBus) {
        this.editPresenter = editPresenter;
        this.eventBus = eventBus;
    }
    @Override
    public ViewContactPresenter getPresenter() {
        return editPresenter;
    }

    @Override
    protected void preparePresenter(PlaceRequest request, ViewContactPresenter presenter) {
        String key = request.getParameter("id", null);
// id обязательный        
        if ((key == null)||(key.equals(""))) {
            eventBus.fireEvent(new Err404Event("Не задан id"));
        }
        else {
            presenter.searchContact(Long.parseLong(key));
        }        
    }

    @Override
    protected PlaceRequest prepareRequest(PlaceRequest request, ViewContactPresenter presenter) {
        request = request.with("id", Long.toString(presenter.getContact().getKey()));
        return request;
    }

    @Override
    public String getName() {
        return "view";
    }
    
}
