package com.zaa.naumentest.server.model;

import com.zaa.naumentest.shared.model.NotFoundException;
import com.zaa.naumentest.shared.model.Contact;
import java.util.ArrayList;

// Интерфейс DAO для контакта

public interface ContactDAO {
// изменение
    public void modify(Contact contact) throws NotFoundException;
// удаление    
    public void delete(Contact contact);
// получение списка
    public ArrayList<Contact> getList(String filter);
// получение контакта
    public Contact get(long key) throws NotFoundException;
// проверка
    public void check(Contact contact);
}
