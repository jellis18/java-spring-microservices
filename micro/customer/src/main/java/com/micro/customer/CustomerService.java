package com.micro.customer;

import org.springframework.stereotype.Service;

import com.micro.clients.fraud.FraudCheckresponse;
import com.micro.clients.fraud.FraudClient;
import com.micro.clients.notification.NotificationClient;
import com.micro.clients.notification.NotificationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);
        FraudCheckresponse fraudCheckResponse = fraudClient.isFraud(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Customer " + customer.getId() + " is a fraudster");
        }

        notificationClient.sendNotification(new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Welcome %s %s", customer.getFirstName(), customer.getLastName())));

    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).get();

    }

}
