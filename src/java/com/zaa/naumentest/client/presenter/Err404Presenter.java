package com.zaa.naumentest.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;
import com.zaa.naumentest.client.event.Err404Event;
import com.zaa.naumentest.client.event.Err404EventHandler;
import com.zaa.naumentest.client.event.OpenContactTabEvent;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

// Presenter 404 ошибки

public class Err404Presenter extends WidgetPresenter<Err404Presenter.Display> {
    
// Интерфейс View
    public interface Display extends WidgetDisplay {
        HasClickHandlers getCancelClickHandlers();
        HasText getError();
    }

    public Err404Presenter(Display display, EventBus eventBus) {
        super(display, eventBus);
    }

    @Override
    protected void onBind() {
// Событие возврата на главную
        registerHandler(display.getCancelClickHandlers().addClickHandler(
            new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    eventBus.fireEvent(new OpenContactTabEvent(""));
                }
            }));
// Событие 404 ошибки
        registerHandler(eventBus.addHandler(Err404Event.getType(),
            new Err404EventHandler() {
                @Override
                public void onErr404(Err404Event event) {
                    setError(event.getError());
                    revealDisplay();
                }
        }));
    }
    public void setError(String error) {
        display.getError().setText(error);
    }

    @Override
    protected void onUnbind() {
    }
// Отображение
    @Override
    public void onRevealDisplay() {
        RootPanel.get().clear();
        RootPanel.get().add(display.asWidget());        
    }
}