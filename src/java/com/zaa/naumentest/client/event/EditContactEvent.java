package com.zaa.naumentest.client.event;
import com.google.gwt.event.shared.GwtEvent;
import com.zaa.naumentest.shared.model.Contact;

// Событие перехода на редактирование

public class EditContactEvent extends GwtEvent<EditContactEventHandler> {
    private Contact contact;
    private static Type<EditContactEventHandler> TYPE;

    public static Type<EditContactEventHandler> getType() {
        return TYPE != null ? TYPE : (TYPE = new Type<EditContactEventHandler>());
    }

    public EditContactEvent(Contact contact) {
        this.contact = contact;
    }
    public Contact getContact() {
        return contact;
    }

    @Override
    public final Type<EditContactEventHandler> getAssociatedType() {
        return getType();
    }

    @Override
    protected void dispatch(EditContactEventHandler handler) {
        handler.onEditContact(this);
    }

}