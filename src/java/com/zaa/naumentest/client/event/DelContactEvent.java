package com.zaa.naumentest.client.event;
import com.google.gwt.event.shared.GwtEvent;
import com.zaa.naumentest.shared.model.Contact;

// Событие удаления

public class DelContactEvent extends GwtEvent<DelContactEventHandler> {
    private Contact contact;
    private static Type<DelContactEventHandler> TYPE;

    public static Type<DelContactEventHandler> getType() {
        return TYPE != null ? TYPE : (TYPE = new Type<DelContactEventHandler>());
    }

    public DelContactEvent(Contact contact) {
        this.contact = contact;
    }
    public Contact getContact() {
        return contact;
    }

    @Override
    public final Type<DelContactEventHandler> getAssociatedType() {
        return getType();
    }

    @Override
    protected void dispatch(DelContactEventHandler handler) {
        handler.onDelContact(this);
    }

}