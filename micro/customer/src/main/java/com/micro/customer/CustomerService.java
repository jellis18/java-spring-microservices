package com.micro.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckResponse.class,
                customer.getId());
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Customer " + customer.getId() + " is a fraudster");
        }

    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).get();

    }

}
