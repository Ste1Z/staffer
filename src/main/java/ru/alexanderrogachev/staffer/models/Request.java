package ru.alexanderrogachev.staffer.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

//TODO проверить CascadeType.DELETE

@Getter
@Setter
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;

    @Column(name = "shop_name")
//    @NotEmpty(message = "Укажите название ММ")
    private String shopName;

    @Column(name = "number_of_req_staffers")
//    @NotEmpty(message = "Укажите кол-во требуемого персонала")
    @Min(value = 0, message = "Кол-во требуемого персонала не может быть отрицательным")
    private int numberOfReqStaffers;

    @Column(name = "req_position")
    private String reqPosition;

    @Column(name = "date_of_request")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfRequest;

    @Column(name = "date_of_work")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @NotEmpty(message = "Укажите необходимую дату")
    @FutureOrPresent(message = "Дата заявки не может быть ранее текущего дня")
    private Date dateOfWork;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
//    @NotEmpty(message = "Укажите необходимое время начала работы")
    private Date startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
//    @NotEmpty(message = "Укажите необходимое время окончания работы")
    private Date endTime;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "not_approved_staffers", joinColumns = @JoinColumn(name = "request_id"), inverseJoinColumns = @JoinColumn(name = "staffer_id"))
    private List<Staffer> notApprovedStaffersList;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "approved_staffers", joinColumns = @JoinColumn(name = "request_id"), inverseJoinColumns = @JoinColumn(name = "staffer_id"))
    private List<Staffer> approvedStaffersList;

    public Request() {
    }

    public Request(String shopName, int numberOfReqStaffers, String reqPosition, Date dateOfRequest, Date dateOfWork, Date startTime, Date endTime) {
        this.shopName = shopName;
        this.numberOfReqStaffers = numberOfReqStaffers;
        this.reqPosition = reqPosition;
        this.dateOfRequest = dateOfRequest;
        this.dateOfWork = dateOfWork;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getCountOfStaffersInRequest() {
        return notApprovedStaffersList.size();
    }

}
