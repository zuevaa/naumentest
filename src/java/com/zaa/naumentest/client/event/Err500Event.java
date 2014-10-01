package com.zaa.naumentest.client.event;
import com.google.gwt.event.shared.GwtEvent;

// Событие 500 ошибки

public class Err500Event extends GwtEvent<Err500EventHandler> {
    private String error;
    private static Type<Err500EventHandler> TYPE;

    public static Type<Err500EventHandler> getType() {
        return TYPE != null ? TYPE : (TYPE = new Type<Err500EventHandler>());
    }

    public Err500Event(String error) {
        this.error = error;
    }
    public String getError() {
        return error;
    }
    @Override
    public final Type<Err500EventHandler> getAssociatedType() {
        return getType();
    }

    @Override
    protected void dispatch(Err500EventHandler handler) {
        handler.onErr500(this);
    }

}