package com.joeun.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.board.dto.Board;
 
@Mapper
public interface BoardMapper {

    // 게시글 목록
    public List<Board> list() throws Exception;
    // 게시글 조회
    public Board select(int no) throws Exception;
    // 게시글 등록
    public int insert(Board board) throws Exception;
    // 게시글 수정
    public int update(Board board) throws Exception;
    // 게시글 삭제
    public int delete(int no) throws Exception;
    // 게시글 전체 삭제
    public int deleteAll() throws Exception;
    
    // 게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;
    
}












