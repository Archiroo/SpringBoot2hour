package SpringFarmeWork.SpringFarmeWork.Model;

public class ResponseObject {
    private String status;

    private String messeage;

    private Object data;

    public ResponseObject() {
    }

    public ResponseObject(String status, String messeage, Object data) {
        this.status = status;
        this.messeage = messeage;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMesseage() {
        return messeage;
    }

    public void setMesseage(String messeage) {
        this.messeage = messeage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
