package com.zaa.naumentest.client.event;
import com.google.gwt.event.shared.GwtEvent;

// Событие 404 ошибки

public class Err404Event extends GwtEvent<Err404EventHandler> {
    private String error;
    private static Type<Err404EventHandler> TYPE;

    public static Type<Err404EventHandler> getType() {
        return TYPE != null ? TYPE : (TYPE = new Type<Err404EventHandler>());
    }

    public Err404Event(String error) {
        this.error = error;
    }
    public String getError() {
        return error;
    }

    @Override
    public final Type<Err404EventHandler> getAssociatedType() {
        return getType();
    }

    @Override
    protected void dispatch(Err404EventHandler handler) {
        handler.onErr404(this);
    }

}