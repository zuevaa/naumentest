package com.zaa.naumentest.shared.dispatch;
import com.zaa.naumentest.shared.model.Contact;
import net.customware.gwt.dispatch.shared.Result;

// Результат получения контакта

public class GetContactResult implements Result {
    private Contact contact;
    private Exception exception;
    GetContactResult() {
    }
    public GetContactResult(Contact contact) {
        this.contact = contact;        
    }
    public GetContactResult(Exception exception) {
        this.exception = exception;
    }
    public Contact getContact() {
        return this.contact;
    }
    public Exception getException(){
        return this.exception;
    }
}
