package atu.ie.eventservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event name is required")
    private String name;

    private String description;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Time is required")
    private LocalTime time;

    @Positive(message = "Price must be greater than 0")
    private double price;

    @Min(value = 0, message = "Available tickets cannot be negative")
    private int availableTickets;

    @NotNull(message = "Venue ID is required")
    private Long venueId;
}