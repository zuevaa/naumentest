package com.zaa.naumentest.shared.dispatch;
import com.zaa.naumentest.shared.model.Contact;
import net.customware.gwt.dispatch.shared.Result;
import java.util.ArrayList;

// Результат получения списка

public class GetContactListResult implements Result {
    private Exception exception;
    private ArrayList<Contact> contractList;
    GetContactListResult(){
    }
    public GetContactListResult(ArrayList<Contact> contractList) {
        this.contractList = contractList;
    }
    public GetContactListResult(Exception exception) {
        this.exception = exception;
    }
    public ArrayList<Contact> getContractList() {
        return this.contractList;
    }
    public Exception getException(){
        return this.exception;
    }
}
