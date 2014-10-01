package com.zaa.naumentest.server.dispatch;

import com.zaa.naumentest.server.model.ContactDAO;
import com.zaa.naumentest.server.model.ContactDAOSingleton;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import com.zaa.naumentest.shared.dispatch.GetContactListAction;
import com.zaa.naumentest.shared.dispatch.GetContactListResult;

// Обработчик получение списка контактов

public class GetContactListHandler implements ActionHandler<GetContactListAction, GetContactListResult> {

    @Override
    public Class<GetContactListAction> getActionType() {
        return GetContactListAction.class;
    }

    @Override
    public GetContactListResult execute( GetContactListAction action, ExecutionContext context ) throws ActionException {
        try {
            ContactDAO contactDAO = ContactDAOSingleton.getInstance();
            return new GetContactListResult(contactDAO.getList(action.getFilter()));
        }
        catch (Exception e) {
            return new GetContactListResult(e);
        }
    }

    @Override
    public void rollback( GetContactListAction action, GetContactListResult result, ExecutionContext context ) throws ActionException {
    }
}