package DataAccesLayer;

import java.io.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import BusinessLogicLayer.*;
import PresentationLayer.Client;

// This class is going to be for serialization...

public class FileHandeler {
   
    private BookingList readingData;
    private BookingsCreated writingData;
    File tempfile = new File("bookings.txt");
    private Scanner scan;
    int id = 1;

    public FileHandeler() {
        
    }

    public void writeBooking(BookingsCreated booking) throws FileNotFoundException{   
        
        scan = new Scanner(tempfile);    

        


    
    }

    public BookingList readBooking() throws FileNotFoundException{

        scan = new Scanner(tempfile);
        String line = "";
        List<BookingsCreated> temporaryList = new ArrayList<BookingsCreated>();    
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            List<String> food = new ArrayList<String>();
            List<String> decor = new ArrayList<String>();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            
            for (String ln : line.split("#")) {
                food.add(ln);
            }

            for (String ln : line.split("&")) {
                decor.add(ln);
            }

            Events event = new Events(line.split(",")[3],now,line.split(",")[5],line.split(",")[6],line.split(",")[7],Integer.parseInt(line.split(",")[8]),Integer.parseInt(line.split(",")[9]),food,decor);
            
            // public Events(String event,LocalDateTime date,String VenueName,String VenueAddress,String VenueContact,int Adults,int Children,List<String> food,List<String> decorations)
            
            temporaryList.add(new BookingsCreated(id,new ClientClass(line.split(",")[0],line.split(",")[1],line.split(",")[2]),event));

            id++;
        }

        BookingList book = new BookingList(temporaryList);

        return book;
    }
}
