package ru.alexanderrogachev.staffer.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "requests")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "requestId")
public class Request {

    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long requestId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "shop_id")
    @NotNull(message = "Укажите магазин")
    private Shop shopName;

    @Column(name = "number_of_req_staffers")
//    @NotEmpty(message = "Укажите кол-во требуемого персонала")
    @Min(value = 0, message = "Кол-во требуемого персонала не может быть отрицательным")
    private int numberOfReqStaffers;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "req_position_id")
    @NotNull(message = "Укажите требуемую должность")
    private Position reqPosition;

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

    @Column(name = "request_comment")
    @Size(max = 255, message = "Максимальная длина комментария 255 символов")
    private String comment;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "not_approved_staffers", joinColumns = @JoinColumn(name = "request_id"), inverseJoinColumns = @JoinColumn(name = "staffer_id"))
    private List<Staffer> notApprovedStaffersList;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "approved_staffers", joinColumns = @JoinColumn(name = "request_id"), inverseJoinColumns = @JoinColumn(name = "staffer_id"))
    private List<Staffer> approvedStaffersList;

    public Request() {
    }

    public Request(Shop shopName, int numberOfReqStaffers, Position reqPosition, Date dateOfRequest, Date dateOfWork, Date startTime, Date endTime, String comment) {
        this.shopName = shopName;
        this.numberOfReqStaffers = numberOfReqStaffers;
        this.reqPosition = reqPosition;
        this.dateOfRequest = dateOfRequest;
        this.dateOfWork = dateOfWork;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
    }

    //Получение кол-ва сотрудников в списке готовых к выходу
    public int getCountOfNotApprovedStaffersInRequest() {
        return notApprovedStaffersList.size();
    }

    //Получение кол-ва сотрудников в списке одобренных к выходу
    public int getCountOfApprovedStaffersInRequest() {
        return approvedStaffersList.size();
    }

//Смена списка готовых к выходу на одобренных и обратно
    public void switchStafferInRequest(Staffer staffer) {
        if (notApprovedStaffersList.contains(staffer)) {
            notApprovedStaffersList.remove(staffer);
            this.setNotApprovedStaffersList(notApprovedStaffersList);
            approvedStaffersList.add(staffer);
            this.setApprovedStaffersList(approvedStaffersList);
        } else {
            approvedStaffersList.remove(staffer);
            this.setApprovedStaffersList(approvedStaffersList);
            notApprovedStaffersList.add(staffer);
            this.setNotApprovedStaffersList(notApprovedStaffersList);
        }
    }
}
