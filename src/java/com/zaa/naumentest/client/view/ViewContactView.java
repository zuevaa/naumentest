package com.zaa.naumentest.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.zaa.naumentest.client.presenter.ViewContactPresenter;

// View просмотра

public class ViewContactView extends Composite implements ViewContactPresenter.Display {

    private final Panel panel;
    private final Label name;
    private final Label phone;
    private final Button editButton;
    private final Button delButton;
    private final Button cancelButton;

    public ViewContactView() {
        panel = new FlowPanel();
        initWidget(panel);
        final FlowPanel infoPanel = new FlowPanel();
        panel.add(infoPanel);
        name = new Label();
        infoPanel.add(createLine("Имя", name));
        phone = new Label();
        infoPanel.add(createLine("Телефон", phone));
        final Panel buttonsPanel = new FlowPanel();
        panel.add(buttonsPanel);
        editButton = new Button("Изменить");
        buttonsPanel.add(editButton);
        delButton = new Button("Удалить");
        buttonsPanel.add(delButton);
        cancelButton = new Button("Отменить");
        buttonsPanel.add(cancelButton);
    }

    private Widget createLine(String header, Widget value) {
        final Panel result = new FlowPanel();
        final Label label = new Label(header);
        result.add(label);
        final SimplePanel valuePanel = new SimplePanel();
        valuePanel.setWidget(value);
        result.add(valuePanel);
        return result;
    }

    @Override
    public HasClickHandlers getEditClickHandlers() {
        return editButton;
    }

    @Override
    public HasClickHandlers getDelClickHandlers() {
        return delButton;
    }

    @Override
    public HasClickHandlers getCancelClickHandlers() {
        return cancelButton;
    }

    @Override
    public HasText getName() {
        return name;
    }

    @Override
    public HasText getPhone() {
        return phone;
    }
}