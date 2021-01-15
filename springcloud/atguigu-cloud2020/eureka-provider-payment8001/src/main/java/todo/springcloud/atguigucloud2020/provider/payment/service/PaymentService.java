package todo.springcloud.atguigucloud2020.provider.payment.service;

import todo.springcloud.atguigucloud2020.common.entity.Payment;

public interface PaymentService {
    int create(Payment payment);

    Payment getById(Long id);
}
