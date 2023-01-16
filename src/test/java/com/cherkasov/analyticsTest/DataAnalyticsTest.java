package com.cherkasov.analyticsTest;

import com.cherkasov.analytics.DataAnalytics;
import com.cherkasov.model.DeviceType;
import com.cherkasov.model.Invoice;
import com.cherkasov.service.ShopService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class DataAnalyticsTest {

    final private DataAnalytics target = new DataAnalytics();
    ShopService SHOP_SERVICE = ShopService.getInstance();

    @Test
    public void countDevices() {
        int actual = target.countDevices(DeviceType.TELEPHONE);
        Assertions.assertTrue(actual > -1);
    }

    @Test
    public void countDevicesNotThrow() {
        Assertions.assertDoesNotThrow(() -> target.countDevices(DeviceType.TELEPHONE));
    }

    @Test
    public void countDevicesNotThrowNull() {
        Assertions.assertDoesNotThrow(() -> target.countDevices(null));
    }

    @Test
    public void minimalInvoiceSum() {
        double actual = target.minimalInvoiceSum();
        Assertions.assertTrue(actual > -1);
    }

    @Test
    public void minimalInvoiceSumNotThrow() {
        Assertions.assertDoesNotThrow(target::minimalInvoiceSum);
    }

    @Test
    public void countSumAllInvoices() {
        double actual = target.countSumAllInvoices();
        Assertions.assertTrue(actual > -1);
    }

    @Test
    public void countSumAllInvoicesNotThrow() {
        Assertions.assertDoesNotThrow(target::countSumAllInvoices);
    }

    @Test
    public void countRetailInvoices() {
        double actual = target.countRetailInvoices();
        Assertions.assertTrue(actual > -1);
    }

    @Test
    public void countRetailInvoicesNotThrow() {
        Assertions.assertDoesNotThrow(target::countRetailInvoices);
    }

    @Test
    public void findInvoicesWithOneDeviceNotNull() {
        List<Invoice> actual = target.findInvoicesWithOneDevice();
        Assertions.assertNotNull(actual);
    }

    @Test
    public void findInvoicesWithOneDeviceNotThrow() {
        Assertions.assertDoesNotThrow(target::findInvoicesWithOneDevice);
    }

    @Test
    public void findFirstThreeInvoices() {
        List<Invoice> expected = target.findFirstThreeInvoices();
        List<Invoice> actual = new ArrayList<>();
        actual.add(SHOP_SERVICE.getAll()[0]);
        actual.add(SHOP_SERVICE.getAll()[1]);
        actual.add(SHOP_SERVICE.getAll()[2]);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void findFirstThreeInvoicesNotNull() {
        List<Invoice> actual = target.findFirstThreeInvoices();
        Assertions.assertNotNull(actual);
    }

    @Test
    public void findFirstThreeInvoicesNotThrow() {
        Assertions.assertDoesNotThrow(target::findFirstThreeInvoices);
    }

    @Test
    public void findInvoicesCustomerUnderEighteenNotNull() {
        List<Invoice> actual = target.findInvoicesCustomerUnderEighteen();
        Assertions.assertNotNull(actual);
    }

    @Test
    public void findInvoicesCustomerUnderEighteenNotThrow() {
        Assertions.assertDoesNotThrow(target::findInvoicesCustomerUnderEighteen);
    }

}

