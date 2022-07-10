package com.library.book.controller;

import com.library.auth.entity.User;
import com.library.book.dto.RentalResponse;
import com.library.book.service.interfaces.IUserRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER')")
public class UserController {

    @Autowired
    private IUserRentalService userService;

    @RequestMapping(value = "rental", method = RequestMethod.GET)
    public ResponseEntity<List<RentalResponse>> getUserRentals() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(userService.GetUserRentals(user));
    }

    @RequestMapping(value = "rental/{id}", method = RequestMethod.GET)
    public ResponseEntity<RentalResponse> getUserRental(@PathVariable("id") UUID id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.GetUserRental(user, id));
    }

    @RequestMapping(value = "rental/{id}", method = RequestMethod.POST)
    public void createUserRental(@PathVariable("id") UUID bookId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.CreateUserRental(user, bookId);
    }

    @RequestMapping(value = "rental/{id}/close", method = RequestMethod.PATCH)
    public void closeUserRental(@PathVariable("id") UUID id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.CloseUserRental(user, id);
    }
}
