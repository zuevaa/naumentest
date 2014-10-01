package com.zaa.naumentest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.zaa.naumentest.client.place.ContactPlaceManager;
import com.zaa.naumentest.client.place.EditContactPlace;
import com.zaa.naumentest.client.place.Err404Place;
import com.zaa.naumentest.client.place.Err500Place;
import com.zaa.naumentest.client.place.TabContactPlace;
import com.zaa.naumentest.client.place.ViewContactPlace;
import com.zaa.naumentest.client.presenter.EditContactPresenter;
import com.zaa.naumentest.client.presenter.Err404Presenter;
import com.zaa.naumentest.client.presenter.Err500Presenter;
import com.zaa.naumentest.client.presenter.TabContactPresenter;
import com.zaa.naumentest.client.presenter.ViewContactPresenter;
import com.zaa.naumentest.client.view.EditContactView;
import com.zaa.naumentest.client.view.Err404View;
import com.zaa.naumentest.client.view.Err500View;
import com.zaa.naumentest.client.view.TabContactView;
import com.zaa.naumentest.client.view.ViewContactView;
import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;
import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.place.ParameterTokenFormatter;

// Точка входа

public class MainEntryPoint implements EntryPoint {
    public MainEntryPoint() {
    }
    
    @Override
    public void onModuleLoad() {
        DispatchAsync dispatch = new StandardDispatchAsync(new DefaultExceptionHandler());
        DefaultEventBus eventBus = new DefaultEventBus();
// Создаем Presenters
        EditContactPresenter editPresenter = new EditContactPresenter(new EditContactView(), eventBus, dispatch);
        editPresenter.bind();
        ViewContactPresenter viewPresenter = new ViewContactPresenter(new ViewContactView(), eventBus, dispatch);
        viewPresenter.bind();
        TabContactPresenter tabPresenter = new TabContactPresenter(new TabContactView(), eventBus, dispatch);
        tabPresenter.bind();
        Err404Presenter err404Presenter = new Err404Presenter(new Err404View(), eventBus);
        err404Presenter.bind();
        Err500Presenter err500Presenter = new Err500Presenter(new Err500View(), eventBus);
        err500Presenter.bind();
// Создаем Places
        ContactPlaceManager placeManager = new ContactPlaceManager(eventBus, new ParameterTokenFormatter ());
        placeManager.registerPlace(new EditContactPlace(editPresenter));
        placeManager.registerPlace(new ViewContactPlace(viewPresenter, eventBus));
        placeManager.registerPlace(new TabContactPlace(tabPresenter));
        placeManager.registerPlace(new Err404Place(err404Presenter));
        placeManager.registerPlace(new Err500Place(err500Presenter));
// Если нет текущего стартуем tab
        if (History.getToken().equals("")) {
            History.newItem("tab");
        }
        placeManager.fireCurrentPlace();
    }
}
