package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 롬복 어노테이션의 장점: 서비스 초기 구축 단계에선 Entity(테이블)의 수정이 빈번하게 일어나는데,
// 이때 코드 변경량을 최소화할 수 있음
@Getter // 클래스 내 모든 필드에 Getter메소드 자동 생성
@NoArgsConstructor // 기본생성자 자동 추가 : public Posts(){}
@Entity // 테이블과 링크될 클래스임을 나타냄 (JPA 어노테이션)
public class Posts { // 실제 DB테이블과 매칭될 클래스 (Entity 클래스라고도 함)
    @Id // 해당 테이블의 PK필드, PK는 Primary Key로, 각 행을 고유하게 식별하는 역할
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙. GenerationType.IDENTITY옵션을 추가해야 auto_increment가 됨
    private Long id;

    // @Column: 테이블의 칼럼을 나타냄, 굳이 선언 안해도 해당 클래스의 필드는 모두 칼럼이 됨
    // 사용 이유는 추가로 변경이 필요한 옵션을 사용할 수 있어서
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
