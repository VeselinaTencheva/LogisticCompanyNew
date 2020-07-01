package com.example.demo.repository;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.serial.SerialJavaObject;
import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, String> {

     List<Shipment> findShipmentByStatus(boolean status);

    List<Shipment> findShipmentByDelivered(boolean isDelivered);

    List<Shipment> findShipmentByDeliveredAndStatus(boolean isDelivered,boolean isRegistered);

    List<Shipment> findShipmentBySender(Customer sender);

    List<Shipment> findShipmentByRecipientAndDelivered(Customer recipient,boolean isDelivered);

    List<Shipment> findShipmentByEmployee(Employee employee);


}
