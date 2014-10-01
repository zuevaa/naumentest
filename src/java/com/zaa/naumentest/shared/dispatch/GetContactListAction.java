package com.zaa.naumentest.shared.dispatch;

import net.customware.gwt.dispatch.shared.Action;

// Action получение списка

public class GetContactListAction implements Action<GetContactListResult> {
    private String filter;
    GetContactListAction() {
    }
    public GetContactListAction(String filter) {
        this.filter = filter;
    }
    public String getFilter() {
        return this.filter;
    }
}