package com.zaa.naumentest.shared.dispatch;

import com.zaa.naumentest.shared.model.Contact;
import net.customware.gwt.dispatch.shared.Action;

// Action изменения

public class ModifyContactAction implements Action<ModifyContactResult> {
    private Contact contact;
    ModifyContactAction() {        
    }
    public ModifyContactAction(Contact contact) {
        this.contact = contact;
    }
    public Contact getContact() {
        return this.contact;
    }
}