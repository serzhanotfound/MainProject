import java.math.BigDecimal;

public class Ticket{
    private String ticketNumber;
    private Flight flight;
    private Passenger passenger;
    private Seat seat;
    private TicketStatus status = TicketStatus.ACTIVE;
    private BigDecimal priceAtPurchase;

    public Ticket(String ticketNumber, Flight flight, Passenger passenger, Seat seat){
        if(ticketNumber == null || ticketNumber.isBlank()){
            throw new IllegalArgumentException("Номер билета не должно быть пустым");
        }
        if(flight == null){
            throw new IllegalArgumentException("Рейс не должно быть пустым");
        }
        if(passenger == null){
            throw new IllegalArgumentException("Пассажир должен быть указан");
        }
        if(seat == null){
            throw new IllegalArgumentException("Место должно быть указан");
        }
        if(flight.findSeatByNumber(seat.getNumber()) != seat){
            throw new IllegalArgumentException("Место не принадлежит указанному рейсу!");
        }

        this.ticketNumber = ticketNumber.strip();
        this.flight = flight;
        this.passenger = passenger;
        this.seat = seat;
        this.priceAtPurchase = flight.getPrice();
    }

    @Override
    public String toString(){
        return "Билет: " + ticketNumber + 
        " | Рейс: " + flight.getFlightNumber() + 
        " | Пассажир: " + passenger + 
        " | Место: " + seat.getNumber() +
        " | Цена: " + priceAtPurchase +
        " | Статус: " + status;
        
    }

    public void cancel(){
        if(status == TicketStatus.CANCELLED){
            throw new IllegalStateException("Билет уже отменен");
        }
        seat.release();
        status = TicketStatus.CANCELLED;
    }

    public String getTicketNumber(){
        return ticketNumber;
    }

    public Flight getFlight(){
        return flight;
    }

    public Passenger getPassenger(){
        return passenger;
    }

    public Seat getSeat(){
        return seat;
    }

    public TicketStatus getStatus(){
        return status;
    }

    public BigDecimal getPriceAtPurchase(){
        return priceAtPurchase;
    }
}