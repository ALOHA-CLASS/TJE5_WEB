package com.joeun.board.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joeun.board.dto.Board;
import com.joeun.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/board")
public class BoardApiController {


    @Autowired
    private BoardService boardService;
    
    
    @GetMapping()
    public ResponseEntity<?> getAll() {
        try {
            List<Board> boardList = boardService.list();
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{no}")
    public ResponseEntity<?> getOne(@PathVariable Integer no) {
        try {
            Board board = boardService.select(no);
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Board board) {
        log.info(board.toString());
        try {
            int result = boardService.insert(board);
            if( result > 0 ) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("FAIL", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Board board) {
        try {
            int result = boardService.update(board);
            if( result > 0 ) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("FAIL", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{no}")
    public ResponseEntity<?> destroy(@PathVariable Integer no) {
        try {
            int result = 0;
            if( no == -1 ) result = boardService.deleteAll();
            else result = boardService.delete(no);
            if( result > 0 ) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("FAIL", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
