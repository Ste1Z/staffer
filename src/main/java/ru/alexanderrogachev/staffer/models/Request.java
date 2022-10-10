package ru.alexanderrogachev.staffer.models;

import lombok.Getter;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int requestId;
    @Column(name = "staffer_id")
    private int stafferId;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "date_of_request")
    private Date dateOfRequest;
    @Column(name = "date_of_work")
    private Date dateOfWork;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "confirmation")
    private Boolean confirmation;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "staffer_requests", joinColumns = @JoinColumn(name = "request_id"), inverseJoinColumns = @JoinColumn(name = "staffer_id"))
    private List<Staffer> requestStaffers;

    public Request() {
    }

    public Request(int stafferId, String shopName, Date dateOfRequest, Date dateOfWork, Date startTime, Date endTime, Boolean confirmation) {
        this.stafferId = stafferId;
        this.shopName = shopName;
        this.dateOfRequest = dateOfRequest;
        this.dateOfWork = dateOfWork;
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmation = confirmation;
    }

    public void addStafferToRequest(Staffer staffer) {
        if (requestStaffers == null) {
            requestStaffers = new ArrayList<>();
        }
        requestStaffers.add(staffer);
    }

}
