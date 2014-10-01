package com.zaa.naumentest.client.place;

import com.zaa.naumentest.client.presenter.EditContactPresenter;
import com.zaa.naumentest.shared.model.Contact;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.place.PresenterPlace;

// Place для редактирования

public class EditContactPlace extends PresenterPlace<EditContactPresenter> {

    private final EditContactPresenter editPresenter;
    public EditContactPlace(EditContactPresenter editPresenter) {
        this.editPresenter = editPresenter;
    }
    @Override
    public EditContactPresenter getPresenter() {
        return editPresenter;
    }

    @Override
    protected void preparePresenter(PlaceRequest request, EditContactPresenter presenter) {
        String key = request.getParameter("id", null);
// Если пусто значит создание нового
        if ((key == null)||(key.equals(""))) {
            presenter.setContact(new Contact());
        }
        else {
            presenter.searchContact(Long.parseLong(key));
        }
    }

    @Override
    protected PlaceRequest prepareRequest(PlaceRequest request, EditContactPresenter presenter) {
// Если пусто значит создание нового
        if (presenter.getContact().getKey() != 0) {
            request = request.with("id", Long.toString(presenter.getContact().getKey()));
        }
        return request;
    }

    @Override
    public String getName() {
        return "edit";
    }

    
}
