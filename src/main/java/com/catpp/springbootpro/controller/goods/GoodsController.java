package com.catpp.springbootpro.controller.goods;

import com.catpp.springbootpro.common.JsonResult;
import com.catpp.springbootpro.pojo.GoodInfo;
import com.catpp.springbootpro.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.catpp.springbootpro.controller.goods
 *
 * @Author cat_pp
 * @Date 2018/11/1
 * @Description 商品Controller
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/save")
    public JsonResult saveGoods(GoodInfo goodInfo) throws Exception {
        Integer goodsId = goodsService.save(goodInfo);
        return JsonResult.ok(goodsId);
    }
}
