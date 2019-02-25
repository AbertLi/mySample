package one.example.com.mysample.main.webservice.fz;

public class BaseModel<T> {
    private long code ;//请求返回码
    private String msg;// 请求返回信息
    private int cost;
    private String timestamp;
    private T data;//请求返回的body

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}