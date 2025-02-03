package com.example.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Locale;

@RestController
@Tag(name = "Demo API", description = "Demo API for Springdoc OpenAPI example")
public class DemoController {

    // define a private field for the dependency
    private Coach myCoach;

    @Autowired
    private MessageSource messageSource;

    // define a constructor for dependency injection
    @Autowired
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    @Operation(summary = "Get daily workout", description = "This endpoint returns a daily workout plan from the Coach.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved daily workout", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Coach.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error 1")
    })
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @PostMapping("/update-coach/{coachId}")
    @Operation(summary = "Submit daily workout", description = "This endpoint allows submitting a daily workout plan to the Coach.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully submitted daily workout", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Coach.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public String submitDailyWorkout(
            @Valid @Parameter(description = "Workout details in JSON format") @RequestBody CricketCoach dto,
            @Valid @Parameter(description = "Path parameter for coach ID") @PathVariable String coachId,
            @Valid @Parameter(description = "Query parameter for workout type") @RequestParam String workoutType) {
        // Process the workout details and type
        return messageSource.getMessage("greeting", null, Locale.of("vi", "VN"));

    }
}
