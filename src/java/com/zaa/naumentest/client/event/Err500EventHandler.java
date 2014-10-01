package com.zaa.naumentest.client.event;
import com.google.gwt.event.shared.EventHandler;

// Интерфейс обработчика 500 ошибки

public interface Err500EventHandler extends EventHandler {
    void onErr500(Err500Event event);
}
