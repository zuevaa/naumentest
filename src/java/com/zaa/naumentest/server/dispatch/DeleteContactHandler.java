package com.zaa.naumentest.server.dispatch;

import com.zaa.naumentest.server.model.ContactDAO;
import com.zaa.naumentest.server.model.ContactDAOSingleton;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import com.zaa.naumentest.shared.dispatch.DeleteContactAction;
import com.zaa.naumentest.shared.dispatch.DeleteContactResult;

// Обработчик удаления

public class DeleteContactHandler implements ActionHandler<DeleteContactAction, DeleteContactResult> {

    @Override
    public Class<DeleteContactAction> getActionType() {
        return DeleteContactAction.class;
    }

    @Override
    public DeleteContactResult execute( DeleteContactAction action, ExecutionContext context ) throws ActionException {
        try {
            ContactDAO contactDAO = ContactDAOSingleton.getInstance();
            contactDAO.delete(action.getContact());
            return new DeleteContactResult();
        }
        catch (Exception e) {
            return new DeleteContactResult(e);
        }
        
    }

    @Override
    public void rollback( DeleteContactAction action, DeleteContactResult result, ExecutionContext context ) throws ActionException {
    }
}