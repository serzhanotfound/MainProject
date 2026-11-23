public class Passenger {
    private String firstName;
    private String lastName;

    public Passenger(String firstName, String lastName){
        if(firstName == null || firstName.isBlank()){
            throw new IllegalArgumentException("Имя не должна быть пустой!");
        }
        
        if(lastName == null || lastName.isBlank()){
            throw new IllegalArgumentException("Фамилия не должна быть пустой!");
        }

        this.firstName = firstName.strip();
        this.lastName = lastName.strip();
    }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
    
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }


}
