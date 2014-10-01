package com.zaa.naumentest.server.dispatch;

import com.zaa.naumentest.server.model.ContactDAO;
import com.zaa.naumentest.server.model.ContactDAOSingleton;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import com.zaa.naumentest.shared.dispatch.GetContactAction;
import com.zaa.naumentest.shared.dispatch.GetContactResult;

// Обработчик получение контакта 

public class GetContactHandler implements ActionHandler<GetContactAction, GetContactResult> {

    @Override
    public Class<GetContactAction> getActionType() {
        return GetContactAction.class;
    }

    @Override
    public GetContactResult execute( GetContactAction action, ExecutionContext context ) throws ActionException {
        try {
            ContactDAO contactDAO = ContactDAOSingleton.getInstance();
            return new GetContactResult(contactDAO.get(action.getKey()));
        }
        catch (Exception e) {
            return new GetContactResult(e);
        }
    }

    @Override
    public void rollback( GetContactAction action, GetContactResult result, ExecutionContext context ) throws ActionException {
    }
}