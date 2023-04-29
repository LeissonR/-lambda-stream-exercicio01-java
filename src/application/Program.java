package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter full file path: ");

		try (BufferedReader br = new BufferedReader(new FileReader(sc.next()))) {
			String line = br.readLine();

			List<Employee> list = new ArrayList<>();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			System.out.print("Enter salary: ");
			Double salaryComp = sc.nextDouble();

			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salaryComp));

			Comparator<String> com = (s1, s2) -> s1.toLowerCase().compareTo(s2.toLowerCase());

			List<String> emails = list.stream().filter(e -> e.getSalary() > salaryComp).map(e -> e.getEmail())
					.sorted(com).collect(Collectors.toList());

			emails.forEach(System.out::println);

			Double sumSalary = list.stream()
					.filter(e -> e.getName().charAt(0) == 'M')
					.map(e -> e.getSalary())
					.reduce(0.00, (x,y) -> x + y);
					
			
			System.out.println("Sum of salary of people whose same starts with 'M': " + String.format("%.2f", sumSalary));
		}

		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();
	}

}
