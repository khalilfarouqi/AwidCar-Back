package com.khalil.wdcar.service;

import com.khalil.wdcar.beans.CarRentalBean;
import com.khalil.wdcar.dto.CarDto;
import com.khalil.wdcar.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SearchService {
    public final CarService carService;
    public final ClientService clientService;

    public List<CarRentalBean> getSearch(String label) {
        List<CarRentalBean> carRentalBeanList = new ArrayList<>();
        CarRentalBean rentalBeanSec = new CarRentalBean();
        List<CarDto> carList = carService.findAllByQuery(rentalBeanSec.setSearch(label));
        List<ClientDto> clientList = clientService.findAllByQuery(rentalBeanSec.setSearch(label));

        carRentalBeanList.addAll(mapCarDtoListToCarRentalBeanList(carList));
        carRentalBeanList.addAll(mapClientDtoListToCarRentalBeanList(clientList));

        return carRentalBeanList;
    }

    private List<CarRentalBean> mapCarDtoListToCarRentalBeanList(List<CarDto> carList) {
        List<CarRentalBean> result = new ArrayList<>();
        for (CarDto car : carList) {
            CarRentalBean rentalBean = new CarRentalBean();
            rentalBean.setName(car.getName());
            rentalBean.setBrande(car.getBrande() != null ? car.getBrande().toString() : null);
            rentalBean.setStyle(car.getStyle() != null ? car.getStyle().toString() : null);
            result.add(rentalBean);
        }
        return result;
    }

    private List<CarRentalBean> mapClientDtoListToCarRentalBeanList(List<ClientDto> clientList) {
        List<CarRentalBean> result = new ArrayList<>();
        for (ClientDto client : clientList) {
            CarRentalBean rentalBean = new CarRentalBean();
            rentalBean.setAdress(client.getAdress());
            rentalBean.setCin(client.getCin());
            rentalBean.setCity(client.getCity() != null ? client.getCity().toString() : null);
            rentalBean.setCountry(client.getCountry());
            rentalBean.setFirstName(client.getFirstName());
            rentalBean.setLastName(client.getLastName());
            rentalBean.setLicence(client.getLicence());
            rentalBean.setUserName(client.getUserName());
            result.add(rentalBean);
        }
        return result;
    }

}
