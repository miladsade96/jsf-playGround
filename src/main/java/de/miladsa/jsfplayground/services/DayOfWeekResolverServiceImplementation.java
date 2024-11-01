package de.miladsa.jsfplayground.services;

import de.miladsa.jsfplayground.interfaces.DayOfWeekResolverService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class DayOfWeekResolverServiceImplementation implements DayOfWeekResolverService {

    @Override
    public String determineDayOfWeek(int year, int month, int dayOfMonth) {
        LocalDate resolvedLocalDate = LocalDate.of(year, month, dayOfMonth);
        return convertToDayOfWeekName(resolvedLocalDate.getDayOfWeek());
    }

    private String convertToDayOfWeekName(DayOfWeek dayOfWeek) {
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }
}
