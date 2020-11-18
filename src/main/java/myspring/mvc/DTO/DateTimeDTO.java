package myspring.mvc.DTO;

public class DateTimeDTO {
    private String dateTime;
    private String differenceInNormalForm;
    private long differenceInMilliseconds;

    protected DateTimeDTO() {
    }

    public DateTimeDTO(String dateTime, String differenceInNormalForm, long differenceInMilliseconds) {
        this.dateTime = dateTime;
        this.differenceInNormalForm = differenceInNormalForm;
        this.differenceInMilliseconds = differenceInMilliseconds;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getDifferenceInNormalForm() {
        return differenceInNormalForm;
    }

    public long getDifferenceInMilliseconds() {
        return differenceInMilliseconds;
    }
}
