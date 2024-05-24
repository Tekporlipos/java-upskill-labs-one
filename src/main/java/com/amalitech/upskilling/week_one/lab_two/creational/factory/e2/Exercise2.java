package com.amalitech.upskilling.week_one.lab_two.creational.factory.e2;

interface DatabaseConnection {
    void connect();
}

class MySQLConnection implements DatabaseConnection {
    @Override
    public void connect() {
        System.out.println("Connecting to MySQL");
    }
}

class PostgreSQLConnection implements DatabaseConnection {
    @Override
    public void connect() {
        System.out.println("Connecting to PostgreSQL");
    }
}

class DatabaseConnectionFactory {
    public DatabaseConnection createConnection(String dbType) {
        return switch (dbType.toLowerCase()) {
            case "mysql" -> new MySQLConnection();
            case "postgresql" -> new PostgreSQLConnection();
            default -> throw new IllegalArgumentException("Unknown database type");
        };
    }
}

class FactoryPatternDBExample {
    public static void main(String[] args) {
        DatabaseConnectionFactory factory = new DatabaseConnectionFactory();
        DatabaseConnection connection1 = factory.createConnection("mysql");
        connection1.connect();
        DatabaseConnection connection2 = factory.createConnection("postgresql");
        connection2.connect();
    }
}
