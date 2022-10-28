package com.spring.webmvc.board.repository;

import com.spring.webmvc.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// SQL Mapper (sql과 자바를 연결해주는) 인터페이스
// 리소시즈 폴더에 지금 이 패키지랑 똑같이 폴더 만들고 이거랑 똑같은 이름으로 xml만들기

// 역할 : 데이터를 저장 조회 수정 삭제하는 책임을 부여받음
@Mapper
public interface BoardRepository {
    // 리턴값을 생각해서 넣자
    // findAll은 다 가져오니까 list 단일조회는 보드 하나의 데이터를 가져오니까 매게변수 boardNo랑 리턴값 Board 쓰기는 성공실패니까 boolean

    // 게시글 목록 조회
    List<Board> findAll();

    // 게시글 상세 단일 조회
    Board findOne(Long boardNo);

    // 게시글 쓰기
    boolean save(Board board);

    // 게시글 삭제
    boolean remove(Long boardNo);

    // 게시글 수정
    boolean modify(Board board);

}
