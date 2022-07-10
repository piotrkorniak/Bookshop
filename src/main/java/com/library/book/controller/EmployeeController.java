package com.library.book.controller;

import com.library.book.dto.AddBookDto;
import com.library.book.dto.RentalResponse;
import com.library.book.service.interfaces.IEmployeeRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController {

    @Autowired
    private IEmployeeRentalService employeeService;

    @RequestMapping(value = "rental", method = RequestMethod.GET)
    public ResponseEntity<List<RentalResponse>> getEmployeeRentals() {
        return ResponseEntity.ok(employeeService.GetEmployeeRentals());
    }

    @RequestMapping(value = "rental/{id}", method = RequestMethod.GET)
    public ResponseEntity<RentalResponse> getEmployeeRental(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(employeeService.GetEmployeeRental(id));
    }

    @RequestMapping(value = "rental/{id}/active", method = RequestMethod.PATCH)
    public void activeEmployeeRental(@PathVariable("id") UUID id) {
        employeeService.activeEmployeeRental(id);
    }

    @RequestMapping(value = "rental/{id}/close", method = RequestMethod.PATCH)
    public void closeEmployeeRental(@PathVariable("id") UUID id) {
        employeeService.closeEmployeeRental(id);
    }

    @RequestMapping(value = "book/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable("id") UUID id) {
        employeeService.deleteBook(id);
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public void AddBook(@Valid @RequestBody AddBookDto bookDto) {
        employeeService.AddBook(bookDto);
    }
}
