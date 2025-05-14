package com.example.demo.controller;

import com.example.demo.apimodel.StatSummary;
import com.example.demo.patterns.ApiEntityMeta;
import com.example.demo.patterns.ApiPermission;
import com.example.demo.patterns.ApiResponse;
import com.example.demo.tools.ExposeInRootLinks;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ExposeInRootLinks(rel = "stat")
@Transactional
@RestController
@RequestMapping("${myapp.api-prefix}/stat")
public class StatController {

    @GetMapping("")
    public ApiResponse<StatSummary> getStats() {
        // Simulate some processing

        var data = new StatSummary();

        data.name = "My app";
        data.description = "API Statistics";
        data.id = "singleton";

        data.$meta = new ApiEntityMeta();
        data.$meta.permissions = ApiPermission.managerRole();

        return ApiResponse.of(data);
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
