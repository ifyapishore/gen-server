package com.example.demo.controller;

import com.example.demo.apimodel.StatSummary;
import com.example.demo.patterns.ApiEntityMeta;
import com.example.demo.patterns.ApiPermission;
import com.example.demo.patterns.IApiResponse;
import com.example.demo.tools.ExposeInRootLinks;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@ExposeInRootLinks(rel = "stat")
@Transactional
@RestController
@RequestMapping("${myapp.api-prefix}/stat")
public class StatController {

    @Operation(summary = "getStats", description = "Sample stat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = StatSummary.class))),
            @ApiResponse(responseCode = "404", description = "Stat not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("")
    public IApiResponse<StatSummary> getStats() {
        // Simulate some processing

        var data = new StatSummary();

        data.name = "My app";
        data.description = "API Statistics";
        data.id = "singleton";

        data.$meta = new ApiEntityMeta();
        data.$meta.permissions = ApiPermission.managerRole();

        return IApiResponse.of(data);
    }
    @GetMapping("/{userId}/task/{taskId}")
    public ResponseEntity<String> getTask(
            @PathVariable Long userId,
            @PathVariable Long taskId,
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(
                "User: " + userId + ", Task: " + taskId + ", Search: " + search
        );
    }
    //
//    @GetMapping("/list")
//    public ApiR getStatDetails() {
//        // Simulate some processing
//        final Map<String, String> resp = new HashMap<>();
//        resp.put("API Statistics", "API Statistics");
//        return resp;
//    }
}
