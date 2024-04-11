# YPasswordManager

Welcome to the YPasswordManager - a secure and efficient solution to manage your passwords. Our application provides a user-friendly interface for storing, retrieving, and managing your passwords with ease. This project is housed in a mono-repository on GitHub, ensuring that all components of the project are centralized and easily accessible.

## Features

- **Secure Storage**: Encrypt and securely store your passwords.
- **Easy Management**: Add, update, and delete passwords.
- **Search Functionality**: Quickly find the passwords you need.
- **Multi-Platform Support**: Accessible on various platforms through our web interface.

## Project Structure

The repository is organized into four main folders:

- **`/front`**: Contains the Angular-based frontend application, offering a responsive and intuitive user interface.
- **`/back`**: Houses the Spring Boot backend application, responsible for secure data handling and business logic.
- **`/deploy`**: Includes scripts, Docker-compose files, and other necessary tools for deploying the application.
- **`/docs`**: Contains global documentation of the project, including setup guides, usage instructions, and development practices.

## Getting Started

### Prerequisites

- Docker and Docker-compose
- Node.js and npm (for frontend development)
- Java Development Kit (JDK) for Spring Boot

### Setup and Installation

1. Clone the repository:

```bash
git clone https://github.com/strackzdev/ypasswordmanager
```
   
2. Run the docker compose application (Development will be in the /docs):

```bash
npm run compose:dev
```

If you're planning to run this project in a production environment, I can't stress enough how **important it is to change the default passwords** we've set up for development or testing. Don't worry, though – we'll have a guide for setting up everything for production ready pretty soon to help you out.


