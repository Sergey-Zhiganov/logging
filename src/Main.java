import java.io.IOException;
import java.util.*;
import java.util.logging.*;

record Product(String name) {
    private static final Logger logger = Logger.getLogger(Product.class.getName());

    Product(String name) {
        this.name = name;
        logger.log(Level.INFO, "Продукт создан: " + name);
    }
}

record EmployeePosition(String position) {
    private static final Logger logger = Logger.getLogger(EmployeePosition.class.getName());

    EmployeePosition(String position) {
        this.position = position;
        logger.log(Level.INFO, "Должность создана: " + position);
    }
}

record Equipment(String name, int quantity) {
    private static final Logger logger = Logger.getLogger(Equipment.class.getName());

    Equipment(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        logger.log(Level.INFO, "Оборудование создано: " + name + ", количество: " + quantity);
    }
}

record Material(String name) {
    private static final Logger logger = Logger.getLogger(Material.class.getName());

    Material(String name) {
        this.name = name;
        logger.log(Level.INFO, "Материал создан: " + name);
    }
}

record Service(String name) {
    private static final Logger logger = Logger.getLogger(Service.class.getName());

    Service(String name) {
        this.name = name;
        logger.log(Level.INFO, "Услуга создана: " + name);
    }
}

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger mainLogger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("main.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            mainLogger.addHandler(fileHandler);
        } catch (IOException e) {
            mainLogger.log(Level.SEVERE, "Ошибка при создании fileHandler: ", e);
        }

        mainLogger.log(Level.INFO, "Программа запущена.");

        while (true) {
            try {
                System.out.println("Выберите категорию (1 - Продукция, 2 - Список должностей, 3 - Количество рабочих станков, 4 - Материалы, 5 - Услуги, 0 - Выйти): ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        mainLogger.log(Level.INFO, "Программа завершена.");
                        if (fileHandler != null) fileHandler.close();
                        return;
                    case 1:
                        handleProducts();
                        break;
                    case 2:
                        handleEmployeePositions();
                        break;
                    case 3:
                        handleEquipment();
                        break;
                    case 4:
                        handleMaterials();
                        break;
                    case 5:
                        handleServices();
                        break;
                    default:
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                        mainLogger.log(Level.WARNING, "Некорректный выбор в главном меню: " + choice);
                }
            } catch (InputMismatchException e) {
                System.out.println("Ввод должен быть числом. Попробуйте снова.");
                mainLogger.log(Level.WARNING, "Введено некорректное значение.", e);
                scanner.nextLine();
            } catch (Exception e) {
                mainLogger.log(Level.SEVERE, "Произошла ошибка: ", e);
            }
        }
    }

    private static void handleProducts() {
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("Product.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            Logger logger = Logger.getLogger(Product.class.getName());
            logger.addHandler(fileHandler);

            List<Product> productList = new ArrayList<>();

            while (true) {
                try {
                    System.out.println("1 - Просмотреть список продукции\n2 - Добавить продукт\n3 - Удалить продукт\n0 - Вернуться в главное меню");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 0:
                            fileHandler.close();
                            return;
                        case 1:
                            productList.forEach(product -> System.out.println(product.name()));
                            break;
                        case 2:
                            System.out.println("Введите название продукта:");
                            String productName = scanner.nextLine();
                            productList.add(new Product(productName));
                            logger.log(Level.INFO, "Добавлен продукт: " + productName);
                            break;
                        case 3:
                            if (!productList.isEmpty()) {
                                System.out.println("Введите номер продукта для удаления:");
                                int indexToRemove = scanner.nextInt();
                                scanner.nextLine();
                                if (indexToRemove >= 0 && indexToRemove < productList.size()) {
                                    Product removedProduct = productList.remove(indexToRemove);
                                    logger.log(Level.INFO, "Удален продукт: " + removedProduct.name());
                                } else {
                                    System.out.println("Некорректный номер продукта.");
                                    logger.log(Level.WARNING, "Некорректный номер продукта для удаления: " + indexToRemove);
                                }
                            } else {
                                System.out.println("Список продукции пуст.");
                                logger.log(Level.WARNING, "Попытка удалить продукт из пустого списка.");
                            }
                            break;
                        default:
                            System.out.println("Некорректный выбор. Попробуйте снова.");
                            logger.log(Level.WARNING, "Некорректный выбор в меню продукции: " + choice);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ввод должен быть числом. Попробуйте снова.");
                    logger.log(Level.WARNING, "Введено некорректное значение в меню продукции.", e);
                    scanner.nextLine();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Произошла ошибка в меню продукции: ", e);
                }
            }
        } catch (IOException e) {
            mainLogger.log(Level.SEVERE, "Ошибка при создании fileHandler для продукции: ", e);
        }
    }

    private static void handleEmployeePositions() {
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("EmployeePosition.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            Logger logger = Logger.getLogger(EmployeePosition.class.getName());
            logger.addHandler(fileHandler);

            List<EmployeePosition> employeePositions = new ArrayList<>();

            while (true) {
                try {
                    System.out.println("1 - Просмотреть список должностей\n2 - Добавить должность\n3 - Удалить должность\n0 - Вернуться в главное меню");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 0:
                            fileHandler.close();
                            return;
                        case 1:
                            employeePositions.forEach(position -> System.out.println(position.position()));
                            break;
                        case 2:
                            System.out.println("Введите название должности:");
                            String positionName = scanner.nextLine();
                            employeePositions.add(new EmployeePosition(positionName));
                            logger.log(Level.INFO, "Добавлена должность: " + positionName);
                            break;
                        case 3:
                            if (!employeePositions.isEmpty()) {
                                System.out.println("Введите номер должности для удаления:");
                                int indexToRemove = scanner.nextInt();
                                scanner.nextLine();
                                if (indexToRemove >= 0 && indexToRemove < employeePositions.size()) {
                                    EmployeePosition removedPosition = employeePositions.remove(indexToRemove);
                                    logger.log(Level.INFO, "Удалена должность: " + removedPosition.position());
                                } else {
                                    System.out.println("Некорректный номер должности.");
                                    logger.log(Level.WARNING, "Некорректный номер должности для удаления: " + indexToRemove);
                                }
                            } else {
                                System.out.println("Список должностей пуст.");
                                logger.log(Level.WARNING, "Попытка удалить должность из пустого списка.");
                            }
                            break;
                        default:
                            System.out.println("Некорректный выбор. Попробуйте снова.");
                            logger.log(Level.WARNING, "Некорректный выбор в меню должностей: " + choice);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ввод должен быть числом. Попробуйте снова.");
                    logger.log(Level.WARNING, "Введено некорректное значение в меню должностей.", e);
                    scanner.nextLine();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Произошла ошибка в меню должностей: ", e);
                }
            }
        } catch (IOException e) {
            mainLogger.log(Level.SEVERE, "Ошибка при создании fileHandler для должностей: ", e);
        }
    }

    private static void handleEquipment() {
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("Equipment.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            Logger logger = Logger.getLogger(Equipment.class.getName());
            logger.addHandler(fileHandler);

            List<Equipment> equipmentList = new ArrayList<>();

            while (true) {
                try {
                    System.out.println("1 - Просмотреть список оборудования\n2 - Добавить оборудование\n3 - Удалить оборудование\n0 - Вернуться в главное меню");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 0:
                            fileHandler.close();
                            return;
                        case 1:
                            equipmentList.forEach(equipment -> System.out.println(equipment.name() + ": " + equipment.quantity()));
                            break;
                        case 2:
                            System.out.println("Введите название оборудования:");
                            String equipmentName = scanner.nextLine();
                            System.out.println("Введите количество:");
                            int quantity = scanner.nextInt();
                            equipmentList.add(new Equipment(equipmentName, quantity));
                            logger.log(Level.INFO, "Добавлено оборудование: " + equipmentName + ", количество: " + quantity);
                            break;
                        case 3:
                            if (!equipmentList.isEmpty()) {
                                System.out.println("Введите номер оборудования для удаления:");
                                int indexToRemove = scanner.nextInt();
                                scanner.nextLine();
                                if (indexToRemove >= 0 && indexToRemove < equipmentList.size()) {
                                    Equipment removedEquipment = equipmentList.remove(indexToRemove);
                                    logger.log(Level.INFO, "Удалено оборудование: " + removedEquipment.name());
                                } else {
                                    System.out.println("Некорректный номер оборудования.");
                                    logger.log(Level.WARNING, "Некорректный номер оборудования для удаления: " + indexToRemove);
                                }
                            } else {
                                System.out.println("Список оборудования пуст.");
                                logger.log(Level.WARNING, "Попытка удалить оборудование из пустого списка.");
                            }
                            break;
                        default:
                            System.out.println("Некорректный выбор. Попробуйте снова.");
                            logger.log(Level.WARNING, "Некорректный выбор в меню оборудования: " + choice);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ввод должен быть числом. Попробуйте снова.");
                    logger.log(Level.WARNING, "Введено некорректное значение в меню оборудования.", e);
                    scanner.nextLine();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Произошла ошибка в меню оборудования: ", e);
                }
            }
        } catch (IOException e) {
            mainLogger.log(Level.SEVERE, "Ошибка при создании fileHandler для оборудования: ", e);
        }
    }

    private static void handleMaterials() {
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("Material.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            Logger logger = Logger.getLogger(Material.class.getName());
            logger.addHandler(fileHandler);

            List<Material> materials = new ArrayList<>();

            while (true) {
                try {
                    System.out.println("1 - Просмотреть список материалов\n2 - Добавить материал\n3 - Удалить материал\n0 - Вернуться в главное меню");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 0:
                            fileHandler.close();
                            return;
                        case 1:
                            materials.forEach(material -> System.out.println(material.name()));
                            break;
                        case 2:
                            System.out.println("Введите название материала:");
                            String materialName = scanner.nextLine();
                            materials.add(new Material(materialName));
                            logger.log(Level.INFO, "Добавлен материал: " + materialName);
                            break;
                        case 3:
                            if (!materials.isEmpty()) {
                                System.out.println("Введите номер материала для удаления:");
                                int indexToRemove = scanner.nextInt();
                                scanner.nextLine();
                                if (indexToRemove >= 0 && indexToRemove < materials.size()) {
                                    Material removedMaterial = materials.remove(indexToRemove);
                                    logger.log(Level.INFO, "Удален материал: " + removedMaterial.name());
                                } else {
                                    System.out.println("Некорректный номер материала.");
                                    logger.log(Level.WARNING, "Некорректный номер материала для удаления: " + indexToRemove);
                                }
                            } else {
                                System.out.println("Список материалов пуст.");
                                logger.log(Level.WARNING, "Попытка удалить материал из пустого списка.");
                            }
                            break;
                        default:
                            System.out.println("Некорректный выбор. Попробуйте снова.");
                            logger.log(Level.WARNING, "Некорректный выбор в меню материалов: " + choice);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ввод должен быть числом. Попробуйте снова.");
                    logger.log(Level.WARNING, "Введено некорректное значение в меню материалов.", e);
                    scanner.nextLine();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Произошла ошибка в меню материалов: ", e);
                }
            }
        } catch (IOException e) {
            mainLogger.log(Level.SEVERE, "Ошибка при создании fileHandler для материалов: ", e);
        }
    }

    private static void handleServices() {
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("Service.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            Logger logger = Logger.getLogger(Service.class.getName());
            logger.addHandler(fileHandler);

            List<Service> services = new ArrayList<>();

            while (true) {
                try {
                    System.out.println("1 - Просмотреть список услуг\n2 - Добавить услугу\n3 - Удалить услугу\n0 - Вернуться в главное меню");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 0:
                            fileHandler.close();
                            return;
                        case 1:
                            services.forEach(service -> System.out.println(service.name()));
                            break;
                        case 2:
                            System.out.println("Введите название услуги:");
                            String serviceName = scanner.nextLine();
                            services.add(new Service(serviceName));
                            logger.log(Level.INFO, "Добавлена услуга: " + serviceName);
                            break;
                        case 3:
                            if (!services.isEmpty()) {
                                System.out.println("Введите номер услуги для удаления:");
                                int indexToRemove = scanner.nextInt();
                                scanner.nextLine();
                                if (indexToRemove >= 0 && indexToRemove < services.size()) {
                                    Service removedService = services.remove(indexToRemove);
                                    logger.log(Level.INFO, "Удалена услуга: " + removedService.name());
                                } else {
                                    System.out.println("Некорректный номер услуги.");
                                    logger.log(Level.WARNING, "Некорректный номер услуги для удаления: " + indexToRemove);
                                }
                            } else {
                                System.out.println("Список услуг пуст.");
                                logger.log(Level.WARNING, "Попытка удалить услугу из пустого списка.");
                            }
                            break;
                        default:
                            System.out.println("Некорректный выбор. Попробуйте снова.");
                            logger.log(Level.WARNING, "Некорректный выбор в меню услуг: " + choice);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ввод должен быть числом. Попробуйте снова.");
                    logger.log(Level.WARNING, "Введено некорректное значение в меню услуг.", e);
                    scanner.nextLine(); // очистка буфера сканера
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Произошла ошибка в меню услуг: ", e);
                }
            }
        } catch (IOException e) {
            mainLogger.log(Level.SEVERE, "Ошибка при создании fileHandler для услуг: ", e);
        }
    }
}