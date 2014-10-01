package com.zaa.naumentest.shared.dispatch;

import net.customware.gwt.dispatch.shared.Action;
import com.zaa.naumentest.shared.model.Contact;

// Action удаления

public class DeleteContactAction implements Action<DeleteContactResult> {
    private Contact contact;
    DeleteContactAction() {
    }
    public DeleteContactAction(Contact contact) {
        this.contact = contact;
    }
    public Contact getContact() {
        return this.contact;
    }
}