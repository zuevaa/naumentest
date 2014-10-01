package com.zaa.naumentest.server.dispatch;

import net.customware.gwt.dispatch.client.standard.StandardDispatchService;
import net.customware.gwt.dispatch.server.DefaultActionHandlerRegistry;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.InstanceActionHandlerRegistry;
import net.customware.gwt.dispatch.server.SimpleDispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.DispatchException;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DispatchServlet extends RemoteServiceServlet implements StandardDispatchService {

    private final Dispatch dispatch;

    public DispatchServlet() {

        InstanceActionHandlerRegistry registry = new DefaultActionHandlerRegistry();
// Регистрируем обработчики
        registry.addHandler(new ModifyContactHandler());
        registry.addHandler(new DeleteContactHandler());
        registry.addHandler(new GetContactHandler());
        registry.addHandler(new GetContactListHandler());
        dispatch = new SimpleDispatch(registry);
    }

    @Override
    public Result execute(Action<?> action) throws DispatchException {
        return dispatch.execute(action);
    }

}
