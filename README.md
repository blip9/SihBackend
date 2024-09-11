# Employee Attendance Tracking System

## Overview

This project is a Spring Boot-based backend system designed to automate and streamline employee attendance tracking across multiple office locations. It uses geolocation technology to record check-ins and check-outs and calculates total working hours for employees.

## Features

- **Geolocation-Based Check-In/Check-Out**: Automatically records check-ins and check-outs within a 200-meter radius of the office.
- **Manual Check-In/Check-Out**: Allows manual check-ins and check-outs for offsite work with location suggestions.
- **Working Hours Calculation**: Computes and stores total working hours for each employee daily.
- **Data Accuracy**: Ensures accurate and tamper-proof attendance records.

## Architecture

### Components

- **Controller**:
  - `CheckInOutController`: Handles check-in and check-out requests.
- **Service**:
  - `CheckInOutService`: Manages check-in/check-out logic.
  - `WorkingHoursService`: Calculates and updates total working hours.
- **Repository**:
  - `CheckInOutRepository`: Interfaces with the `Check_In_Out_Events` table.
  - `WorkingHoursRepository`: Interfaces with the `Working_Hours_Calculation` table.
- **Database**:
  - **`Check_In_Out_Events`**: Stores check-in/check-out events.
  - **`Working_Hours_Calculation`**: Stores total working hours.

### Endpoints

- **POST /api/attendance/checkin**: Records a check-in event.
- **POST /api/attendance/checkout**: Records a check-out event and updates working hours.
- **GET /api/attendance/working-hours/{employeeId}/{date}**: Retrieves total working hours for a specific date.

## Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/blip9/SihBackend.git
   cd SihBackend

Update application.properties for database configuration and other settings.


