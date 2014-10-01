package com.zaa.naumentest.server.model;

import com.zaa.naumentest.shared.model.Contact;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.zaa.naumentest.shared.model.NotFoundException;
import java.util.ArrayList;
import javax.validation.ValidationException;

// DAO для контакта для GAE DS

public class ContactDAODS implements ContactDAO {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    private Entity getEntyity(Long key) throws NotFoundException {
        Key contact_key = KeyFactory.createKey("Contact", key);
        Entity entity;
        try {
            entity = datastore.get(contact_key);
        } catch (EntityNotFoundException ex) {
            throw new NotFoundException("Контакт не найден");
        }
        return entity;
    }

    @Override
    public void modify(Contact contact) throws NotFoundException{
        Entity entity;
        if (contact.getKey() == 0) {
            entity = new Entity("Contact");
        }
        else {
            entity = getEntyity(contact.getKey());
        }
        entity.setProperty("name", contact.getName());
        entity.setProperty("phone", contact.getPhone());
        datastore.put(entity);
        contact.setKey(entity.getKey().getId());
    }
    
    @Override
    public void delete(Contact contact){
        datastore.delete(KeyFactory.createKey("Contact", contact.getKey()));
    }
    
    @Override
    public ArrayList<Contact> getList(String filter) {
        
        ArrayList<Contact> contractList = new ArrayList<>();
        Query query = new Query("Contact");
        PreparedQuery prepquery = datastore.prepare(query);
        for (Entity entity : prepquery.asIterable()) {
// GAE DS не умеет искать по маске, фильтруем ручками
            if ((filter != null)&&(!filter.equals(""))) {
                if (((String)entity.getProperty("name")).indexOf(filter) != -1) {
                    contractList.add(new Contact(entity.getKey().getId(), (String)entity.getProperty("name"), (String)entity.getProperty("phone")));
                }
            }
            else {
                contractList.add(new Contact(entity.getKey().getId(), (String)entity.getProperty("name"), (String)entity.getProperty("phone")));
            }
        }
        return contractList;
    }
    
    @Override
    public Contact get(long key) throws NotFoundException{
        Entity entity = getEntyity(key);
        return new Contact(entity.getKey().getId(), (String)entity.getProperty("name"), (String)entity.getProperty("phone"));
    }
    
    @Override
    public void check(Contact contact) {
        if (contact.getName() == null) {
            throw new ValidationException("Имя обязательно");
        }
        if (contact.getPhone()== null) {
            throw new ValidationException("Телефон обязателен");
        }
        Query query = new Query("Contact");
        Query.Filter filter =  new Query.FilterPredicate("name", Query.FilterOperator.EQUAL, contact.getName());
        query = query.setFilter(filter);
        PreparedQuery prepquery = datastore.prepare(query);
        for (Entity entity : prepquery.asIterable()) {
            if (entity.getKey().getId() != contact.getKey()) {
                throw new ValidationException("Данное имя уже есть");
            }
        }
    }
}
