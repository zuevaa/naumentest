package com.zaa.naumentest.client.view;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.zaa.naumentest.client.presenter.EditContactPresenter;

// View редактирования

public class EditContactView extends Composite implements EditContactPresenter.Display {

    private final Panel panel;
    private final TextBox name;
    private final TextBox phone;
    private final Button saveButton;
    private final Button cancelButton;
    private final Label error;

    public EditContactView() {
        panel = new FlowPanel();
        initWidget(panel);
        final FlowPanel infoPanel = new FlowPanel();
        panel.add(infoPanel);
        name = new TextBox();
        infoPanel.add(createLine("Имя", name));
        phone = new TextBox();
        infoPanel.add(createLine("Телефон", phone));
        final Panel buttonsPanel = new FlowPanel();
        panel.add(buttonsPanel);        
        saveButton = new Button("Сохранить");
        buttonsPanel.add(saveButton);
        cancelButton = new Button("Отменить");
        buttonsPanel.add(cancelButton);
        error = new Label();
        panel.add(error);
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
    protected void onAttach() {
        super.onAttach();
        setFocus();
    }

    private void setFocus() {
        name.setFocus(true);
        name.setSelectionRange(0, name.getText().length());
    }

    @Override
    public HasClickHandlers getSaveClickHandlers() {
        return saveButton;
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
    @Override
    public HasText getError() {
        return error;
    }    

}