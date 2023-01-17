package com.cherkasov.service;

import com.cherkasov.exceptions.IncorrectLineException;
import com.cherkasov.model.*;
import com.cherkasov.repository.InvoiceListRepository;
import com.cherkasov.util.Util;
import org.apache.commons.lang3.EnumUtils;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.DoubleStream;

public class ShopService {
    private final InvoiceListRepository invoiceListRepository;

    PersonService PERSON_SERVICE = PersonService.getInstance();
    private final Random random = new Random();

    private static ShopService instance;

    private ShopService(final InvoiceListRepository invoiceListRepository) {
        this.invoiceListRepository = invoiceListRepository;
    }

    public static ShopService getInstance() {
        if (instance == null) {
            instance = new ShopService(InvoiceListRepository.getInstance());
        }
        return instance;
    }

    public static ShopService getInstance(final InvoiceListRepository repository) {
        if (instance == null) {
            instance = new ShopService(repository);
        }
        return instance;
    }

    public void createFifteenRandomInvoice() {
        for (int i = 0; i < 15; i++) {
            createRandomInvoice();
        }
    }

    public Invoice createRandomInvoice() {
        Invoice invoice = new Invoice(PERSON_SERVICE.createRundomCustomer(), createRandomListGoods());
        invoiceListRepository.save(invoice);
        return invoice;
    }

    private List<Device> createRandomListGoods() {
        final List<Device> deviceList = new ArrayList<>();
        final int price = random.nextInt(10000, 50000);
        final double diagonal = random.nextDouble(10, 40);
        deviceList.add(new Telephone(Util.getRandomString(), Util.getRandomString(),
                price, Util.getRandomString()));
        deviceList.add(new Television(Util.getRandomString(), Util.getRandomString(),
                price, diagonal, Util.getRandomString()));
        return deviceList;
    }

    public Invoice[] getAll() {
        return invoiceListRepository.getAll();
    }

    public Optional<Invoice> getById(final String id) {
        return invoiceListRepository.getById(id);
    }

    public void delete(final String id) {
        invoiceListRepository.delete(id);
    }


    public Optional<Invoice> createInvoiceFromFile(final String fileWay) {
        if (fileWay == null || !fileWay.endsWith(".csv")) {
            return Optional.empty();
        } else {
            Invoice invoice = new Invoice(PERSON_SERVICE.createRundomCustomer(), createListGoods(fileWay));
            invoiceListRepository.save(invoice);
            printInvoiceInfo(invoice);
            return Optional.of(invoice);
        }
    }

    private List<Device> createListGoods(final String fileWay) {
        final int countGoods = random.nextInt(1, 5);
        List<Map<String, String>> listMapFields = createListMap(fileWay, countGoods);
        List<Device> listGoods = new ArrayList<>(countGoods);
        for (int i = 0; i < countGoods; i++) {
            listGoods.add(createGoods(listMapFields.get(i)));
        }
        return listGoods;
    }

    private List<Map<String, String>> createListMap(final String fileWay, final int countGoods) {
        List<String> data = createList(fileWay);
        String[] keys = data.get(0).split(";");
        data.remove(0);
        List<String[]> fields = createFieldsList(data);
        List<Map<String, String>> listMap = new ArrayList<>();
        for (int i = 0; i < countGoods; i++) {
            int randomInt = random.nextInt(0, (fields.size() - 1));
            listMap.add(createMap(keys, fields.get(randomInt)));
        }
        return listMap;
    }

    private List<String[]> createFieldsList(final List<String> data) {
        return data.stream()
                .map(field -> field.split(";"))
                .peek(array -> {
                    for (String s : array) {
                        if (s.isBlank()) {
                            try {
                                throw new IncorrectLineException("Incorrect line in file");
                            } catch (IncorrectLineException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .toList();
    }

    private List<String> createList(final String fileWay) {
        BufferedReader reader = createBufferedReader(fileWay);
        List<String> data = new LinkedList<>();
        String s;
        try {
            while ((s = reader.readLine()) != null) {
                data.add(s);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    private Map<String, String> createMap(final String[] keys, final String[] fields) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], fields[i]);
        }
        return map;
    }

    private BufferedReader createBufferedReader(final String fileWay) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream(fileWay);
        return new BufferedReader(new InputStreamReader(input));
    }

    private Device createGoods(final Map<String, String> mapFields) {
        Function<Map<String, String>, Device> mapper = map1 -> {
            String typeString = mapFields.getOrDefault("deviceType", "TELEPHONE");
            DeviceType type = EnumUtils.getEnum(DeviceType.class, typeString);
            if (type == DeviceType.TELEPHONE) {
                return createTelephone(map1);
            } else {
                return createTelevision(map1);
            }
        };
        return mapper.apply(mapFields);
    }

    private static Device createTelephone(final Map<String, String> map) {
        Telephone telephone = (Telephone) createDevice(map, DeviceType.TELEPHONE);
        final String model = map.getOrDefault("model", "sfdfd");
        telephone.setModel(model);
        return telephone;
    }

    private static Device createTelevision(final Map<String, String> map) {
        Television television = (Television) createDevice(map, DeviceType.TELEVISION);
        final String country = map.getOrDefault("country", "Kongo");
        television.setCountry(country);
        final String StringDiagonal = map.getOrDefault("diagonal", "56,7");
        double diagonal = Double.parseDouble(StringDiagonal);
        television.setDiagonal(diagonal);
        return television;
    }

    private static Device createDevice(final Map<String, String> map, final DeviceType deviceType) {
        final Device device;
        if (deviceType == DeviceType.TELEPHONE) {
            device = new Telephone();
        } else {
            device = new Television();
        }
        device.setDeviceType(deviceType);
        final String series = map.getOrDefault("series", "sjdfh");
        device.setSeries(series);
        final String screenType = map.getOrDefault("screen type", "sdhsjd");
        device.setScreenType(screenType);
        final String StringPrice = map.getOrDefault("price", "35545,43");
        double price = Double.parseDouble(StringPrice);
        device.setPrice(price);
        return device;
    }

    private void printInvoiceInfo(final Invoice invoice) {
        StringBuffer buffer = new StringBuffer();
        Date date = new Date();
        for (int i = 0; i < invoice.getGoods().size(); i++) {
            buffer.append(invoice.getGoods().get(i).toString());
        }
        System.out.printf("Date:" + date + ", Customer: %s, Devices: %s%n"
                + date, invoice.getCustomer().toString(), buffer);
    }


    final Comparator<Invoice> ageCustomerComparator = (o1, o2) ->
            Integer.compare(o2.getCustomer().getAge(), o1.getCustomer().getAge());

    final Comparator<Invoice> countDevicesComparator = Comparator.comparingInt(o -> o.getGoods().size());

    final Comparator<Invoice> totalPriceComparator = (o1, o2) -> {
        double sum1 = o1.getGoods().stream()
                .flatMapToDouble(device -> DoubleStream.of(device.getPrice()))
                .sum();
        double sum2 = o2.getGoods().stream()
                .flatMapToDouble(device -> DoubleStream.of(device.getPrice()))
                .sum();
        return Double.compare(sum1, sum2);
    };

    final Comparator<Invoice> invoiceComparator = ageCustomerComparator
            .thenComparing(countDevicesComparator)
            .thenComparing(totalPriceComparator);

    public List<Invoice> sortInvoices() {
        return Arrays.stream(invoiceListRepository.getAll())
                .sorted(invoiceComparator)
                .toList();
    }

}

