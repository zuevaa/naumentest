package com.zaa.naumentest.client.event;
import com.google.gwt.event.shared.EventHandler;

// Интерфейс обработчика 404 ошибки

public interface Err404EventHandler extends EventHandler {
    void onErr404(Err404Event event);
}
