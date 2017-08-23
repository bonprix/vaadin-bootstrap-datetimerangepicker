package org.vaadin.addons.datetimerangepicker.client;

import java.util.Date;

import org.vaadin.addons.datetimerangepicker.DateTimeRangeField;
import org.vaadin.addons.datetimerangepicker.client.VDateTimeRangeField.DateRangeFieldClientUpdateValueHandler;

import com.google.gwt.core.client.JsDate;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(DateTimeRangeField.class)
public class DateTimeRangeFieldConnector extends AbstractComponentConnector {

    private static final long serialVersionUID = 1L;

    // ServerRpc is used to send events to server. Communication implementation
    // is automatically created here
    DateTimeRangeFieldServerRpc rpc = RpcProxy.create(DateTimeRangeFieldServerRpc.class, this);

    public DateTimeRangeFieldConnector() {

        // To receive RPC events from server, we register ClientRpc
        // implementation
        registerRpc(DateTimeRangeFieldClientRpc.class, new DateTimeRangeFieldClientRpc() {
            private static final long serialVersionUID = 1L;
        });

        getWidget().setUpdateValueHandler(new DateRangeFieldClientUpdateValueHandler() {

            @Override
            public void onUpdateValue(final JsDate start, final JsDate end) {
                final Date startDate = new Date((long) start.getTime());
                final Date endDate = new Date((long) end.getTime());
                DateTimeRangeFieldConnector.this.rpc.valueChanged(startDate, endDate);
            }
        });
    }

    // We must implement getWidget() to cast to correct type
    // (this will automatically create the correct widget type)
    @Override
    public VDateTimeRangeField getWidget() {
        return (VDateTimeRangeField) super.getWidget();
    }

    // We must implement getState() to cast to correct type
    @Override
    public DateTimeRangeFieldState getState() {
        return (DateTimeRangeFieldState) super.getState();
    }

    // Whenever the state changes in the server-side, this method is called
    @Override
    public void onStateChanged(final StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        getWidget().refresh(getState().getLanguage(), getState().getApplyLabel(), getState().getCancelLabel(), getState().getParentEl(), getState().getStartDate(),
                            getState().getEndDate(), getState().getMinDate(), getState().getMaxDate(), getState().isShowDropdowns(),
                            getState().isShowWeekNumbers(), getState().isShowISOWeekNumbers(), getState().isSingleDatePicker(), getState().isTimePicker(),
                            getState().isTimePicker24Hour(), getState().getTimePickerIncrement(), getState().isTimePickerSeconds(), getState().getDateLimitSpanMoment(), getState().getDateLimitSpanValue(), getState().isAutoApply(),
                            getState().isLinkedCalendars(), getState().isAutoUpdateInput(), getState().getOpens(), getState().getDrops(), getState().getButtonClasses(), getState().getApplyClass(), getState().getCancelClass(), getState().getDateRanges(), getState().isAlwaysShowCalendars(), getState().isShowCustomRangeLabel());
    }
}
