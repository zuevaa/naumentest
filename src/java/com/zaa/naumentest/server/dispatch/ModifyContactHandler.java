package com.zaa.naumentest.server.dispatch;
import com.zaa.naumentest.server.model.ContactDAO;
import com.zaa.naumentest.server.model.ContactDAOSingleton;
import com.zaa.naumentest.shared.model.Contact;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import com.zaa.naumentest.shared.dispatch.ModifyContactAction;
import com.zaa.naumentest.shared.dispatch.ModifyContactResult;

// Обработчик изменения контакта

public class ModifyContactHandler implements ActionHandler<ModifyContactAction, ModifyContactResult> {

    @Override
    public Class<ModifyContactAction> getActionType() {
        return ModifyContactAction.class;
    }

    @Override
    public ModifyContactResult execute( ModifyContactAction action, ExecutionContext context ) throws ActionException {
        try {
            Contact contact = action.getContact();
            ContactDAO contactDAO = ContactDAOSingleton.getInstance();
            contactDAO.check(contact);
            contactDAO.modify(contact);
            return new ModifyContactResult(contact.getKey());
        }
        catch (Exception e) {
            return new ModifyContactResult(e);
        }
    }

    @Override
    public void rollback( ModifyContactAction action, ModifyContactResult result, ExecutionContext context ) throws ActionException {
    }
}