package br.com.thing.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.dto.DeviceDTO;
import br.com.thing.dto.DeviceStatusDTO;
import br.com.thing.entity.Board;
import br.com.thing.entity.Device;
import br.com.thing.entity.Port;
import br.com.thing.mqtt.MqttConnection;
import br.com.thing.mqtt.Publisher;
import br.com.thing.repository.BoardRepository;
import br.com.thing.repository.DeviceRepository;
import br.com.thing.repository.PortRepository;
import br.com.thing.service.GenericService;
import br.com.thing.service.ServicePaths;

@RestController
@RequestMapping(path = ServicePaths.DISPOSITIVO_PATH)
public class DeviceController extends GenericService<Device, Long> {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private BoardRepository boardRepository;

	@Autowired
	private PortRepository portRepository;      

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Device> device = deviceRepository.findById(id);

        if (device.get().getPort() != null) {
            DeviceDTO deviceWithPort = deviceRepository.getDeviceBoardByPort(device.get().getPort().getId());
            Port port = new Port();
            port.setId(deviceWithPort.getPortId());
            port.setName(deviceWithPort.getPortName());
            device.get().setPort(port);

            Board board = new Board();
            board.setName(deviceWithPort.getBoardName());
            device.get().getPort().setBoard(board);
        }

        return ResponseEntity.status(HttpStatus.OK).body(device);
    }

    @GetMapping(value = "/findByClientId/{id}")
    public ResponseEntity<?> findByClientId(@PathVariable("id") Long id) {
        List<Device> deviceList = deviceRepository.findByClientId(id);

        return ResponseEntity.status(HttpStatus.OK).body(deviceList);
    }

    @Override
    public Device insert(@RequestBody Device device) {
        return super.insert(device);
    }

    @PutMapping(value = "/updatePort/{portId}/{deviceId}")
    public ResponseEntity<?> updatePort(@PathVariable("portId") Long portId, @PathVariable("deviceId") Long deviceId) {
        deviceRepository.updatePort(portId, deviceId);
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }

    @PutMapping(value = "/updateStatus/")
    public ResponseEntity<?> updateStatus(@RequestBody DeviceStatusDTO device) {
        deviceRepository.updateStatus(device.getDevice(), device.getAction() ==1 ? true:false);

        Optional<Port> port = portRepository.findById(device.getPin());

        if (port.get()!= null) {
            Optional<Board> board = boardRepository.findById(port.get().getBoard().getId());

            int pino = Integer.valueOf(port.get().getName().substring(1, 3));
    
            new Publisher(MqttConnection.CLIENT_ID, device.getUser(), board.get().getId(), board.get().getTopicPublish(), "A", pino, device.getAction());            
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }
}
