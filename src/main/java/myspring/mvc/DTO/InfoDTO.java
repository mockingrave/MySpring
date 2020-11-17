package myspring.mvc.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfoDTO {

    private String information = "failure";
    private String additional_information;

    public InfoDTO() {
    }

    public InfoDTO(String information, String additional_information) {
        this.information = information;
        this.additional_information = additional_information;
    }

    public String getInformation() {
        return information;
    }

    public String getAdditional_information() {
        return additional_information;
    }

}
