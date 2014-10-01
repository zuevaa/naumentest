package com.zaa.naumentest.client.event;
import com.google.gwt.event.shared.EventHandler;

// Интерфейс обработчика удаления

public interface DelContactEventHandler extends EventHandler {
    void onDelContact(DelContactEvent event);
}
