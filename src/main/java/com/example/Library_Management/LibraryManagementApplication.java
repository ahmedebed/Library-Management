package com.example.Library_Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LibraryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);

		// كود توليد الباسورد المشفر (استخدمه مؤقتاً)
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "Ahmed123@"; // الباسورد اللي بيمر بالـ Pattern
		String hashedPassword = encoder.encode(rawPassword);
		System.out.println("Generated hashed password: " + hashedPassword);
	}
}
