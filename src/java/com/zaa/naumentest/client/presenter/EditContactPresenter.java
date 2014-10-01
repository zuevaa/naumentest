package com.zaa.naumentest.client.presenter;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;
import com.zaa.naumentest.shared.model.Contact;
import com.zaa.naumentest.client.event.OpenContactTabEvent;
import com.zaa.naumentest.client.event.EditContactEvent;
import com.zaa.naumentest.client.event.EditContactEventHandler;
import com.zaa.naumentest.client.event.Err404Event;
import com.zaa.naumentest.client.event.Err500Event;
import com.zaa.naumentest.shared.model.NotFoundException;
import com.zaa.naumentest.shared.dispatch.GetContactAction;
import com.zaa.naumentest.shared.dispatch.GetContactResult;
import com.zaa.naumentest.shared.dispatch.ModifyContactAction;
import com.zaa.naumentest.shared.dispatch.ModifyContactResult;
import net.customware.gwt.dispatch.client.DispatchAsync;

// Presenter редактирования

public class EditContactPresenter extends WidgetPresenter<EditContactPresenter.Display> {
// Интерфейс View
    public interface Display extends WidgetDisplay {
        HasClickHandlers getSaveClickHandlers();
        HasClickHandlers getCancelClickHandlers();
        HasText getName();
        HasText getPhone();
        HasText getError();
    }
    private Contact contact;
    private final DispatchAsync dispatch;
    public EditContactPresenter(Display display, EventBus eventBus, DispatchAsync dispatch) {
        super(display, eventBus);
        this.dispatch = dispatch;
    }

    @Override
    protected void onBind() {
// Сохранение
        registerHandler(display.getSaveClickHandlers().addClickHandler(
            new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    contact.setName(display.getName().getText());
                    contact.setPhone(display.getPhone().getText());
// Вызываем сохранение на сервере
                    dispatch.execute(new ModifyContactAction(contact), new AsyncCallback<ModifyContactResult>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            eventBus.fireEvent(new Err500Event(caught.getMessage()));
                        }
                        @Override
                        public void onSuccess(ModifyContactResult result) {
                           if (result.getException() != null) {
                               setError(result.getException().getMessage());
                           }
                           else {
                                eventBus.fireEvent(new OpenContactTabEvent(""));
                           }
                        }
                    });
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
        
// Событие перехода к редактированию
        registerHandler(eventBus.addHandler(EditContactEvent.getType(),
            new EditContactEventHandler() {
                @Override
                public void onEditContact(EditContactEvent event) {
                    contact = event.getContact();
                    revealDisplay();
                }

        }));        
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
// Установка ошибки
    public void setError(String error) {
        display.getError().setText(error);
    }
    
    @Override
    protected void onUnbind() {
    }

// Отображение
    @Override
    public void onRevealDisplay() {
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