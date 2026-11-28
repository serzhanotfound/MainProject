import java.time.LocalDateTime;
import java.math.BigDecimal;


public class FlightService{
    public Flight createFlight(
        String flightNumber,
        String departureCity,
        String arrivalCity,
        int totalSeats,
        LocalDateTime departureDateTime,
        BigDecimal price
    ){
        if (departureDateTime == null) {
            throw new IllegalArgumentException(
                    "Дата и время отправления должны быть указаны!");
        }

        if (!departureDateTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "Дата и время вылета должны быть в будущем!");
        }

        return new Flight(
                flightNumber, departureCity, arrivalCity,
                totalSeats, departureDateTime, price);
        }
    }