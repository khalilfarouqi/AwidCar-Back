package com.khalil.wdcar.service;

import com.khalil.wdcar.beans.CarRentalBean;
import com.khalil.wdcar.beans.RentalBean;
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
    private final CarService carService;
    private final ClientService clientService;

    public List<RentalBean> getSearch(String label) {
        List<RentalBean> carRentalBeanList = new ArrayList<>();
        CarRentalBean carRentalBean = new CarRentalBean();
        carRentalBeanList.addAll(mapCarDtoListToCarRentalBeanList(carService.findAllByQuery(carRentalBean.setSearch(label)), label));
        carRentalBeanList.addAll(mapClientDtoListToCarRentalBeanList(clientService.findAllByQuery(carRentalBean.setSearch(label)), label));
        return carRentalBeanList;
    }

    private List<RentalBean> mapCarDtoListToCarRentalBeanList(List<CarDto> carList, String label) {
        List<RentalBean> result = new ArrayList<>();
        for (CarDto car : carList) {
            result.addAll(mapToRentalBeans(car, label, "cars"));
        }
        return result;
    }

    private List<RentalBean> mapClientDtoListToCarRentalBeanList(List<ClientDto> clientList, String label) {
        List<RentalBean> result = new ArrayList<>();
        for (ClientDto client : clientList) {
            result.addAll(mapToRentalBeans(client, label, "clients"));
        }
        return result;
    }

    private List<RentalBean> mapToRentalBeans(Object dto, String label, String tableName) {
        List<RentalBean> result = new ArrayList<>();
        RentalBean rentalBean = new RentalBean();

        if (dto instanceof CarDto) {
            CarDto car = (CarDto) dto;
            rentalBean.setTableName(tableName);
            rentalBean.setIdColum(car.getId());
            for (String column : new String[]{"name", "brande", "style"}) {
                String columnValue = getColumnValue(car, column);
                if (columnValue != null && columnValue.startsWith(label)) {
                    rentalBean.setColumName(column);
                    rentalBean.setValue(columnValue);
                    result.add(rentalBean);
                }
            }
        } else if (dto instanceof ClientDto) {
            ClientDto client = (ClientDto) dto;
            rentalBean.setTableName(tableName);
            rentalBean.setIdColum(client.getId());
            for (String column : new String[]{"adress", "cin", "city", "country", "first_name", "last_name", "licence", "user_name"}) {
                String methodName = getMethodName(column);
                String columnValue = getColumnValue(client, methodName);
                if (columnValue != null && columnValue.startsWith(label)) {
                    rentalBean.setColumName(column);
                    rentalBean.setValue(columnValue);
                    result.add(rentalBean);
                }
            }
        }
        return result;
    }

    private String getColumnValue(Object obj, String column) {
        try {
            String methodName = "get" + column.substring(0, 1).toUpperCase() + column.substring(1);
            Object value = obj.getClass().getMethod(methodName).invoke(obj);
            return value != null ? value.toString() : null;
        } catch (Exception e) {
            return null;
        }
    }

    private String getMethodName(String column) {
        // Convert the column name to a standard Java bean getter method name.
        String[] parts = column.split("_");
        StringBuilder methodName = new StringBuilder();
        boolean firstPart = true;
        for (String part : parts) {
            if (firstPart) {
                methodName.append(part);
                firstPart = false;
            } else {
                methodName.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
            }
        }
        return methodName.toString();
    }
}
