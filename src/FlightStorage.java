import java.util.ArrayList;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class FlightStorage {
    public static void save(Path file, ArrayList<Flight> flights)throws IOException {
        ArrayList<String> data = new ArrayList<>();
        try{
        for(Flight flight : flights) {
            data.add(flight.getFlightNumber());
            data.add(flight.getDepartureCity());
            data.add(flight.getArrivalCity());
            data.add(String.valueOf(flight.getTotalSeats()));
            data.add(flight.getDepartureTime().toString());
            data.add(flight.getPrice().toPlainString());
        }
        Files.write(file, data, StandardCharsets.UTF_8);
    } catch(IOException e){
        System.out.println("Ошибка записи: " + e.getMessage());
    }
    }

    public static ArrayList<Flight> load(Path file) throws IOException{
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        ArrayList<Flight> loadedFlights = new ArrayList<>();
        
        if(lines.size() % 6 != 0) {
            throw new IllegalArgumentException("Неверный формат данных");
        }

        for(int i = 0; i < lines.size(); i += 6){
            
            String flightNumber = lines.get(i);
            String departureCity = lines.get(i+1);
            String arrivalCity = lines.get(i+2);
            int totalSeats = Integer.parseInt(lines.get(i+3));
            LocalDateTime localDateTime = LocalDateTime.parse(lines.get(i+4));
            BigDecimal price = new BigDecimal(lines.get(i+5));

            Flight newFlight = new Flight(flightNumber, departureCity, arrivalCity, totalSeats, localDateTime, price);
            loadedFlights.add(newFlight);
        }
        return loadedFlights;
    }
}
