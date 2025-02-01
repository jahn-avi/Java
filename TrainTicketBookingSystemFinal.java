package Booking;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainTicketBookingSystemFinal extends JFrame implements ActionListener {
    JLabel lblTitle, lblDeparture, lblArrival, lblDate, lblPassengers, lblClass, lblTotal;
    JComboBox<String> cbDeparture, cbArrival, cbClass;
    JTextField tfDate, tfPassengers;
    JButton btnBookTicket;
    JTextArea taConfirmation;
    double ticketPrice;

    public TrainTicketBookingSystemFinal() {
        setTitle("Train Ticket Booking System");
        setSize(401, 364);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitle = new JLabel("Train Ticket Booking System");
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 14));
        lblDeparture = new JLabel("Departure Station:");
        lblArrival = new JLabel("Arrival Station:");
        lblDate = new JLabel("Date (DD/MM/YYYY):");
        lblPassengers = new JLabel("Number of Passengers:");
        lblClass = new JLabel("Class:");
        lblTotal = new JLabel("Total Price: ₹0.00");

        String[] stations = {"Select","Katra","Mussorie","Faridabad","New Delhi","Gaziabad","Beawar","Jaipur","Mirzapur","Patna","Kolkata","Ranchi","Indore","Ahmedabad","Mumbai","Vasco-da-Gama(Goa)","KSR Bengaluru","Hyderabad","Amravati","Puducherry","Chennai","Kochi"};
        cbDeparture = new JComboBox<>(stations);
        cbArrival = new JComboBox<>(stations);

        String[] classes = {"AC-I","AC-II","AC-III","SL","GEN"};
        cbClass = new JComboBox<>(classes);

        tfDate = new JTextField(10);
        tfPassengers = new JTextField(10);
        tfPassengers.setText("0");

        btnBookTicket = new JButton("Book Ticket");
        btnBookTicket.setBackground(new Color(135, 206, 250));
        btnBookTicket.setForeground(new Color(0, 0, 0));
        btnBookTicket.addActionListener(this);
        SpringLayout springLayout = new SpringLayout();
        springLayout.putConstraint(SpringLayout.NORTH, lblDeparture, 22, SpringLayout.SOUTH, lblTitle);
        springLayout.putConstraint(SpringLayout.NORTH, lblTitle, 10, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblTitle, 0, SpringLayout.WEST, lblDeparture);
        springLayout.putConstraint(SpringLayout.NORTH, lblDate, 16, SpringLayout.SOUTH, lblArrival);
        springLayout.putConstraint(SpringLayout.WEST, lblArrival, 5, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblDeparture, 5, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, tfPassengers, 68, SpringLayout.EAST, lblPassengers);
        springLayout.putConstraint(SpringLayout.NORTH, lblArrival, 9, SpringLayout.SOUTH, lblDeparture);
        springLayout.putConstraint(SpringLayout.NORTH, cbArrival, -3, SpringLayout.NORTH, lblArrival);
        springLayout.putConstraint(SpringLayout.EAST, cbArrival, 0, SpringLayout.EAST, cbDeparture);
        springLayout.putConstraint(SpringLayout.NORTH, cbDeparture, -3, SpringLayout.NORTH, lblDeparture);
        springLayout.putConstraint(SpringLayout.WEST, cbDeparture, 0, SpringLayout.WEST, tfDate);
        springLayout.putConstraint(SpringLayout.WEST, lblPassengers, 5, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblDate, 5, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, tfPassengers, 12, SpringLayout.EAST, lblPassengers);
        springLayout.putConstraint(SpringLayout.WEST, tfDate, 24, SpringLayout.EAST, lblDate);
        springLayout.putConstraint(SpringLayout.WEST, btnBookTicket, 42, SpringLayout.EAST, cbClass);
        springLayout.putConstraint(SpringLayout.WEST, lblTotal, 8, SpringLayout.EAST, btnBookTicket);
        springLayout.putConstraint(SpringLayout.NORTH, lblTotal, 0, SpringLayout.NORTH, lblClass);
        springLayout.putConstraint(SpringLayout.NORTH, btnBookTicket, -4, SpringLayout.NORTH, lblClass);
        springLayout.putConstraint(SpringLayout.NORTH, cbClass, -3, SpringLayout.NORTH, lblClass);
        springLayout.putConstraint(SpringLayout.WEST, cbClass, 6, SpringLayout.EAST, lblClass);
        springLayout.putConstraint(SpringLayout.NORTH, lblClass, 19, SpringLayout.SOUTH, lblPassengers);
        springLayout.putConstraint(SpringLayout.WEST, lblClass, 10, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, lblPassengers, 19, SpringLayout.SOUTH, lblDate);
        springLayout.putConstraint(SpringLayout.NORTH, tfDate, -3, SpringLayout.NORTH, lblDate);
        springLayout.putConstraint(SpringLayout.NORTH, tfPassengers, -3, SpringLayout.NORTH, lblPassengers);
        getContentPane().setLayout(springLayout);

        getContentPane().add(lblTitle);
        getContentPane().add(lblDeparture);
        getContentPane().add(cbDeparture);
        getContentPane().add(lblArrival);
        getContentPane().add(cbArrival);
        getContentPane().add(lblDate);
        getContentPane().add(tfDate);
        getContentPane().add(lblPassengers);
        getContentPane().add(tfPassengers);
        getContentPane().add(lblClass);
        getContentPane().add(cbClass);
        getContentPane().add(btnBookTicket);
        getContentPane().add(lblTotal);
        JScrollPane scrollPane = new JScrollPane();
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 5, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 1146, SpringLayout.WEST, getContentPane());
        getContentPane().add(scrollPane);
        
                taConfirmation = new JTextArea(5, 30);
                springLayout.putConstraint(SpringLayout.NORTH, taConfirmation, 9, SpringLayout.SOUTH, btnBookTicket);
                springLayout.putConstraint(SpringLayout.WEST, taConfirmation, 32, SpringLayout.WEST, getContentPane());
                getContentPane().add(taConfirmation);
                taConfirmation.setEditable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBookTicket) {
            String departure = (String) cbDeparture.getSelectedItem();
            String arrival = (String) cbArrival.getSelectedItem();
            String date = tfDate.getText();
            int passengers = Integer.parseInt(tfPassengers.getText());
            String trainClass = (String) cbClass.getSelectedItem();
            
            if (departure.equals(arrival)) {
                JOptionPane.showMessageDialog(this, 
                    "Departure and Arrival stations cannot be the same!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return; // Exit the method to prevent further execution
            }

            if (!isValidDate(date)) {
                JOptionPane.showMessageDialog(this,"Enter a valid date!","Error",
                                JOptionPane.ERROR_MESSAGE);
                return; // Exit the method
            }
            
            if (trainClass.equals("AC-I")) {
                ticketPrice = 1000.0;
            }
            else if(trainClass.equals("AC-II")) {
                ticketPrice = 800.0;
            }
            else if(trainClass.equals("AC-III")) {
            	ticketPrice = 500.0;
            }
            else if(trainClass.equals("SL")) {
            	ticketPrice = 300.0;
            }
            else {
            	ticketPrice = 150.0;
            }

            double totalPrice = ticketPrice * passengers;

            lblTotal.setText("Total Price: ₹" + totalPrice);

            if(totalPrice <= 0) {
            	JOptionPane.showMessageDialog(this, 
                        "Enter the no. of Passengers!!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
            }
            
            String confirmationMessage = "Ticket Details:\n" +
                    "Departure: " + departure + "\n" +
                    "Arrival: " + arrival + "\n" +
                    "Date: " + date + "\n" +
                    "Class: " + trainClass + "\n" +
                    "Passengers: " + passengers + "\n" +
                    "Total Price: ₹" + totalPrice;
            taConfirmation.setText(confirmationMessage);
        }
    }

    private boolean isValidDate(String date){
        try {
            // Define the date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(date, formatter); // Try to parse the date
            return true; // Parsing successful, date is valid
        }
        catch (DateTimeParseException e) {
        	return false;
        }
    }
    
public static void main(String[] args) {
    new TrainTicketBookingSystemFinal();
}

}
