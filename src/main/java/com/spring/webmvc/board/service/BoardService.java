package com.spring.webmvc.board.service;

// 역할 : 컨트롤러와 레파지토리 사이의 중간 잡다한 처리를 담당

import com.spring.webmvc.board.domain.Board;
import com.spring.webmvc.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor // final 필드 초기화 생성자를 만듦
@Service
public class BoardService {
    private final BoardRepository repository; // final거는 순간 셋터가 안됨 -> 생성자 주입으로 ㄱㄱ 생성자주입을 써야하는 이유 이런거 보자

//    @Autowired
//    public BoardService(BoardRepository repository){
//        this.repository = repository;
//    }

    // 전체 조회 중간처리
    public List<Board> getList() {
        List<Board> boardList = repository.findAll();
        for (Board b : boardList) {
            subStringTitle(b); // Ctrl + alt + m 해서 for문 안에 있던거 함수로 뻄
            convertDateFormat(b);
        }




        return boardList;
    }

    // 날짜 포맷팅 처리
    private void convertDateFormat(Board b) {
        Date regDate = b.getRegDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd a hh:mm");
        b.setPrettierDate(sdf.format(regDate));
    }

    // 게시물 제목 줄임 처리
    // 만약에 글제목이 6글자 이상이면 6글자까지만 보여주고 뒤에 ... 처리
    private void subStringTitle(Board b) {
        String title = b.getTitle();
        if (title.length() > 6) {
            String shortTitle = title.substring(0, 6) + "...";
            b.setShortTitle(shortTitle);
        } else {
            b.setShortTitle(title);
        }
    }

    // 상세 조회 중간처리
    public Board getDetail(Long boardNo) {
        Board board = repository.findOne(boardNo);
        return board;
    }

    // 게시물 저장 중간처리
    public boolean insert(Board board) {
        boolean save = repository.save(board);
        return save;
    }

    // 게시물 수정 중간처리
    public boolean update(Board board) {
        boolean modify = repository.modify(board);
        return modify;
    }

    // 게시물 삭제 중간처리
    public boolean delete(Long boardNo) {
        boolean remove = repository.remove(boardNo);
        return remove;
    }


}
