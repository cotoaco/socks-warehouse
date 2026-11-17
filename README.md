# Socks Warehouse
Ссылка на задание: https://github.com/Hexlet/ru-test-assignments/blob/main/java/raiffeisen/cib-interns-test-task.md
Простое REST API приложение для учёта носков на складе.

## Технологии
- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Возможности
- Добавление носков на склад (income).
- Выдача носков со склада (outcome).
- Получение количества носков по цвету и составу хлопка.

## Запуск
1. Создать базу данных PostgreSQL `socks_warehouse`.
2. Указать пароль базы данных через переменную окружения `DB_PASSWORD`.
3. Запустить приложение через IntelliJ IDEA.

Приложение работает на порту 8080.

## API
- `POST /api/socks/income`
- `POST /api/socks/outcome`
- `GET /api/socks?color=red&operation=equal&cottonPart=100`

Пример JSON для добавления носков:

```json
{
  "color": "red",
  "cottonPart": 100,
  "quantity": 10
}
```
