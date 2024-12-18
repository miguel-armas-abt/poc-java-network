package com.demo.poc.service;

import com.demo.poc.repository.ubigeo.UbigeoRepository;
import com.demo.poc.dto.ContactDataDTO;
import com.demo.poc.dao.customer.entity.CustomerEntity;
import com.demo.poc.mapper.ContactDataMapper;
import com.demo.poc.repository.customer.CustomerRepository;
import com.demo.poc.dao.ubigeo.wrapper.UbigeoResponseWrapper;
import com.google.inject.Inject;

public class ContactDataServiceImpl implements ContactDataService {

  private final ContactDataMapper mapper;
  private final CustomerRepository customerRepository;
  private final UbigeoRepository ubigeoRepository;

  @Inject
  public ContactDataServiceImpl(ContactDataMapper mapper,
                                CustomerRepository customerRepository,
                                UbigeoRepository ubigeoRepository) {
    this.mapper = mapper;
    this.customerRepository = customerRepository;
    this.ubigeoRepository = ubigeoRepository;
  }

  @Override
  public ContactDataDTO findByDni(String dni) {
    CustomerEntity customer = customerRepository.findByDni(dni);
    UbigeoResponseWrapper ubigeo = ubigeoRepository.findUbigeo(customer.getUbigeoCode());
    return mapper.toDTO(customer, ubigeo);
  }
}
