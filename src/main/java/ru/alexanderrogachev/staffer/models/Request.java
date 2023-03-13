package ru.alexanderrogachev.staffer.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "requests")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "requestId")
public class Request {

    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    @NotNull(message = "Укажите магазин")
    private Shop requestShop;

    @Column(name = "number_of_req_staffers")
    @Min(value = 0, message = "Кол-во требуемого персонала не может быть отрицательным")
    @NotNull(message = "Укажите кол-во требуемого персонала")
    private int requestNumOfReqStaffers;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "req_position_id")
    @NotNull(message = "Укажите требуемую должность")
    private Position requestReqPosition;

    @Column(name = "date_of_request")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Укажите дату создания запроса")
    private Date requestCreationDate;

    @Column(name = "date_of_work")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Дата выхода сотрудника не может быть ранее текущего дня")
    @NotNull(message = "Укажите дату выхода сотрудника")
    private Date requestDateOfWork;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "Укажите время начала работы")
    private Date requestStartTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "Укажите время окончания работы")
    private Date requestEndTime;

    @Column(name = "request_comment")
    @Size(max = 255, message = "Максимальная длина комментария 255 символов")
    private String requestComment;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "not_approved_staffers", joinColumns = @JoinColumn(name = "request_id"), inverseJoinColumns = @JoinColumn(name = "staffer_id"))
    private List<Staffer> notApprovedStaffersList;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "approved_staffers", joinColumns = @JoinColumn(name = "request_id"), inverseJoinColumns = @JoinColumn(name = "staffer_id"))
    private List<Staffer> approvedStaffersList;

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
