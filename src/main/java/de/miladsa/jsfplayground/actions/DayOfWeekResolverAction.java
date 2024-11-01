package de.miladsa.jsfplayground.actions;

import de.miladsa.jsfplayground.components.DayOfWeekResolverForm;
import de.miladsa.jsfplayground.services.DayOfWeekResolverServiceImplementation;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.io.Serializable;

@Component
@RequestScope
public class DayOfWeekResolverAction implements Serializable {
    private DayOfWeekResolverForm dayOfWeekResolverForm;
    private DayOfWeekResolverServiceImplementation dayOfWeekResolverServiceImplementation;

    public DayOfWeekResolverAction(DayOfWeekResolverForm dayOfWeekResolverForm, DayOfWeekResolverServiceImplementation dayOfWeekResolverServiceImplementation) {
        this.dayOfWeekResolverForm = dayOfWeekResolverForm;
        this.dayOfWeekResolverServiceImplementation = dayOfWeekResolverServiceImplementation;
    }

    public void determineDayOfWeek() {
        int year = dayOfWeekResolverForm.getYear();
        int month = dayOfWeekResolverForm.getMonth();
        int dayOfMonth = dayOfWeekResolverForm.getDayOfMonth();

        String dayOfWeekName = dayOfWeekResolverServiceImplementation.determineDayOfWeek(year, month, dayOfMonth);
        dayOfWeekResolverForm.setDayOfWeek(dayOfWeekName);
    }
}
