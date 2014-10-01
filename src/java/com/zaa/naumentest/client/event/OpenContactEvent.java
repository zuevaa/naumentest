package com.zaa.naumentest.client.event;
import com.google.gwt.event.shared.GwtEvent;
import com.zaa.naumentest.shared.model.Contact;

// Событие перехода на просмотр 

public class OpenContactEvent extends GwtEvent<OpenContactEventHandler> {
    private Contact contact;
    private static Type<OpenContactEventHandler> TYPE;

    public static Type<OpenContactEventHandler> getType() {
        return TYPE != null ? TYPE : (TYPE = new Type<OpenContactEventHandler>());
    }

    public OpenContactEvent(Contact contact) {
        this.contact = contact;
    }
    public Contact getContact() {
        return contact;
    }

    @Override
    public final Type<OpenContactEventHandler> getAssociatedType() {
        return getType();
    }

    @Override
    protected void dispatch(OpenContactEventHandler handler) {
        handler.onOpenContact(this);
    }

}