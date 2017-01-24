/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author timurozkul
 */
public class Clients {
    
    private int id;
    private String firstName;
    private String lastName;
    private String checkIn;
    private String checkOut;
    private int pax;
    private String month;
    private String reservationDate;
    private String country;
    private String bookingType;
    private String GroupName;

    public Clients(){}
    
    public Clients(String firstName, String lastName, String checkIn, String checkOut, int pax, String month, String reservationDate, String country, String bookingType, String GroupName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.pax = pax;
        this.month = month;
        this.reservationDate = reservationDate;
        this.country = country;
        this.bookingType = bookingType;
        this.GroupName = GroupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }
    
    
}
