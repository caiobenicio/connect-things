package br.com.thing.mqtt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.thing.dto.MessageMqttResponse;
import br.com.thing.entity.Board;
import br.com.thing.entity.Port;
import br.com.thing.enums.PortType;
import br.com.thing.repository.BoardRepository;
import br.com.thing.repository.PortRepository;

@Component
public class SavePort {

    MessageMqttResponse msg;
    
	@Autowired(required=true)
    private BoardRepository boardRepository;

	@Autowired(required=true)
	private PortRepository portRepository; 
          
    public SavePort() {
    }
    
    public SavePort(MessageMqttResponse msg) {
        this.msg = msg;
    }

    public void save() {
        Optional<Board> board = boardRepository.findById(msg.getBoard());
        if (board.isPresent() && board.get().getPorts().isEmpty()) {
            for (String in : msg.getPinsIn()) {
                portRepository.save(new Port(in, PortType.I, board.get()));
            }

            for (String out : msg.getPinsOut()) {
                portRepository.save(new Port(out, PortType.O, board.get()));
            }
        } 
    }

}
