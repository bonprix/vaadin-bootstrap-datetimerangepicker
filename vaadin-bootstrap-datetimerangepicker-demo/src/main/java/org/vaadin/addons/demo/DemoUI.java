package org.vaadin.addons.demo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.DateTimeRangeField;
import org.vaadin.addons.demo.model.SomeBean;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@Title("MyComponent Add-on Demo")
@Widgetset("org.vaadin.addons.demo.DemoWidgetSet")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(
            value = "/*",
            asyncSupported = true)
    @VaadinServletConfiguration(
            productionMode = false,
            ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    private static final Locale LOCALE = Locale.ENGLISH;

    private static final String DATE_PATTERN = "MM/dd/YYYY";
    private static final Format DATE_FORMATTER = new SimpleDateFormat(DemoUI.DATE_PATTERN);

    private static final String APPLY_LABEL = "Apply";
    private static final String CANCEL_LABEL = "Cancel";

    private static final String DEFAULT_LANGUAGE = "en";

    // UI Components

    // Initialize our new UI component
    private DateTimeRangeField dateRangeField;

    private String language;

    private DateField startDateField;
    private DateField endDateField;
    private DateField minDateField;
    private DateField maxDateField;

    private final Date startDate = Date.from(LocalDate.now()
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant());

    private final Date endDate = Date.from(LocalDate.now()
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant());

    private ComboBox comboOpens;
    private ComboBox comboDrops;

    private CheckBox checkShowDropDowns;
    private CheckBox checkShowWeekNumbers;
    private CheckBox checkShowISOWeekNumbers;

    private CheckBox checkSingleDatePicker;
    private CheckBox checkTimePicker;
    private CheckBox checkTimePicker24Hour;

    private TextField textTimePickerIncrement;

    private CheckBox checkTimePickerSeconds;
    private CheckBox checkAutoApply;
    private CheckBox checkLinkedCalendars;
    private CheckBox checkAutoUpdateInput;

    private Button btnRefreshUI;

    private TextField textButtonClasses;
    private TextField textApplyClass;
    private TextField textCancelClass;

    @Override
    protected void init(final VaadinRequest request) {

        // Initialize our new UI component
        this.dateRangeField = new DateTimeRangeField(DemoUI.DATE_FORMATTER);

        final BeanFieldGroup<SomeBean> fieldGroup = new BeanFieldGroup<>(SomeBean.class);
        fieldGroup.setBuffered(false);
        fieldGroup.setItemDataSource(new SomeBean());
        fieldGroup.bind(this.dateRangeField, "dateRange");

        this.language = "en";

        // StartDate
        this.startDateField = new DateField("startDate");
        this.startDateField.setLocale(DemoUI.LOCALE);
        this.startDateField.setValue(this.startDate);

        // EndDate
        this.endDateField = new DateField("endDate");
        this.endDateField.setLocale(DemoUI.LOCALE);
        this.endDateField.setValue(this.endDate);

        // MinDate
        this.minDateField = new DateField("minDate");
        this.minDateField.setLocale(DemoUI.LOCALE);

        // MaxDate
        this.maxDateField = new DateField("maxDate");
        this.maxDateField.setLocale(DemoUI.LOCALE);

        // Combobox opens
        this.comboOpens = new ComboBox("opens");
        final java.util.List<Object> opensList = java.util.Arrays.asList("right", "left", "center");
        this.comboOpens.addItem(opensList.get(0));
        this.comboOpens.addItem(opensList.get(1));
        this.comboOpens.addItem(opensList.get(2));
        this.comboOpens.setNullSelectionAllowed(false);
        this.comboOpens.setValue(opensList.get(0));

        // Combobox drops
        final java.util.List<Object> dropsList = java.util.Arrays.asList("down", "up");
        this.comboDrops = new ComboBox("drops");
        this.comboDrops.addItem(dropsList.get(0));
        this.comboDrops.addItem(dropsList.get(1));
        this.comboDrops.setNullSelectionAllowed(false);
        this.comboDrops.setValue(dropsList.get(0));

        // Checkbox showDropdowns
        this.checkShowDropDowns = new CheckBox("showDropdowns");
        this.checkShowDropDowns.setValue(false);

        // Checkbox showWeekNumbers
        this.checkShowWeekNumbers = new CheckBox("showWeekNumbers");
        this.checkShowWeekNumbers.setValue(false);

        // Checkbox showISOWeekNumbers
        this.checkShowISOWeekNumbers = new CheckBox("showISOWeekNumbers");
        this.checkShowISOWeekNumbers.setValue(false);

        // Checkbox singleDatePicker
        this.checkSingleDatePicker = new CheckBox("singleDatePicker");
        this.checkSingleDatePicker.setValue(false);

        // Checkbox timePicker
        this.checkTimePicker = new CheckBox("timePicker");
        this.checkTimePicker.setValue(false);

        // Checkbox timePicker24Hour
        this.checkTimePicker24Hour = new CheckBox("timePicker24Hour");
        this.checkTimePicker24Hour.setValue(false);

        this.textTimePickerIncrement = new TextField();
        this.textTimePickerIncrement.setValue(String.valueOf(1));

        // Checkbox timePickerSeconds
        this.checkTimePickerSeconds = new CheckBox("timePickerSeconds");
        this.checkTimePickerSeconds.setValue(false);

        // Checkbox autoApply
        this.checkAutoApply = new CheckBox("autoApply");
        this.checkAutoApply.setValue(false);

        // Checkbox linkedCalendars
        this.checkLinkedCalendars = new CheckBox("linkedCalendars");
        this.checkLinkedCalendars.setValue(false);

        // Checkbox autoUpdateInput
        this.checkAutoUpdateInput = new CheckBox("autoUpdateInput");
        this.checkAutoUpdateInput.setValue(false);

        // Textfield buttonClasses
        this.textButtonClasses = new TextField("buttonClasses");
        this.textButtonClasses.setValue("btn btn-sm");

        // Textfield applyClass
        this.textApplyClass = new TextField("applyClass");
        this.textApplyClass.setValue("btn-success");

        // Textfield cancelClass
        this.textCancelClass = new TextField("cancelClass");
        this.textCancelClass.setValue("btn-default");

        // Button buildUI

        this.btnRefreshUI = new Button("refreshUI");
        this.btnRefreshUI.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                DemoUI.this.dateRangeField.startDate(DemoUI.this.startDateField.getValue())
                        .endDate(DemoUI.this.endDateField.getValue())
                        .minDate(DemoUI.this.minDateField.getValue())
                        .maxDate(DemoUI.this.maxDateField.getValue())
                        .applyLabel(DemoUI.APPLY_LABEL)
                        .canelLabel(DemoUI.CANCEL_LABEL)
                        .opens(DemoUI.this.comboOpens.getValue()
                                .toString())
                        .language(DemoUI.DEFAULT_LANGUAGE)
                        .drops(DemoUI.this.comboDrops.getValue()
                                .toString())
                        .showDropdowns(DemoUI.this.checkShowDropDowns.getValue())
                        .showWeekNumbers(DemoUI.this.checkShowWeekNumbers.getValue())
                        .showISOWeekNumbers(DemoUI.this.checkShowISOWeekNumbers.getValue())
                        .singleDatePicker(DemoUI.this.checkSingleDatePicker.getValue())
                        .timePicker(DemoUI.this.checkTimePicker.getValue())
                        .timePicker24Hour(DemoUI.this.checkTimePicker24Hour.getValue())
                        .timePickerIncrement(Integer.valueOf(DemoUI.this.textTimePickerIncrement.getValue()))
                        .timePickerSeconds(DemoUI.this.checkTimePickerSeconds.getValue())
                        .autoApply(DemoUI.this.checkAutoApply.getValue())
                        .linkedCalendars(DemoUI.this.checkLinkedCalendars.getValue())
                        .autoUpdateInput(DemoUI.this.checkAutoUpdateInput.getValue())
                        .buttonClasses(DemoUI.this.textButtonClasses.getValue())
                        .applyClass(DemoUI.this.textApplyClass.getValue())
                        .cancelClass(DemoUI.this.textCancelClass.getValue())
                        .refresh();
            }
        });

        // Button show

        final Button button = new Button("show");
        button.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                Notification.show(fieldGroup.getItemDataSource()
                        .getBean()
                        .getDateRange()
                        .toString());
            }
        });

        final VerticalLayout elementsLayout = new VerticalLayout();
        elementsLayout.setSpacing(true);

        // LEFT

        elementsLayout.addComponent(this.startDateField);
        elementsLayout.setComponentAlignment(this.startDateField, Alignment.MIDDLE_LEFT);

        elementsLayout.addComponent(this.endDateField);
        elementsLayout.setComponentAlignment(this.endDateField, Alignment.MIDDLE_LEFT);

        elementsLayout.addComponent(this.minDateField);
        elementsLayout.setComponentAlignment(this.minDateField, Alignment.MIDDLE_LEFT);

        elementsLayout.addComponent(this.maxDateField);
        elementsLayout.setComponentAlignment(this.maxDateField, Alignment.MIDDLE_LEFT);

        elementsLayout.addComponent(this.comboOpens);
        elementsLayout.setComponentAlignment(this.comboOpens, Alignment.MIDDLE_LEFT);

        elementsLayout.addComponent(this.comboDrops);
        elementsLayout.setComponentAlignment(this.comboDrops, Alignment.MIDDLE_LEFT);

        // MIDDLE

        elementsLayout.addComponent(this.checkShowDropDowns);
        elementsLayout.setComponentAlignment(this.checkShowDropDowns, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkShowDropDowns);
        elementsLayout.setComponentAlignment(this.checkShowDropDowns, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkShowWeekNumbers);
        elementsLayout.setComponentAlignment(this.checkShowWeekNumbers, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkShowISOWeekNumbers);
        elementsLayout.setComponentAlignment(this.checkShowISOWeekNumbers, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkSingleDatePicker);
        elementsLayout.setComponentAlignment(this.checkSingleDatePicker, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkTimePicker);
        elementsLayout.setComponentAlignment(this.checkTimePicker, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkTimePicker24Hour);
        elementsLayout.setComponentAlignment(this.checkTimePicker24Hour, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.textTimePickerIncrement);
        elementsLayout.setComponentAlignment(this.textTimePickerIncrement, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkTimePickerSeconds);
        elementsLayout.setComponentAlignment(this.checkTimePickerSeconds, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkAutoApply);
        elementsLayout.setComponentAlignment(this.checkAutoApply, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkLinkedCalendars);
        elementsLayout.setComponentAlignment(this.checkLinkedCalendars, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkAutoUpdateInput);
        elementsLayout.setComponentAlignment(this.checkAutoUpdateInput, Alignment.MIDDLE_CENTER);

        // ---

        elementsLayout.addComponent(this.btnRefreshUI);
        elementsLayout.setComponentAlignment(this.btnRefreshUI, Alignment.MIDDLE_CENTER);

        //
        // DateRangeField
        //

        elementsLayout.addComponent(this.dateRangeField);
        elementsLayout.setComponentAlignment(this.dateRangeField, Alignment.MIDDLE_CENTER);
        elementsLayout.addComponent(button);
        elementsLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);

        // RIGHT

        elementsLayout.addComponent(this.textButtonClasses);
        elementsLayout.setComponentAlignment(this.textButtonClasses, Alignment.MIDDLE_RIGHT);

        elementsLayout.addComponent(this.textApplyClass);
        elementsLayout.setComponentAlignment(this.textApplyClass, Alignment.MIDDLE_RIGHT);

        elementsLayout.addComponent(this.textCancelClass);
        elementsLayout.setComponentAlignment(this.textCancelClass, Alignment.MIDDLE_RIGHT);

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(elementsLayout);
        layout.setComponentAlignment(elementsLayout, Alignment.MIDDLE_CENTER);

        setContent(layout);
    }
}
