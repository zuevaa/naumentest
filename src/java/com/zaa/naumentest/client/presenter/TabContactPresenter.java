package com.zaa.naumentest.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.zaa.naumentest.client.event.DelContactEvent;
import com.zaa.naumentest.client.event.DelContactEventHandler;
import com.zaa.naumentest.client.event.EditContactEvent;
import com.zaa.naumentest.client.event.Err500Event;
import com.zaa.naumentest.client.event.OpenContactEvent;
import com.zaa.naumentest.client.event.OpenContactTabEvent;
import com.zaa.naumentest.client.event.OpenContactTabEventHandler;
import com.zaa.naumentest.shared.dispatch.DeleteContactAction;
import com.zaa.naumentest.shared.dispatch.DeleteContactResult;
import com.zaa.naumentest.shared.dispatch.GetContactListAction;
import com.zaa.naumentest.shared.dispatch.GetContactListResult;
import com.zaa.naumentest.shared.model.Contact;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

// Presenter таблицы

public class TabContactPresenter extends WidgetPresenter<TabContactPresenter.Display> {
// Интерфейс View
    public interface Display extends WidgetDisplay {
        Column getEditColumn();
        Column getDelColumn();
        Column getOpenColumn();
        HasClickHandlers getNewClickHandlers();
        HasClickHandlers getSearchClickHandlers();
        HasText getFilter();
        CellTable getGrid();
    }
    private final DispatchAsync dispatch;
    public TabContactPresenter(Display display, EventBus eventBus, DispatchAsync dispatch) {
        super(display, eventBus);
        this.dispatch = dispatch;
    }

    @Override
    protected void onBind() {
// Переход к редактированию
        display.getEditColumn().setFieldUpdater(new FieldUpdater<Contact, String>() {
            @Override
            public void update(int index, Contact contact, String value) {
                eventBus.fireEvent(new EditContactEvent(contact));
            }
        });
// Удаление
        display.getDelColumn().setFieldUpdater(new FieldUpdater<Contact, String>() {
            @Override
            public void update(int index, Contact contact, String value) {
                eventBus.fireEvent(new DelContactEvent(contact));
            }
        });
// Переход к просмотру
        display.getOpenColumn().setFieldUpdater(new FieldUpdater<Contact, String>() {
            @Override
            public void update(int index, Contact contact, String value) {
                eventBus.fireEvent(new OpenContactEvent(contact));
            }
        });
// Добавление нового
        registerHandler(display.getNewClickHandlers().addClickHandler(
            new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    eventBus.fireEvent(new EditContactEvent(new Contact()));
                }
        }));
// Поиск
        registerHandler(display.getSearchClickHandlers().addClickHandler(
            new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    eventBus.fireEvent(new OpenContactTabEvent(display.getFilter().getText()));
                }
        }));
// Событие удаления
        registerHandler(eventBus.addHandler(DelContactEvent.getType(),
            new DelContactEventHandler() {
                @Override
                public void onDelContact(DelContactEvent event) {
                    dispatch.execute(new DeleteContactAction(event.getContact()), new AsyncCallback<DeleteContactResult>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            eventBus.fireEvent(new Err500Event(caught.getMessage()));
                        }
                        @Override
                        public void onSuccess(DeleteContactResult result) {
                           if (result.getException() != null) {
                               eventBus.fireEvent(new Err500Event(result.getException().getMessage()));
                           }
                           else {
                               // Возврат к таблице
                               eventBus.fireEvent(new OpenContactTabEvent(display.getFilter().getText()));
                           }
                        }
                    });
                }
        }));
// Событие перехода к таблице
        registerHandler(eventBus.addHandler(OpenContactTabEvent.getType(),
            new OpenContactTabEventHandler() {
                @Override
                public void onOpenContactTab(OpenContactTabEvent event) {
                    FillGrid(event.getFilter());
                }
        }));
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
// Заполнение с сервера
    public void FillGrid(String filter) {
        display.getFilter().setText(filter);
        dispatch.execute(new GetContactListAction(filter), new AsyncCallback<GetContactListResult>() {
            @Override
            public void onFailure(Throwable caught) {
                eventBus.fireEvent(new Err500Event(caught.getMessage()));
            }
            @Override
            public void onSuccess(GetContactListResult result) {
               if (result.getException() != null) {
                   eventBus.fireEvent(new Err500Event(result.getException().getMessage()));
               }
               else {
                   CellTable contactGrid = display.getGrid();
                   contactGrid.setRowData(result.getContractList());
                   revealDisplay();
               }                
            }
        });        
    }
            
}
