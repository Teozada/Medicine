package com.example.medicine.dto;

import com.example.medicine.model.Medicine;
import com.example.medicine.model.Reactions;
import com.sun.istack.NotNull;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class MedicineDto {

    public static class CreateOrUpdate{
        @NotNull
        public String name;
        @NotNull
        public int expirationYear;
        @NotNull
        public int expirationMonth;
        @NotNull
        public int expirationDay;
        @NotNull
        public String phone;
        @NotNull
        public Double price;
        @NotNull
        public int amount;
        @NotNull
        public String producer;
        public Medicine toModel() {
            return new Medicine(
                    name,
                    LocalDate.of(
                            expirationYear,
                            expirationMonth,
                            expirationDay),
                    phone,
                    price,
                    amount,
                    producer);
        }
    }
    public static class AddRemoveReactions {
        public List<Long> reactionsIds;
    }
}
