package ru.alexanderrogachev.staffer.domain;

//Список ролей на сайте
public enum Role {

    //Неавторизованный пользователь
    ROLE_GUEST,
    //Добавление в созданные заявки
    ROLE_STAFFER,
    //Добавление в созданные заявки
    //Добавление и редактирование своих заявок
    ROLE_SHOP_ADMIN,
    //Абсолютная власть
    ROLE_PRIME_ADMIN

}
