package br.com.thing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thing.entity.Board;
import br.com.thing.entity.Client;
import br.com.thing.entity.Home;
import br.com.thing.repository.BoardRepository;

@RestController
@RequestMapping(path = ServicePaths.BOARD_PATH)
public class BoardService extends GenericService<Board, Long> {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> findByClientId(Long clientId) {
        return boardRepository.findByClientId(clientId);
    }

    @Override
    public Board insert(@RequestBody Board board) {
        board = super.insert(board);

        return board;
    }

//    @GetMapping(value = "/findByClientId/{id}")
//    public ResponseEntity<?> findByClientId(@PathVariable("id") Long id) {
//        Optional<Client> client = userRepository.findById(id);
//        Optional<List<Home>> home = homeRepository.findByClient(client.get());
//
//        return ResponseEntity.status(HttpStatus.OK).body(home);
//    }
}
