package com.guofang.hatch.service.impl;

import com.guofang.hatch.service.EnterpriseService;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceImpl extends BaseServiceImpl implements EnterpriseService {
    @Override
    public String getDbTable(){
        return "gf_enterprise";
    }

    @Override
    public String getFillableColumns(){
        return "*";
    }


}
