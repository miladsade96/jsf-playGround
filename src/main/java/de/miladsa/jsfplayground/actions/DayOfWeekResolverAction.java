package de.miladsa.jsfplayground.actions;

import de.miladsa.jsfplayground.components.DayOfWeekResolverForm;
import de.miladsa.jsfplayground.services.DayOfWeekResolverServiceImplementation;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@ViewScoped
@Named(value = "dayOfWeekResolverAction")
public class DayOfWeekResolverAction implements Serializable {
    private final DayOfWeekResolverForm dayOfWeekResolverForm;
    private final DayOfWeekResolverServiceImplementation dayOfWeekResolverServiceImplementation;

    @Inject
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
