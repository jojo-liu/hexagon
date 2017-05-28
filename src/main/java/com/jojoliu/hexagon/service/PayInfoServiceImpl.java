package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.enums.OrderStatus;
import com.jojoliu.hexagon.model.PayInfo;
import com.jojoliu.hexagon.util.NoUtil;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Random;

/**
 * Created by Jojo on 26/05/2017.
 */
public class PayInfoServiceImpl extends AbstractBaseService<PayInfo> implements PayInfoService{

//    @Autowired
//    private PayInfoMapper payInfoMapper;

    public List<PayInfo> list() {
        Example example = new Example(PayInfo.class);
        example.setOrderByClause("orderNo desc");
        return mapper.selectByExample(example);
    }

    @Override
    public List<PayInfo> find(PayInfo payInfo) {
        Example example = new Example(PayInfo.class);
        example.setOrderByClause("orderNo desc");
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(payInfo.getOrderNo())) {
            criteria.andEqualTo(payInfo.getOrderNo());
        }
        return mapper.selectByExample(example);
    }

    @Override
    public PayInfo add(PayInfo payInfo) {
        payInfo.setOrderNo(NoUtil.generate("P"));
        payInfo.setStatus(OrderStatus.I.name());
        mapper.insert(payInfo);
        return payInfo;
    }

    @Override
    public Response pay(PayInfo payInfo) {
        payInfo = this.add(payInfo);
        Response response = new Response();
        try {
            if (payMock()) {
                payInfo.setStatus(OrderStatus.S.name());
                response.setResult(payInfo);
            } else {
                payInfo.setStatus(OrderStatus.F.name());
                response.setError("支付失败");
            }
        } catch (Exception e) {
            payInfo.setStatus(OrderStatus.P.name());
            response.setError("支付发生异常");
        }
        mapper.updateByPrimaryKey(payInfo);
        return response;
    }

    @Override
    public boolean payMock() {
        int randomInt= new Random().nextInt(10) ;
        logger.info("支付结果随机值{}", randomInt);
        if (randomInt < 3) {
            throw new RuntimeException("异常");
        } if (randomInt < 6) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }
}
