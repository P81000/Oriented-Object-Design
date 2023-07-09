/*Luiz Fernando Moloni
 *Pedro Henrique Masteguin
 */
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ConferenceRoom {
    private String name;
    private int capacity;
    private boolean isAvailable;
    private LocalDateTime reservedStartTime;
    private LocalDateTime reservedEndTime;

    public ConferenceRoom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.isAvailable = true;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void reserve(LocalDateTime startTime, LocalDateTime endTime) {
        this.reservedStartTime = startTime;
        this.reservedEndTime = endTime;
        this.isAvailable = false;
    }

    public LocalDateTime getReservedStartTime() {
        return reservedStartTime;
    }

    public LocalDateTime getReservedEndTime() {
        return reservedEndTime;
    }

    public void cancelReservation() {
        this.reservedStartTime = null;
        this.reservedEndTime = null;
        this.isAvailable = true;
    }
}

interface SearchStrategy {
    List<ConferenceRoom> searchRooms(List<ConferenceRoom> rooms);
}

class AvailabilitySearchStrategy implements SearchStrategy {
    public List<ConferenceRoom> searchRooms(List<ConferenceRoom> rooms) {
        List<ConferenceRoom> availableRooms = new ArrayList<>();
        for (ConferenceRoom room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
}

class CapacitySearchStrategy implements SearchStrategy {
    private int requiredCapacity;

    public CapacitySearchStrategy(int requiredCapacity) {
        this.requiredCapacity = requiredCapacity;
    }

    public List<ConferenceRoom> searchRooms(List<ConferenceRoom> rooms) {
        List<ConferenceRoom> availableRooms = new ArrayList<>();
        for (ConferenceRoom room : rooms) {
            if (room.isAvailable() && room.getCapacity() >= requiredCapacity) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
}

interface RoomObserver {
    void update(ConferenceRoom room);
}

class UserNotificationObserver implements RoomObserver {
    private String username;

    public UserNotificationObserver(String username) {
        this.username = username;
    }

    public void update(ConferenceRoom room) {
        if (room.isAvailable()) {
            System.out.println("Usuário " + username + ": A sala " + room.getName() + " está disponível para reserva !");
        }
    }
}

interface Employee {
    String getUsername();
    void viewCurrentReservations();
}

class RegularEmployee implements Employee {
    private String username;

    public RegularEmployee(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void viewCurrentReservations() {
        RoomBookingSystem bookingSystem = RoomBookingSystem.getInstance();
        List<ConferenceRoom> userReservations = bookingSystem.getUserReservations(username);
        if (userReservations.isEmpty()) {
            System.out.println("Usuário " + username + ": Você não possui reservas.");
        } else {
            System.out.println("Usuário " + username + ": Suas reservas:");
            for (ConferenceRoom room : userReservations) {
                System.out.println(room.getName());
            }
        }
    }
}

class ManagerEmployee implements Employee {
    private String username;

    public ManagerEmployee(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void viewCurrentReservations() {
        RoomBookingSystem bookingSystem = RoomBookingSystem.getInstance();
        List<ConferenceRoom> userReservations = bookingSystem.getUserReservations(username);
        if (userReservations.isEmpty()) {
            System.out.println("Usuário " + username + ": Você não possui reservas.");
        } else {
            System.out.println("Usuário " + username + ": Suas reservas:");
            for (ConferenceRoom room : userReservations) {
                System.out.println(room.getName());
            }
        }
    }
}

class AdministratorEmployee implements Employee {
    private String username;

    public AdministratorEmployee(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void viewCurrentReservations() {
        RoomBookingSystem bookingSystem = RoomBookingSystem.getInstance();
        List<ConferenceRoom> userReservations = bookingSystem.getUserReservations(username);
        if (userReservations.isEmpty()) {
            System.out.println("Usuário " + username + ": Você não possui reservas.");
        } else {
            System.out.println("Usuário " + username + ": Suas reservas:");
            for (ConferenceRoom room : userReservations) {
                System.out.println(room.getName());
            }
        }
    }
}

class EmployeeFactory {
    public Employee createEmployee(String employeeType, String username) {
        switch (employeeType) {
            case "regular":
                return new RegularEmployee(username);
            case "manager":
                return new ManagerEmployee(username);
            case "administrator":
                return new AdministratorEmployee(username);
            default:
                throw new IllegalArgumentException("Funcionário inválido: " + employeeType);
        }
    }
}

class RoomBookingSystem {
    private static RoomBookingSystem instance;
    private List<ConferenceRoom> rooms;
    private Map<String, List<ConferenceRoom>> reservations;
    private List<RoomObserver> observers;

    private RoomBookingSystem() {
        rooms = new ArrayList<>();
        reservations = new HashMap<>();
        observers = new ArrayList<>();
    }

    public static RoomBookingSystem getInstance() {
        if (instance == null) {
            instance = new RoomBookingSystem();
        }
        return instance;
    }

    public void addConferenceRoom(ConferenceRoom room) {
        rooms.add(room);
    }

    public void removeConferenceRoom(ConferenceRoom room) {
        rooms.remove(room);
    }

    public List<ConferenceRoom> getConferenceRooms() {
        return Collections.unmodifiableList(rooms);
    }

    public void addObserver(RoomObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(RoomObserver observer) {
        observers.remove(observer);
    }

    public List<ConferenceRoom> searchRooms(int req) {
        ContextSearchStrategy context = new ContextSearchStrategy(req);
        return context.searchRooms(rooms);
    }
    
    private boolean isWithinBusinessHours(LocalDateTime startTime, LocalDateTime endTime) {
        LocalTime startHour = startTime.toLocalTime();
        LocalTime endHour = endTime.toLocalTime();
        return !startHour.isBefore(LocalTime.of(8, 0)) && !endHour.isAfter(LocalTime.of(18, 0));
    }

    private boolean hasTimeConflict(ConferenceRoom room, LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime reservedStartTime = room.getReservedStartTime();
        LocalDateTime reservedEndTime = room.getReservedEndTime();
        return (startTime.isAfter(reservedStartTime) && startTime.isBefore(reservedEndTime)) ||
                (endTime.isAfter(reservedStartTime) && endTime.isBefore(reservedEndTime)) ||
                (startTime.isBefore(reservedStartTime) && endTime.isAfter(reservedEndTime)) ||
                (startTime.equals(reservedStartTime) && endTime.equals(reservedEndTime));
    }

    private void notifyObservers(ConferenceRoom room) {
        for (RoomObserver observer : observers) {
            observer.update(room);
        }
    }

    private void failedMessage(String username, ConferenceRoom room) {
        System.out.println("Usuário " + username + ": Não foi possível fazer a reserva da sala " + room.getName() + ".");
    }

    private void successMessage(String username, ConferenceRoom room) {
        System.out.println("Usuário " + username + ": Reserva da sala " + room.getName() + " realizada com sucesso!");
    }

    public boolean reserveRoom(String username, ConferenceRoom room, LocalDateTime startTime, LocalDateTime endTime) {
        if (!isWithinBusinessHours(startTime, endTime) || !room.isAvailable()) {
            failedMessage(username, room);
            return false;
        }

        for (List<ConferenceRoom> userReservations : reservations.values()) {
            for (ConferenceRoom reservedRoom : userReservations) {
                if (reservedRoom.equals(room) && hasTimeConflict(reservedRoom, startTime, endTime)) {
                    failedMessage(username, room);
                    return false;
                }
            }
        }

        room.reserve(startTime, endTime);
        reservations.computeIfAbsent(username, k -> new ArrayList<>()).add(room);
        successMessage(username, room);
        
        return true;
    }

    public void cancelReservation(String username, ConferenceRoom room) {
        List<ConferenceRoom> userReservations = reservations.get(username);
        if (userReservations != null && userReservations.contains(room)) {
            room.cancelReservation();
            userReservations.remove(room);

            notifyObservers(room);
        }
    }

    public List<ConferenceRoom> getUserReservations(String username) {
        return reservations.getOrDefault(username, Collections.emptyList());
    }
}

class ContextSearchStrategy {
    private SearchStrategy strategy;

    public ContextSearchStrategy(int requiredCapacity) {
        switch (requiredCapacity) {
            case 0:
                strategy = new AvailabilitySearchStrategy();
                break;
            default:
                strategy = new CapacitySearchStrategy(requiredCapacity);
                break;
        }
    }

    public List<ConferenceRoom> searchRooms(List<ConferenceRoom> rooms) {
        return strategy.searchRooms(rooms);
    }
} 

class Main {
    public static void main(String[] args) {
        ConferenceRoom room1 = new ConferenceRoom("Sala 1", 10);
        ConferenceRoom room2 = new ConferenceRoom("Sala 2", 8);
        ConferenceRoom room3 = new ConferenceRoom("Sala 3", 12);
        ConferenceRoom room4 = new ConferenceRoom("Sala 4", 15);
        ConferenceRoom room5 = new ConferenceRoom("Sala 5", 20);

        RoomBookingSystem bookingSystem = RoomBookingSystem.getInstance();
        bookingSystem.addConferenceRoom(room1);
        bookingSystem.addConferenceRoom(room2);
        bookingSystem.addConferenceRoom(room3);
        bookingSystem.addConferenceRoom(room4);
        bookingSystem.addConferenceRoom(room5);

        EmployeeFactory employeeFactory = new EmployeeFactory();
        Employee regularEmployee = employeeFactory.createEmployee("regular", "John");
        Employee managerEmployee = employeeFactory.createEmployee("manager", "Alice");
        Employee administratorEmployee = employeeFactory.createEmployee("administrator", "Bob");

        RoomObserver regularUserObserver = new UserNotificationObserver(regularEmployee.getUsername());
        RoomObserver managerUserObserver = new UserNotificationObserver(managerEmployee.getUsername());
        bookingSystem.addObserver(regularUserObserver);
        bookingSystem.addObserver(managerUserObserver);

        //Salas Disponíveis	usando CapacitySearchStrategy
        List<ConferenceRoom> availableRooms = bookingSystem.searchRooms(10);
        System.out.println("Salas disponíveis para reuniões de 10 pessoas:");
        for (ConferenceRoom room : availableRooms) {
            System.out.println(room.getName());
        }

        //--------------------------------Reservando salas para o usuário regular-----------------------------------
        LocalDateTime startTime = LocalDateTime.of(2023, 6, 15, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 6, 15, 12, 0);

        boolean reservationSuccess = bookingSystem.reserveRoom(regularEmployee.getUsername(), room1, startTime, endTime);

        startTime = LocalDateTime.of(2023, 6, 15, 12, 0);
        endTime = LocalDateTime.of(2023, 6, 15, 15, 0);

        reservationSuccess = bookingSystem.reserveRoom(regularEmployee.getUsername(), room1, startTime, endTime);

        startTime = LocalDateTime.of(2023, 6, 17, 12, 0);
        endTime = LocalDateTime.of(2023, 6, 20, 15, 0);

        reservationSuccess = bookingSystem.reserveRoom(regularEmployee.getUsername(), room4, startTime, endTime);

        //------------------------------------------------------------------------------------------------------------
        //Salas Disponíveis	usando CapacitySearchStrategy
        availableRooms = bookingSystem.searchRooms(10);
        System.out.println("Salas disponíveis para reuniões de 10 pessoas após a reserva:");
        for (ConferenceRoom room : availableRooms) {
            System.out.println(room.getName());
        }

        //Cancelando reserva da sala 1
        bookingSystem.cancelReservation(regularEmployee.getUsername(), room1);

        //Salas Disponíveis usando AvailabilitySearchStrategy
        availableRooms = bookingSystem.searchRooms(0);
        System.out.println("Salas disponíveis para reuniões após o cancelamento:");
        for (ConferenceRoom room : availableRooms) {
            System.out.println(room.getName());
        }
    }
}