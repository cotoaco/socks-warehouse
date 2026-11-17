package com.example.sockswarehouse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService service;

    public SocksController(SocksService service) {
        this.service = service;
    }

    @PostMapping("/income")
    public String income(@RequestBody SocksRequest request) {
        service.income(
                request.color,
                request.cottonPart,
                request.quantity
        );
        return "Носки добавлены";
    }

    @PostMapping("/outcome")
    public String outcome(@RequestBody SocksRequest request) {
        service.outcome(
                request.color,
                request.cottonPart,
                request.quantity
        );
        return "Носки выданы";
    }

    public static class SocksRequest {
        public String color;
        public int cottonPart;
        public int quantity;
    }
    @GetMapping
    public int getSocks(
            @RequestParam String color,
            @RequestParam String operation,
            @RequestParam int cottonPart) {

        return service.getSocks(color, operation, cottonPart);
    }
}