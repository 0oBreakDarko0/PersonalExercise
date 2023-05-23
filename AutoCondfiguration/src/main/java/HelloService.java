public class HelloService {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void sayHello() {
        System.out.println("hello" + msg);
    }
    @Override
    public String toString() {
        return "HelloService{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
