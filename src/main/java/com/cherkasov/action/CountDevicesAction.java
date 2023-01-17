package com.cherkasov.action;

import com.cherkasov.model.DeviceType;
import com.cherkasov.util.UserInput;

public class CountDevicesAction implements Action {
    @Override
    public void execute() {
        String[] menu = {"Telephone", "Television"};
        final int userChoice = UserInput.menu(menu);
        if (userChoice == 0) {
            DATA_ANALYTICS.countDevices(DeviceType.TELEPHONE);
        }
        if (userChoice == 1) {
            DATA_ANALYTICS.countDevices(DeviceType.TELEVISION);
        }
    }



}
