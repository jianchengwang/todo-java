package todo.springcloud.atguigucloud2020.seata.storage.service;

public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
