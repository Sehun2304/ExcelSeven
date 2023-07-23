package com.excelseven.backoffice.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false, unique = true)
    private String email;

    @ElementCollection //값 타입 컬렉션을 매핑할 때 사용, 데이터베이스에 별도의 테이블을 생성하지 않고, 엔티티와
    //같은 테이블에 해당 값들이 컬렉션으로 저장
    private List<String> lastThreePasswords;

    public List<String> getLastThreePasswords(){
        return lastThreePasswords;
    }

    public void setLastThreePasswords(List<String> lastThreePasswords){
        this.lastThreePasswords = lastThreePasswords;
    }


    public User(SignupRequestDto requestDto, String password) {
        this.username = requestDto.getUsername();
        this.password = password;
    }
//
//    @Column(nullable = false)
//    private String nickName;




}
