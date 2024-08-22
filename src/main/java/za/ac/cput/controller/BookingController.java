package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.facade.BookingGeneralStaff;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.service.BookingService;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;
    private BookingGeneralStaff bookingGeneralStaff;

    @Autowired
    public BookingController(BookingService bookingService,  BookingGeneralStaff bookingGeneralStaff) {
        this.bookingService = bookingService;
        this.bookingGeneralStaff = bookingGeneralStaff;
    }

    @PostMapping("/save")
    public ResponseEntity<Booking> save(@RequestBody Booking obj){
        Booking booking = BookingFactory.buildBooking(Time.valueOf(obj.getTime()).toLocalTime(), Date.valueOf(obj.getDate()).toLocalDate(),obj.getTableNo(),obj.getSectionNo(),obj.getStatus());
        if(booking !=null){
                return ResponseEntity.status(HttpStatus.OK).body(bookingService.save(booking));
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Booking> read(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.read(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Booking> update(@RequestBody Booking obj){
        Booking booking = BookingFactory.buildBooking(obj.getBookingId(),Time.valueOf(obj.getTime()).toLocalTime(), Date.valueOf(obj.getDate()).toLocalDate(),obj.getTableNo(),obj.getSectionNo(),obj.getStatus(),obj.getBookedBy());
        if(booking !=null){
            if(bookingGeneralStaff.canSave(booking) != null){
                return ResponseEntity.status(HttpStatus.OK).body(bookingService.update(booking));
            }
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.delete(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Booking>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAll());
    }
}