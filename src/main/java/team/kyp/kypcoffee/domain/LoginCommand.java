package team.kyp.kypcoffee.domain;

import javax.validation.Valid;

@Valid
public class LoginCommand {

    private String id;
    private String pw;
    private String name;
    private int no;
    private int type;

    public int getType() {return type;}

    public void setType(int type) {this.type = type;}

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}