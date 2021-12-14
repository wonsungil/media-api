package com.bbp.bbptest.google.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.service.RemarketingActionService;
import com.google.ads.googleads.v9.resources.RemarketingAction;

@RequestMapping(value = "/google/remarketing-action")
@Controller
public class RemarketingActionController {

    private RemarketingActionService ggRemarketingActionService;

    public RemarketingActionController(RemarketingActionService ggRemarketingService) {
        this.ggRemarketingActionService = ggRemarketingService;
    }

    @GetMapping(value = "/remarketingActions/{customerId}")
    public @ResponseBody
    ResponseEntity getRemarketingActions(@PathVariable("customerId") long customerId) {
        List<RemarketingAction>
                remarketingActionList = ggRemarketingActionService.getRemarketingActions(customerId);
        for (RemarketingAction remarketingAction : remarketingActionList) {
            System.out.println(remarketingAction.toString());
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
