package io.github.rvdxk.carrentalspringproject.dto;

import io.github.rvdxk.carrentalspringproject.constant.PaymentMethod;
import io.github.rvdxk.carrentalspringproject.entity.RentalInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PaymentDto {

    private Long id;

    @NotNull(message = "Please, enter rental info ID.")
    private RentalInfo rentalInfo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please, select the payment date.")
    private Date paymentDate;

    @NotNull(message = "Please, enter the payment method. (cash/card)")
    private PaymentMethod paymentMethod;

}
