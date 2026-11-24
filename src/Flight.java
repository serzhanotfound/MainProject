import java.util.ArrayList;
import java.time.LocalDateTime;

public class Flight{
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private int totalSeats;
    private ArrayList<Seat> seats = new ArrayList<>();
    private LocalDateTime departureDateTime;

    public Flight(String flightNumber, String departureCity, String arrivalCity, int totalSeats, LocalDateTime departureDateTime){
    
    if(flightNumber == null || flightNumber.isBlank()){
        throw new IllegalArgumentException("Номер рейса не должно быть пустым!");
    }
    if(departureCity == null || departureCity.isBlank()){
        throw new IllegalArgumentException("Город отправки не должно быть пустым!");
    } 
    if(arrivalCity == null || arrivalCity.isBlank()){
        throw new IllegalArgumentException("Город прибытия не должно быть пустым!");
    }
    if(departureCity.equalsIgnoreCase(arrivalCity.strip())){
        throw new IllegalArgumentException("Город отправки не должно совпадать с городом прибытия!");
    }
    if(totalSeats <= 0){
        throw new IllegalArgumentException("Количетсво мест должно быть больше нуля!");
    }
    if(departureDateTime == null){
        throw new IllegalArgumentException("Дата и время отправления не должно быть пустым!");
    }
    if (!departureDateTime.isAfter(LocalDateTime.now())) {
    throw new IllegalArgumentException(
            "Дата и время вылета должны быть в будущем!");
    }

    for(int number = 1; number <= totalSeats; number++){
        seats.add(new Seat(number));
    }

        this.flightNumber = flightNumber.strip();
        this.departureCity = departureCity.strip();
        this.arrivalCity = arrivalCity.strip();
        this.totalSeats = totalSeats;
        this.departureDateTime = departureDateTime;
    }

    @Override
    public String toString(){
        return "Рейс: " + flightNumber
                + ": " + departureCity
                + " -> " + arrivalCity
                + ", мест: " + totalSeats
                + ", вылет: " + departureDateTime;
            }

    public String getFlightNumber(){
        return flightNumber;
    }
    
    public String getDepartureCity(){
        return departureCity;
    }

    public String getArrivalCity(){
        return arrivalCity;    
    }

    public int getTotalSeats(){
        return totalSeats;
    }

    public LocalDateTime getDepartureTime(){
        return departureDateTime;
    }

    public Seat findSeatByNumber(int number){
        for(Seat seat : seats){
            if(seat.getNumber() == number){
                return seat;
            }
        
        }
        return null;
    }

    public ArrayList<Seat> findAvailableSeats(){
        ArrayList<Seat> availableSeats = new ArrayList<>();
        for(Seat seat : seats ){
            if(!seat.isBooked()){
                availableSeats.add(seat);
            }
        }

        return availableSeats;
    }
}
