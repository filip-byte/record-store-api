# Record Shop API

## Overview
This API allows the Record Shop to manage their inventory and provide information to customers about the records they have in stock.

## Features
- **List all albums** in stock
- **Get album by ID**
- **Filter albums** by artist, year, or genre
- **Add, update, and delete albums** from the database

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/filip-byte/record-store-api.git
    ```

2. Set up your database and environment variables:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/recordshop
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

| HTTP Method | Endpoint          | Description               |
|-------------|-------------------|---------------------------|
| GET         | `/albums`          | List all albums           |
| GET         | `/albums/{id}`     | Get album by ID           |
| POST        | `/albums`          | Add a new album           |
| DELETE      | `/albums/{id}`     | Delete album by ID        |
