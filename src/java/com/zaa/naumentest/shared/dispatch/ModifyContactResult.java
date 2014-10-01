package com.zaa.naumentest.shared.dispatch;
import net.customware.gwt.dispatch.shared.Result;

// Результат изменения

public class ModifyContactResult implements Result {
    private long key;
    private Exception exception;
    ModifyContactResult() {        
    }
    public ModifyContactResult(long key) {
        this.key = key;
    }
    public ModifyContactResult(Exception exception) {
        this.exception = exception;
    }
    public long getKey() {
        return this.key;
    }
    public Exception getException(){
        return this.exception;
    }
}
