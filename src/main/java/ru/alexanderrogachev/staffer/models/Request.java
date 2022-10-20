package ru.alexanderrogachev.staffer.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "number_of_req_staffers")
    private int numberOfReqStaffers;

    @Column(name = "date_of_request")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfRequest;

    @Column(name = "date_of_work")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfWork;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date endTime;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "staffer_requests", joinColumns = @JoinColumn(name = "request_id"), inverseJoinColumns = @JoinColumn(name = "staffer_id"))
    private List<Staffer> requestStaffers;

    public Request() {
    }

    public Request(String shopName, Date dateOfRequest, Date dateOfWork, Date startTime, Date endTime) {
        this.shopName = shopName;
        this.dateOfRequest = dateOfRequest;
        this.dateOfWork = dateOfWork;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
