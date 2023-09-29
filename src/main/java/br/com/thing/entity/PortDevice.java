package br.com.thing.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "port_device")
public class PortDevice extends BaseEntity<PortDeviceKey> {

}
