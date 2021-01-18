package todo.springcloud.atguigucloud2020.seata.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import todo.springcloud.atguigucloud2020.seata.storage.domain.Storage;

@Mapper
public interface StorageMapper extends BaseMapper<Storage> {
}
