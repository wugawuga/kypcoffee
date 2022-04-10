package team.kyp.kypcoffee.domain;

import lombok.Data;

@Data
public class AuthInfo { //세션에 로그인 정보 저장

    private String id;
    private String name;
    private int no;
    private String pw;
    private String email;
    private String picture;
    private int type;

    public AuthInfo () {}

    public AuthInfo(String id, String name, int no, String pw, int type) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.pw = pw;
        this.type = type;
    }

    public AuthInfo(String id, String name, int no, String pw) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.pw = pw;
    }

    public AuthInfo(String id, String name, int no) {
        this.id = id;
        this.name = name;
        this.no = no;
    }

    public AuthInfo(String id, String name, int no, String pw, String email, String picture) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.pw = pw;
        this.email = email;
        this.picture = picture;
    }

    public AuthInfo(String id, String name, int no, String pw, String email, String picture,int type) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.pw = pw;
        this.email = email;
        this.picture = picture;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getType() { return type; }

    public void setType(int type) { this.type = type; }
}
