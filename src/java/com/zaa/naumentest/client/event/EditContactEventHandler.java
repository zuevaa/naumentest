package com.zaa.naumentest.client.event;
import com.google.gwt.event.shared.EventHandler;

// Интерфейс обработчика перехода на редактирование

public interface EditContactEventHandler extends EventHandler {
    void onEditContact(EditContactEvent event);
}
