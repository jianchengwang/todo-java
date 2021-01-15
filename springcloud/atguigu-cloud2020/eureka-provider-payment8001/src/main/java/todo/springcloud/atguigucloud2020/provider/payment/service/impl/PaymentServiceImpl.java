package todo.springcloud.atguigucloud2020.provider.payment.service.impl;

import org.springframework.stereotype.Service;
import todo.springcloud.atguigucloud2020.provider.payment.dao.PaymentDao;
import todo.springcloud.atguigucloud2020.common.entity.Payment;
import todo.springcloud.atguigucloud2020.provider.payment.service.PaymentService;

import javax.annotation.Resource;

/**
 * @author wjc
 * @date 2021/1/15
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getById(Long id) {
        return paymentDao.getById(id);
    }
}
