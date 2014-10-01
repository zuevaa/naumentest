package com.zaa.naumentest.client.event;
import com.google.gwt.event.shared.EventHandler;

// Интерфейс обработчика перехода на просмотр

public interface OpenContactEventHandler extends EventHandler {
    void onOpenContact(OpenContactEvent event);
}
