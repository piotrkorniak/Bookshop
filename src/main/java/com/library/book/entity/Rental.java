package com.library.book.entity;

import com.library.auth.entity.User;
import com.library.book.enums.RentalStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

import static com.library.book.enums.RentalStatus.Active;
import static com.library.book.enums.RentalStatus.Pending;

@Entity
public class Rental {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    private RentalStatus status;

    private Date startDate;

    private Date endDate;

    public UUID getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Rental() {
        this.id = UUID.randomUUID();
        this.status = Pending;
        this.startDate = new Date(System.currentTimeMillis());
        this.endDate = null;
    }

    public void CloseRental() {
        endDate = new Date(System.currentTimeMillis());
        switch (status) {
            case Active:
                status = RentalStatus.Completed;
                break;
            case Pending:
                status = RentalStatus.Canceled;
                break;
        }
    }

    public void activateRental() {
        status = Active;
    }

    public boolean isPending() {
        return status == Pending;
    }

    public boolean isCanceled() {
        return status == RentalStatus.Canceled;
    }

    public boolean isCompleted() {
        return status == RentalStatus.Completed;
    }
}
