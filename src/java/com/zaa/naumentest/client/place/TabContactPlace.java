package com.zaa.naumentest.client.place;

import com.zaa.naumentest.client.presenter.TabContactPresenter;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.place.PresenterPlace;

// Place для таблицы

public class TabContactPlace extends PresenterPlace<TabContactPresenter> {

    private final TabContactPresenter editPresenter;
    public TabContactPlace(TabContactPresenter editPresenter) {
        this.editPresenter = editPresenter;
    }
    @Override
    public TabContactPresenter getPresenter() {
        return editPresenter;
    }

    @Override
    protected void preparePresenter(PlaceRequest request, TabContactPresenter presenter) {
        String filter = request.getParameter("filter", null);
        presenter.FillGrid(filter);
    }

    @Override
    protected PlaceRequest prepareRequest(PlaceRequest request, TabContactPresenter presenter) {
        String filter = presenter.getDisplay().getFilter().getText();
// Если фильтра нет то не пишем его в url
        if ((filter != null)&&(!filter.equals(""))) {
            request = request.with("filter", presenter.getDisplay().getFilter().getText());
        }
        return request;
    }

    @Override
    public String getName() {
        return "tab";
    }    
}
