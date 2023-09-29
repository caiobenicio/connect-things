package br.com.thing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Board;
import br.com.thing.entity.Client;
import br.com.thing.mqtt.MqttConnection;
import br.com.thing.mqtt.Publisher;
import br.com.thing.mqtt.Subscribe;
import br.com.thing.repository.BoardRepository;
import br.com.thing.repository.ClientRepository;

@RestController
@RequestMapping(path = ServicePaths.BOARD_PATH)
public class BoardService extends GenericService<Board, Long> {

    @Autowired
    private ClientRepository userRepository;
    
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Board insert(@RequestBody Board board) {
        board = super.insert(board);
        board.setClient(null);
        return board;
    }

    @GetMapping(value = "/findByClientId/{id}")
    public ResponseEntity<?> findByClientId(@PathVariable("id") Long id) {
    	List<Board> boards = null;
    	Optional<Client> client = userRepository.findById(id);
        if(client.isPresent()) {
        	Client user = client.get();
        	boards = user.getBoards();
        }

        return ResponseEntity.status(HttpStatus.OK).body(boards);
    }
    
    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
    	Optional<Board> board = boardRepository.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(board);
    }
    
    @Override
	public ResponseEntity<?> update(@RequestBody Board board, Errors errors) {
    	HttpStatus status = HttpStatus.OK;
    	super.update(board, errors);
    	
    	
    	if (!board.isStatus()) {
    		status = HttpStatus.NO_CONTENT;
    		Subscribe x = new Subscribe();
    		x.unsubscribe(MqttConnection.getInstance().getMapConnection().get(MqttConnection.CLIENT_ID), board.getTopicSubscribe());
    	} else {
    		new Subscribe(MqttConnection.getInstance().getMapConnection().get(MqttConnection.CLIENT_ID), board.getTopicSubscribe());
    	}

    	return ResponseEntity.status(status).body(null);
	}

    @GetMapping(value = "/findByPorts")
    public ResponseEntity<?> findByPorts() {
        new Publisher(MqttConnection.CLIENT_ID, "clientweb/inTopic", "P");
        return ResponseEntity.status(HttpStatus.OK).body("Comando enviado!");
    }
}
