package com.cpe.sumulationserver.services.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "*/5 * * * * *")
    public void updateEvery5Seconds() {
        //log.info("The time is now {}", dateFormat.format(new Date()));
        // TODO : Ici on vient mettre à jour la position des camions &/ou des pompiers
        // On vient aussi dire si un camion est dans le rayon d'un feu celui-ci réduit en intensité jusqu'à
        // s'éteindre
    }
}
