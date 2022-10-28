package com.spring.webmvc.board.repository;

import com.spring.webmvc.board.domain.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.Math.random;

// 테스트시 스프링의 컨테이너를 사용할 것임.
// -> 의존객체를 스프링에게 주입받아 사용할 것이다.
@SpringBootTest
class BoardRepositoryTest {

    // junit5 부터는 모든 제한자를 디폴트제한으로 설정
    // 필드주입 사용
    @Autowired
    BoardRepository repository;


    @Test
    void bulkInsert() {
        for (int i = 1; i <= 300; i++) {
            Board b = new Board();
            b.setTitle("꿀꿀이" + i);
            b.setContent("ㅎㅇㅇㅎ" + i);
            b.setWriter("곤육몬" + (300 - i));

            repository.save(b);
        }
    }

    // 단언(Assertion) : 강하게 주장한다.
    @Test
    @DisplayName("300번 게시글을 조회했을 떄 제목이 꿀꿀이300이어야 한다")
    void findOneTest() {
        // given : 테스트시 주어지는 변동 데이터
        Long boardNo = 300L;

        // when : 테스트 실제 상황
        Board board = repository.findOne(boardNo);

        // then : 테스트 예상 결과
        // (나의 예상 결과, 실제 결과)
        assertEquals("꿀꿀이300", board.getTitle());
    }

    @Test
    @DisplayName("전체 게시물을 조회했을때 리스트의 크기가 300이어야 한다")
    void findAllTest() {

        //when
        List<Board> boardList = repository.findAll();
        for (Board board : boardList) { // iter 하고 단축완성 하면 이렇게됨
            System.out.println(board); // board.toString()임
        }
        //then
        assertEquals(300, boardList.size()); // 아마 300개일거다, 보드리스트의 사이즈가

    }

    // 테스트용이니까 커밋하지말고 롤백해야 하기때문에 Transactional, Rollback을 넣어준다
    @Test
    @DisplayName("298번의 제목을 응후엔232, 내용을 랄랄랄 으로 수정해야 한다.")
    @Transactional
    @Rollback
    void modifyTest() {
        //given
        Board board = new Board();
        board.setBoardNo(298L);
        board.setTitle("응우엔");
        board.setContent("랄랄랄");
        //when
        boolean modify = repository.modify(board);
        Board foundBoard = repository.findOne(board.getBoardNo());

        //then
        assertTrue(modify);
        assertEquals("응우엔", foundBoard.getTitle());
        assertEquals("랄랄랄", foundBoard.getContent());

    }

    @Test
    @DisplayName("300번 게시물을 삭제하고 다시 조회했을때 null값이 나와야 한다")
    @Transactional
    @Rollback
    void removeTest() {
        //given
        Long boardNo=300L;

        //when
        boolean flags = repository.remove(boardNo);
        Board board = repository.findOne(boardNo);

        //then
        assertTrue(flags);
        assertNull(board);
    }

}

