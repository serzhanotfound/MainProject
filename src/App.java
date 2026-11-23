import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        ArrayList<Flight> flights = new ArrayList<>();
        ArrayList<Ticket> tickets = new ArrayList<>();

        Flight flight1 = new Flight("DEMO101", "Алматы", "Астана", 20);
        Flight flight2 = new Flight("DEM102", "Алматы", "Актау", 25);
        Flight flight3 = new Flight("DEM103", "Алматы", "Берлин", 30);
        Flight flight4 = new Flight("DEM104", "Тараз", "Актау", 20);
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        Passenger passenger = new Passenger("Алия", "Омарова");

        TicketService service = new TicketService();
        int nextTicketNumber = 1;

        Ticket first = service.buyTicket("T1", flight1, passenger, 1);
        first.cancel();
        Ticket second = service.buyTicket("T2", flight1, passenger, 1);
        // first.cancel();
        System.out.println(second);
        System.out.println(first);

        System.out.println("=== Авиасистема ===");

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        String choice;

        do {
            System.out.println(
                    "1. Показать все рейсы \n2. Найти рейсы по городу прибытия " + 
                    "\n3. Купить билеты \n4. Показать купленные билеты " + 
                    "\n5. Отменить Билет \n6. Показать свободные места " + 
                    "\n7. Добавить рейсы \n0. Выход");
            System.out.println("Выберите действие: ");
            choice = scanner.nextLine().strip();

            switch (choice) {
                case "1" -> {
                    for (Flight flight : flights) { // «для каждого рейса flight из списка flights выполнить тело цикла»
                        System.out.println(flight);

                    }
                    System.out.println("Всего рейсов: " + flights.size());
                }
                case "2" -> {
                    String destination = readDestination(scanner);
                    printFlightByDestination(flights, destination);
                }
                case "3" -> {
                    try {
                        System.out.println("Номер рейса: ");
                        String flightNum = scanner.nextLine().strip();
                        Flight newFlight = findFlightByNumber(flights, flightNum);
                        if (newFlight == null) {
                            throw new IllegalArgumentException("Рейс не найден!");
                        }

                        System.out.println("Имя: ");
                        String firstName = scanner.nextLine().strip();
                        System.out.println("Фамилия: ");
                        String lastName = scanner.nextLine().strip();

                        Passenger newPassenger = new Passenger(firstName, lastName);

                        System.out.println("Введите номер места: ");
                        int seatNumber = Integer.parseInt(scanner.nextLine().strip());

                        String ticketNumber = "T" + nextTicketNumber;

                        Ticket newTicket = service.buyTicket(ticketNumber, newFlight, newPassenger, seatNumber);

                        tickets.add(newTicket);
                        nextTicketNumber++;

                        System.out.println(newTicket);

                    } catch (NumberFormatException e) {
                        System.out.println("Номер Места нужно вводить целым числом.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка данных: " + e.getMessage());
                    } catch (IllegalStateException e) {
                        System.out.println("Покупка невозможна: " + e.getMessage());
                    }
                }
                case "4" -> {
                    if (tickets.isEmpty()) {
                        System.out.println("Купленных билетов пока нет");
                    } else {
                        System.out.println("Количество билетов - " + tickets.size() + ": ");
                        for (Ticket ticket : tickets) {
                            System.out.println(ticket);
                        }
                    }
                }
                case "5" -> {
                    try {
                        System.out.println("Введите номер билета: ");
                        String ticketNum = scanner.nextLine().strip();
                        Ticket selectedTicket = findTicketByNumber(tickets, ticketNum);
                        if (selectedTicket == null) {
                            throw new IllegalArgumentException("Билет не найден");
                        }

                        selectedTicket.cancel();

                        System.out.println("Билет успешно отменён!");
                        System.out.println(selectedTicket);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка данных: " + e.getMessage());
                    } catch (IllegalStateException e) {
                        System.out.println("Отмена невозможна: " + e.getMessage());
                    }
                }
                case "6" -> {
                    try{
                    System.out.println("Введите номер рейса: ");
                    String flightNum = scanner.nextLine().strip();
                    Flight selectedflight = findFlightByNumber(flights, flightNum);
                    if(selectedflight == null){
                        throw new IllegalArgumentException("Рейс не найден");
                    }

                    ArrayList<Seat> availableSeats = selectedflight.findAvailableSeats();
                    if(availableSeats.isEmpty()){
                        System.out.println("Нету свободных мест");
                    }else{
                        System.out.println("Свободных мест: " + availableSeats.size());
                        
                        for(Seat seat : availableSeats){
                            System.out.println(seat);
                        }    
                    }
                    
                    }catch(IllegalArgumentException e){
                        System.out.println("Ошибка данных: " + e.getMessage());
                    }
                }
                case "7" -> {
                    try{
                        System.out.println("Введите номер рейса: ");
                        String flightNumber = scanner.nextLine().strip();
                        if(findFlightByNumber(flights, flightNumber) != null){
                            throw new IllegalArgumentException("Рейс с таким номером уже существует");
                        }    
                        System.out.println("Введите город отправления: ");
                        String departureCity = scanner.nextLine().strip();
                        System.out.println("Введите город прибытия: ");
                        String arrivalCity = scanner.nextLine().strip();
                        System.out.println("Введите количество мест: ");
                        int totalSeats = Integer.parseInt(scanner.nextLine().strip());
                        
                        Flight newFlight = new Flight(flightNumber, departureCity, arrivalCity, totalSeats);
                        flights.add(newFlight);

                        System.out.println("Рейс успешно создан");
                        System.out.println(newFlight);
                    } catch(NumberFormatException e){
                        System.out.println("Количество мест нужно вводить целым числом.");
                    } catch(IllegalArgumentException e){
                        System.out.println("Ошибка данных: " + e.getMessage());
                    }
                }
                case "0" -> {
                    System.out.println("Завершено!");
                }
                default -> {
                    System.out.println("Неизвестная команда. Выберите 1, 2 или 0.");
                }
            }
        } while (!choice.equals("0"));

        scanner.close();
    }

    private static void printFlightByDestination(
            ArrayList<Flight> flights,
            String destination) {

        boolean found = false;
        for (Flight flight : flights) {
            if (flight.getArrivalCity().equalsIgnoreCase(destination)) {
                System.out.println(flight);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Рейсы на " + destination + " не найдено.");
        }
    }

    private static String readDestination(Scanner scanner) {

        String destination;

        do {

            System.out.print("Введите город прибытия: ");
            destination = scanner.nextLine().strip(); // nextLine() - ждет ввода и нажатия Enter, затем возвращает
                                                      // введенную строку.
                                                      // strip() - удаляет пробелы по краям

            if (destination.isBlank()) {
                System.out.println("Пустой запрос, пожалуйста введите название города!");
            }
        } while (destination.isBlank());
        return destination;
    }

    private static Flight findFlightByNumber(ArrayList<Flight> flights, String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    private static Ticket findTicketByNumber(ArrayList<Ticket> tickets, String ticketNumber) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketNumber().equalsIgnoreCase(ticketNumber)) {
                return ticket;
            }
        }
        return null;
    }
}
