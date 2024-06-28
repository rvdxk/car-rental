package io.github.rvdxk.carrentalspringproject.dto;

import io.github.rvdxk.carrentalspringproject.constant.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PaymentDto {

    private Long id;

    @NotNull(message = "Please, enter total cost.")
    private double totalCost;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please, select the payment date.")
    private LocalDate paymentDate;

    @NotNull(message = "Please, enter the payment method. (cash/card)")
    private PaymentMethod paymentMethod;

}
