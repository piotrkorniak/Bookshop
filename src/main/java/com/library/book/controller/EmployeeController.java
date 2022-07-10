package com.library.book.controller;

import com.library.book.dto.AddBookDto;
import com.library.book.service.interfaces.IEmployeeRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController {

    @Autowired
    private IEmployeeRentalService employeeService;
//    [HttpGet("rental")]
//    public async Task<ActionResult<IEnumerable<RentalResponse>>> GetAllRentals(CancellationToken cancellationToken)
//    {
//        var getAllRentalsQuery = new GetAllRentalsQuery();
//        var allRentals = await _mediator.Send(getAllRentalsQuery, cancellationToken);
//        return Ok(allRentals);
//    }
//
//
//        [HttpGet("rental/{id:int}")]
//    public async Task<ActionResult<RentalResponse>> GetRental([FromRoute] int id,
//                                                              CancellationToken cancellationToken)
//    {
//        var getRentalQuery = new GetRentalQuery
//        {
//            RentalId = id
//        };
//        var rental = await _mediator.Send(getRentalQuery, cancellationToken);
//        return rental;
//    }
//
//        [HttpPatch("rental/{id:int}/active")]
//    public async Task<ActionResult> ActiveRental([FromRoute] int id, CancellationToken cancellationToken)
//    {
//        var activeRentalCommand = new ActiveRentalCommand
//        {
//            RentalId = id
//        };
//        await _mediator.Send(activeRentalCommand, cancellationToken);
//        return Ok();
//    }
//
//        [HttpPatch("rental/{id:int}/close")]
//    public async Task<ActionResult> CloseRental([FromRoute] int id,
//                                                CancellationToken cancellationToken)
//    {
//        var closeRentalCommand = new CloseRentalCommand
//        {
//            RentalId = id
//        };
//        await _mediator.Send(closeRentalCommand, cancellationToken);
//        return Ok();
//    }

    @RequestMapping(value = "book/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable("id") UUID id) {
        employeeService.deleteBook(id);
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public void AddBook(@Valid @RequestBody AddBookDto bookDto) {
        employeeService.AddBook(bookDto);
    }
}
