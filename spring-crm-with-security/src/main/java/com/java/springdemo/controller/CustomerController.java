package com.java.springdemo.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.springdemo.entity.Customer;
import com.java.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject our customer service
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();

		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);

		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer theCustomer = new Customer();

		theModel.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult,
			Model theModel) throws Exception {

		// getting the existing email
		boolean emailexists = doesEmailExist(theCustomer.getEmail(),theCustomer);
		// save the customer using our service

		if (theBindingResult.hasErrors()) {

			return "customer-form";
		}

		else if (emailexists) {

			theModel.addAttribute("emailError", "Email already exists.");
			return "customer-form";
		}

		customerService.saveCustomer(theCustomer);

		return "redirect:/customer/list";

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		// get the customer from our service
		Customer theCustomer = customerService.getCustomer(theId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);

		// send over to our form
		return "customer-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {

		// delete the customer
		customerService.deleteCustomer(theId);

		return "redirect:/customer/list";
	}

	public boolean doesEmailExist(String string, Customer theCustomer) {
/*Integer id= theCustomer.getId();*/
		// check the database if the email already exists
		List<Customer> theCustomers = customerService.getCustomers();
		if (theCustomers != null && !theCustomers.isEmpty()) {
			for (Customer customers : theCustomers) {
				if (string.equals(customers.getEmail()) && theCustomer.getId()!=customers.getId() ) {

					return true;
				}

			}

		}
		return false;
	}

	/*@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public String handleException(final Exception e, Model theModel) {
		theModel.addAttribute("customer", new Customer());
		theModel.addAttribute("emailError", "Email already exists.");
		return "customer-form";
	}*/
}
