package com.spring.webmvc.board.domain;

// 취업한 후에 JPA를 꼭 공부해라 -> 미래지향적기술 -> jpa가 하이버네이트임

// DTO는 넘어오는  값만 멤버변수로 있으면 됨 ex) writer, title, content만 있어도 됨

import lombok.*;

import java.util.Date;

// DB 엔터티(테이블)과 1:1 매칭되는 VO (Value Object)

// @ToString : 변수값을 출력하면 ~~~@~~~이런식으로 주소값이 나옴, 주소값말고 필드값을 보고싶을때 쓰는거
// @NoArgsConstructor :
// @AllArgsConstructor :
// 생성자 단점 : 객체를 만들때 set으로 설정할수도 있지만, 객체(~~,~~,~~) 이런식으로 늠 -> 파라미터 순서도 생각 안남
// @Builder : -> 그래서 이걸로 하면 원하는것만 넣을수있고, 순서 생각 안나도 상관없음
// ex) Board build = Board.builder().title("제먹").boardNo(20L).content("ㅇㅇ").build(); //build()는 끝내고싶을때
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    private Long boardNo;
    private String writer;
    private String content;
    private String title;
    private int viewCnt;
    private Date regDate;

    // 커스텀 필드
    private String shortTitle; // 줄임 제목
    private String prettierDate; // 포맷팅한 날짜문자열



    private Board(Builder builder) {
        this.boardNo = builder.boardNo;
        this.writer = builder.writer;
        this.content = builder.content;
        this.title = builder.title;
        this.viewCnt = builder.viewCnt;
        this.regDate = builder.regDate;
    }


    public static class Builder {
        private Long boardNo;
        private String writer;
        private String content;
        private String title;
        private int viewCnt;
        private Date regDate;


        public Builder boardNo(Long boardNo) {
            this.boardNo = boardNo;
            return this;
        }

        public Builder writer(String writer) {
            this.writer = writer;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder viewCnt(int viewCnt) {
            this.viewCnt = viewCnt;
            return this;
        }

        public Builder regDate(Date regDate) {
            this.regDate = regDate;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }

}
