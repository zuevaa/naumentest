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
import com.zaa.naumentest.client.presenter.Err404Presenter;

// View 404 ошибки

public class Err404View extends Composite implements Err404Presenter.Display {

    private final Panel panel;
    private final Label error;
    private final Button cancelButton;

    public Err404View() {
        panel = new FlowPanel();
        initWidget(panel);
        Label title = new Label();
        title.setText("Страница не найдена");
        panel.add(title);
        error  = new Label();
        panel.add(createLine("Подробно", error));
        final Panel buttonsPanel = new FlowPanel();
        cancelButton = new Button("На главную");
        buttonsPanel.add(cancelButton);
        panel.add(buttonsPanel);
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
    public HasClickHandlers getCancelClickHandlers() {
        return cancelButton;
    }

    @Override
    public HasText getError() {
        return error;
    }

}