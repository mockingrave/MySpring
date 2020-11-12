package myspring.mvc.DTO;

public class DateTimeDTO {
    private String dateTime;
    private String difference;

    protected DateTimeDTO() {
    }

    public DateTimeDTO(String dateTime, String difference) {
        this.dateTime = dateTime;
        this.difference = difference;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getDifference() {
        return difference;
    }
}
