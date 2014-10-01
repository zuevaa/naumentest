package com.zaa.naumentest.client.event;
import com.google.gwt.event.shared.GwtEvent;

// Событие перехода к таблице

public class OpenContactTabEvent extends GwtEvent<OpenContactTabEventHandler> {
    private String filter;
    private static Type<OpenContactTabEventHandler> TYPE;

    public static Type<OpenContactTabEventHandler> getType() {
        return TYPE != null ? TYPE : (TYPE = new Type<OpenContactTabEventHandler>());
    }

    public OpenContactTabEvent(String filter) {
        this.filter = filter;
    }
    public String getFilter() {
        return this.filter;
    }

    @Override
    public final Type<OpenContactTabEventHandler> getAssociatedType() {
        return getType();
    }

    @Override
    protected void dispatch(OpenContactTabEventHandler handler) {
        handler.onOpenContactTab(this);
    }

}