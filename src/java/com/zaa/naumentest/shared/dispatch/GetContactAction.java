package com.zaa.naumentest.shared.dispatch;

import net.customware.gwt.dispatch.shared.Action;
 
// Action получение контакта

public class GetContactAction implements Action<GetContactResult> {
    private long key;
    GetContactAction() {        
    }
    public GetContactAction(long key) {
        this.key = key;
    }
    public long getKey() {
        return this.key;
    }
}