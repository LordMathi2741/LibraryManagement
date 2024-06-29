package com.LibraryManagement.upc.shared.application.internal.eventhandlers;

import com.LibraryManagement.upc.iam.domain.model.commands.SeedRolesCommand;
import com.LibraryManagement.upc.iam.domain.services.RoleCommandService;
import com.LibraryManagement.upc.inventory.domain.model.commands.SeedGenresCommand;
import com.LibraryManagement.upc.inventory.domain.services.SeedGenresCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
/**
 * ApplicationReadyEventHandler class
 * This class is used to handle the ApplicationReadyEvent
 */
@Service
public class ApplicationReadyEventHandler {
    private final RoleCommandService roleCommandService;
    private final SeedGenresCommandService seedGenresCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(RoleCommandService roleCommandService, SeedGenresCommandService seedGenresCommandService) {
        this.roleCommandService = roleCommandService;
        this.seedGenresCommandService = seedGenresCommandService;
    }

    /**
     * Handle the ApplicationReadyEvent
     * This method is used to seed the roles
     * @param event the ApplicationReadyEvent the event to handle
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if roles seeding is needed for {} at {}", applicationName, currentTimestamp());
        LOGGER.info("Seeding  book genres");
        var seedRolesCommand = new SeedRolesCommand();
        var command = new SeedGenresCommand();
        seedGenresCommandService.handle(command);
        roleCommandService.handle(seedRolesCommand);
        LOGGER.info("Roles seeded");
        LOGGER.info("Book genres seeded");
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}