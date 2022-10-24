package ru.alexanderrogachev.staffer.domain;

import org.springframework.security.core.GrantedAuthority;

//Список ролей на сайте
public enum Role  implements GrantedAuthority {

    //Неавторизованный пользователь
    ROLE_GUEST,
    //Добавление в созданные заявки
    ROLE_STAFFER,
    //Добавление в созданные заявки
    //Добавление и редактирование своих заявок
    ROLE_SHOP_ADMIN,
    //Абсолютная власть
    ROLE_PRIME_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
