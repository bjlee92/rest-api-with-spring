package me.whiteship.demoinfleanrestapi.events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDto eventDto, Errors errors) {
        int basePrice = eventDto.getBasePrice();
        int maxPrice = eventDto.getMaxPrice();

        if (basePrice > maxPrice && maxPrice != 0) {
            errors.rejectValue("basePrice", "wrongValue", "BasePrice is wrong.");
            errors.rejectValue("maxPrice", "wrongValue", "MaxPrice is wrong.");
        }

        LocalDateTime beginEventDateTime = eventDto.getBeginEventDateTime();
        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        LocalDateTime beginEnrollmentDateTime = eventDto.getBeginEnrollmentDateTime();
        LocalDateTime closeEnrollmentDateTime = eventDto.getCloseEnrollmentDateTime();

        if (endEventDateTime.isBefore(beginEventDateTime) ||
        endEventDateTime.isBefore(closeEnrollmentDateTime) ||
        endEventDateTime.isBefore(beginEnrollmentDateTime)) {
            errors.rejectValue("endEventDateTime", "wrongValue", "endEventDateTime is wrong.");
        }

        if (beginEventDateTime.isAfter(endEventDateTime) ||
        beginEventDateTime.isBefore(beginEnrollmentDateTime)) {
            errors.rejectValue("beginEventDateTime", "wrongValue", "beginEventDateTime is wrong.");
        }

        if (closeEnrollmentDateTime.isBefore(beginEnrollmentDateTime) ||
        closeEnrollmentDateTime.isAfter(endEventDateTime)) {
            errors.rejectValue("closeEnrollmentDateTime", "wrongValue", "closeEnrollmentDateTime is wrong.");
        }

        if (beginEnrollmentDateTime.isAfter(closeEnrollmentDateTime) ||
        beginEnrollmentDateTime.isAfter(endEventDateTime) ||
        beginEnrollmentDateTime.isAfter(beginEventDateTime)) {
            errors.rejectValue("beginEnrollmentDateTime", "wrongValue", "beginEnrollmentDateTime is wrong.");
        }

    }

}
