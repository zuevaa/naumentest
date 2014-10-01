package com.zaa.naumentest.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.zaa.naumentest.client.presenter.TabContactPresenter;
import com.zaa.naumentest.shared.model.Contact;

// View таблицы

public class TabContactView extends Composite implements TabContactPresenter.Display {
    private final Panel panel;
    private final TextBox filter;
    private final Button searchButton;
    private final Button newButton;
    private final Column<Contact, String> openColumn, editColumn, delColumn;
    private final CellTable contactGrid;
    private final SimplePager pager;
    public TabContactView() {
        panel = new FlowPanel();
        initWidget(panel);
        filter = new TextBox();
        final Panel searchPanel = new FlowPanel();
        final Label label = new Label("Поиск");
        searchPanel.add(label);
        searchPanel.add(filter);
        searchButton = new Button("Поиск");
        searchPanel.add(searchButton);
        newButton = new Button("Создать");
        searchPanel.add(newButton);        
        panel.add(searchPanel);
// Создаем таблицу
        contactGrid = new CellTable<Contact>();
        contactGrid.setWidth("100%", true);
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(contactGrid);
        panel.add(contactGrid);
// Создаем колонки
        final TextCell name = new TextCell();
        final TextCell phone = new TextCell();
        final Column<Contact, String> nameColumn = new Column<Contact, String>(name) {
            @Override
            public String getValue(Contact contact) {
                return contact.getName();
            }
        };
        contactGrid.addColumn(nameColumn, "Имя");
        contactGrid.setColumnWidth(nameColumn, 25, Unit.PCT);
        final Column<Contact, String> phoneColumn = new Column<Contact, String>(phone) {
            @Override
            public String getValue(Contact contact) {
                return contact.getPhone();
            }
        };
        contactGrid.addColumn(phoneColumn, "Телефон");
        contactGrid.setColumnWidth(phoneColumn, 25, Unit.PCT);
// Создаем кнопки
        final ButtonCell openButton = new ButtonCell();
        openColumn = new Column<Contact, String>(openButton) {
            @Override
            public String getValue(Contact contact) {
                return "Открыть";
            }
        };
        contactGrid.addColumn(openColumn, "");
        contactGrid.setColumnWidth(openColumn, 80, Unit.PX);
        final ButtonCell editButton = new ButtonCell();
        editColumn = new Column<Contact, String>(editButton) {
            @Override
            public String getValue(Contact contact) {
                return "Редактировать";
            }
        };        
        contactGrid.addColumn(editColumn, "");
        contactGrid.setColumnWidth(editColumn, 80, Unit.PX);
        final ButtonCell delButton = new ButtonCell();
        delColumn = new Column<Contact, String>(delButton) {
            @Override
            public String getValue(Contact contact) {
                return "Удалить";
            }
        };        
        contactGrid.addColumn(delColumn, "");
        contactGrid.setColumnWidth(delColumn, 80, Unit.PX);
    }
  
    @Override
    public Column getEditColumn() {
        return editColumn;
    }

    @Override
    public Column getDelColumn() {
        return delColumn;
    }

    @Override
    public Column getOpenColumn() {
        return openColumn;
    }

    @Override
    public HasClickHandlers getNewClickHandlers() {
        return newButton;
    }

    @Override
    public HasClickHandlers getSearchClickHandlers() {
        return searchButton;
    }

    @Override
    public HasText getFilter() {
        return filter;
    }
    
    @Override
    public CellTable getGrid() {
        return contactGrid;
    }
    
}
