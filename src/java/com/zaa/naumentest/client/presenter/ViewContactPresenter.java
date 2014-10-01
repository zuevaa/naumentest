package com.zaa.naumentest.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;
import com.zaa.naumentest.client.event.DelContactEvent;
import com.zaa.naumentest.client.event.EditContactEvent;
import com.zaa.naumentest.client.event.Err404Event;
import com.zaa.naumentest.client.event.Err500Event;
import com.zaa.naumentest.client.event.OpenContactEvent;
import com.zaa.naumentest.client.event.OpenContactEventHandler;
import com.zaa.naumentest.client.event.OpenContactTabEvent;
import com.zaa.naumentest.shared.model.NotFoundException;
import com.zaa.naumentest.shared.dispatch.GetContactAction;
import com.zaa.naumentest.shared.dispatch.GetContactResult;
import com.zaa.naumentest.shared.model.Contact;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

// Presenter просмотра

public class ViewContactPresenter extends WidgetPresenter<ViewContactPresenter.Display> {
// Интерфейс View
    public interface Display extends WidgetDisplay {
        HasClickHandlers getEditClickHandlers();
        HasClickHandlers getDelClickHandlers();
        HasClickHandlers getCancelClickHandlers();
        HasText getName();
        HasText getPhone();
    }

    private Contact contact;
    private final DispatchAsync dispatch;
    public ViewContactPresenter(Display display, EventBus eventBus, DispatchAsync dispatch) {
        super(display, eventBus);
        this.dispatch = dispatch;
    }

    @Override
    protected void onBind() {
// Переход к редактированию
        registerHandler(display.getEditClickHandlers().addClickHandler(
            new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    eventBus.fireEvent(new EditContactEvent(contact));
                }
        }));
// Удаление
        registerHandler(display.getDelClickHandlers().addClickHandler(
            new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    eventBus.fireEvent(new DelContactEvent(contact));
                }
        }));
// Возврат к таблице
        registerHandler(display.getCancelClickHandlers().addClickHandler(
            new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    eventBus.fireEvent(new OpenContactTabEvent(""));
                }
        }));
// Событие перехода на просмотр
        registerHandler(eventBus.addHandler(OpenContactEvent.getType(),
            new OpenContactEventHandler() {
                @Override
                public void onOpenContact(OpenContactEvent event) {
                    contact = event.getContact();
                    revealDisplay();
                }
        }));        
    }

    @Override
    protected void onUnbind() {
    }
// Поиск контакта по ключу
    public void searchContact(long key) {
        dispatch.execute(new GetContactAction(key), new AsyncCallback<GetContactResult>() {
            @Override
            public void onFailure(Throwable caught) {
                eventBus.fireEvent(new Err500Event(caught.getMessage()));
            }
            @Override
            public void onSuccess(GetContactResult result) {
               if (result.getException() != null) {
                   if (result.getException() instanceof NotFoundException) {
                        eventBus.fireEvent(new Err404Event(result.getException().getMessage()));
                   }
                   else {
                        eventBus.fireEvent(new Err500Event(result.getException().getMessage()));
                   }
               }
               else {
                   contact = result.getContact();
                   revealDisplay();
               }
            }
        });        
    }
// Отображение
    @Override
    protected void onRevealDisplay() {
        if (contact != null) {
            display.getName().setText(contact.getName());
            display.getPhone().setText(contact.getPhone());
            RootPanel.get().clear();
            RootPanel.get().add(display.asWidget());
        }
    }
    public void setContact(Contact contact){
        this.contact = contact;
    }
    public Contact getContact() {
        return contact;
    }    
}