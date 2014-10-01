package com.zaa.naumentest.shared.dispatch;
import net.customware.gwt.dispatch.shared.Result;

// Результат удаления

public class DeleteContactResult implements Result {
    private Exception exception;
    public DeleteContactResult() {
    }
    public DeleteContactResult(Exception exception) {
        this.exception = exception;
    }
    public Exception getException(){
        return this.exception;
    }    
}
