public class Seat {
    private int number;
    private boolean booked;

    public Seat(int number){
        if(number <= 0){
            throw new IllegalArgumentException("Номер должен быть больше нуля");
        }
        this.number = number;
        this.booked = false;
    }

    public int getNumber(){
        return number;
    }
    public boolean isBooked(){
        return booked;
    }

    public void book(){
        if(booked){
            throw new IllegalStateException("Уже занято");
        }
            booked = true;
    }

    public void release(){
        if(booked == false){
            throw new IllegalStateException("Место уже свободно");
        }
        booked = false;
    }

    @Override 
    public String toString(){
        if(!booked){
        return "Место " + number + ": Свободно";
    }else{
        return "Место " + number + ": Занято";
    }
    }
}

