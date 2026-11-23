public class TicketService {
    public Ticket buyTicket(String ticketNumber, Flight flight, Passenger passenger, int seatNumber){
        if(flight == null){
            throw new IllegalArgumentException("Рейс не должен быть пустым");
        }
        Seat seat = flight.findSeatByNumber(seatNumber);
        if(seat == null){
            throw new IllegalArgumentException("Место не найдено!");
        }
        Ticket ticket = new Ticket(ticketNumber, flight, passenger, seat);
        seat.book();

        return ticket;
    }
    
}
