package todo.springcloud.atguigucloud2020.seata.storage.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import todo.springcloud.atguigucloud2020.seata.storage.domain.Storage;
import todo.springcloud.atguigucloud2020.seata.storage.mapper.StorageMapper;
import todo.springcloud.atguigucloud2020.seata.storage.service.StorageService;

import javax.annotation.Resource;

/**
 * @author wjc
 * @date 2021/1/18
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
        Storage storage = new Storage();
        Storage updateDo = storageMapper.selectOne(Wrappers.lambdaQuery(storage).eq(Storage::getProductId, productId));
        updateDo.setProductId(productId);
        updateDo.setUsed(updateDo.getUsed() + count);
        updateDo.setResidue(updateDo.getResidue() - count);
        storageMapper.updateById(updateDo);
    }
}
