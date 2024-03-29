import java.io.*;
import java.io.IOException;
public class Ticket {
    private String row;
    private int seat;
    private double price;
    private Person person;

    public void save(){
        String filename= getRow()+getSeat();
        try{
            File file=new File(filename+".txt");
            boolean file_created=file.createNewFile();
            if(file_created){
                System.out.println("___"+file.getName()+" for a ticket sold in row "+getRow()+" seat "+getSeat()+" ___");
                System.out.println();
            }
            else if (file.delete()){
                System.out.println("🔄️Exist file deleted & new file created🔄️");
                System.out.println("___"+file.getName()+" for a ticket sold in row "+getRow()+" seat "+getSeat()+" ___");

            }
        }catch (IOException ex){
            System.out.println("<Error occurred>");
        }
        try{
            FileWriter myWriter=new FileWriter(filename+".txt");
            myWriter.write("Ticket Information>>>>>\nRow : "+ row +"\nSeat number: "+seat+"\nPrice : £ "+price+"\nPersonal Information>>>>>\nName : "+person.getName()+"\nSurname :"+person.getSurname()+"\nEmail : "+person.getEmail()+"\n\n");
            myWriter.close();

            System.out.println("<<<<Ticket information saved to "+filename+" file>>>>");
        }
        catch (IOException ex){
            System.out.println("<Error occurred>");
        }
    }
    public Ticket(String row, int seat, double price, Person person){
        this.row =row;
        this.seat=seat;
        this.price=price;
        this.person=person;
    }

    public String getRow(){
        return row;
    }
    public void setRow(String row){
        this.row = row;
    }
    public int getSeat(){
        return seat;
    }
    public void setSeat(int seat){
        this.seat=seat;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public Person getPerson(){
        return person;
    }
    public void setPerson(Person person) {
        this.person=person;
    }
    public void Ticket_details(){
        System.out.println();
        System.out.println("Row is "+ row);
        System.out.println("Seat is "+seat);
        System.out.println("Price is "+price);
        System.out.println();
        System.out.println("<<<<Personal Information>>>>");
        System.out.println();
        person.Person_details();
    }
}
