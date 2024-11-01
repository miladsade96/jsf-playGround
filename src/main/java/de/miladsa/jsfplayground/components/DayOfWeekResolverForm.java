package de.miladsa.jsfplayground.components;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
@Getter
@Setter
public class DayOfWeekResolverForm implements Serializable {
    private int year;
    private int month;
    private int dayOfMonth;
    private String dayOfWeek;
}
