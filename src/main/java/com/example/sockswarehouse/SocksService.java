package com.example.sockswarehouse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SocksService {

    private final SocksRepository repository;

    public SocksService(SocksRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void income(String color, int cottonPart, int quantity) {
        Socks socks = repository.findAll().stream()
                .filter(s -> s.getColor().equalsIgnoreCase(color)
                        && s.getCottonPart() == cottonPart)
                .findFirst()
                .orElse(null);

        if (socks == null) {
            socks = new Socks();
            socks.setColor(color);
            socks.setCottonPart(cottonPart);
            socks.setQuantity(quantity);
        } else {
            socks.setQuantity(socks.getQuantity() + quantity);
        }

        repository.save(socks);
    }

    @Transactional
    public void outcome(String color, int cottonPart, int quantity) {
        Socks socks = repository.findAll().stream()
                .filter(s -> s.getColor().equalsIgnoreCase(color)
                        && s.getCottonPart() == cottonPart)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Носки не найдены"));

        if (socks.getQuantity() < quantity) {
            throw new RuntimeException("Недостаточно носков на складе");
        }

        socks.setQuantity(socks.getQuantity() - quantity);
        repository.save(socks);
    }
    public int getSocks(String color, String operation, int cottonPart) {
        return repository.findAll().stream()
                .filter(s -> s.getColor().equalsIgnoreCase(color))
                .filter(s -> switch (operation) {
                    case "moreThan" -> s.getCottonPart() > cottonPart;
                    case "lessThan" -> s.getCottonPart() < cottonPart;
                    case "equal" -> s.getCottonPart() == cottonPart;
                    default -> throw new IllegalArgumentException("Неверная операция");
                })
                .mapToInt(Socks::getQuantity)
                .sum();
    }
}