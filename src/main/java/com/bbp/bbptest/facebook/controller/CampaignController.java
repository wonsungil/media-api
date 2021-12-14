package com.bbp.bbptest.facebook.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bbp.bbptest.facebook.model.CampaignDto;
import com.bbp.bbptest.facebook.model.CampaignReq;
import com.bbp.bbptest.facebook.service.CampaignService;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.Campaign;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/facebook/campaign")
@Controller(value = "fbCampaignController")
public class CampaignController {

    private CampaignService fbCampaignService;

    public CampaignController(CampaignService fbCampaignService) {
        this.fbCampaignService = fbCampaignService;
    }

    /**
     * 캠페인 생성
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/create")
    public ResponseEntity<CampaignDto> create(@RequestBody CampaignReq req) throws Exception {
        Campaign create = fbCampaignService.create(req.getAdAccountId(), req.getName(), req.getObjective(), req.getStatus());
        CampaignDto campaignDto =
                CampaignDto.builder()
                           .id(Long.parseLong(create.getFieldId()))
                           .adAccountId(Long.parseLong(create.getFieldAccountId()))
                           .name(create.getFieldName())
                           .objective(create.getFieldObjective())
                           .status(create.getFieldStatus().name())
                           .build();

        return new ResponseEntity<CampaignDto>(campaignDto, HttpStatus.OK);
    }

    /**
     * 캠페인 조회
     * @param campaignId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/{campaignId}")
    public ResponseEntity<CampaignDto> getCampaign(@PathVariable("campaignId") Long campaignId)
            throws Exception {
        Campaign campaign = fbCampaignService.getCampaign(campaignId);
        CampaignDto campaignDto = CampaignDto.builder()
                   .id(Long.parseLong(campaign.getFieldId()))
                   .adAccountId(Long.parseLong(campaign.getFieldAccountId()))
                   .name(campaign.getFieldName())
                   .status(campaign.getFieldStatus().name())
                   .objective(campaign.getFieldObjective())
                   .build();

        return new ResponseEntity<CampaignDto>(campaignDto, HttpStatus.OK);
    }

    /**
     * 캠페인 리스트 조회
     * @param adAccountId
     * @return
     * @throws APIException
     */
    @GetMapping(value = "/{adAccountId}/campaigns")
    public ResponseEntity<List<CampaignDto>> getCampaigns(@PathVariable("adAccountId") Long adAccountId)
            throws APIException {
        APINodeList<Campaign> campaignAPINodeList = fbCampaignService.getCampaigns(adAccountId);
        List<CampaignDto> campList =
                campaignAPINodeList
                        .stream()
                        .map(cn -> CampaignDto.builder()
                                              .id(Long.parseLong(cn.getFieldId()))
                                              .adAccountId(Long.parseLong(cn.getFieldAccountId()))
                                              .name(cn.getFieldName())
                                              .status(cn.getFieldStatus().name())
                                              .objective(cn.getFieldObjective())
                                              .build())
                        .collect(Collectors.toList());

        return new ResponseEntity<List<CampaignDto>>(campList, HttpStatus.OK);
    }

    /**
     * 캠페인 수정
     * @param req
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/update")
    public ResponseEntity<CampaignDto> update(@RequestBody CampaignReq req) throws Exception {
        Campaign create = fbCampaignService.update(req.getId(), req.getName(), req.getObjective(), req.getStatus());
        CampaignDto campaignDto =
                CampaignDto.builder()
                           .id(Long.parseLong(create.getFieldId()))
                           .adAccountId(Long.parseLong(create.getFieldAccountId()))
                           .name(create.getFieldName())
                           .objective(create.getFieldObjective())
                           .status(create.getFieldStatus().name())
                           .build();

        return new ResponseEntity<CampaignDto>(campaignDto, HttpStatus.OK);
    }

    /**
     * 캠페인 삭제
     * @param req
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/delete")
    public ResponseEntity<CampaignDto> delete(@RequestBody CampaignReq req) throws Exception {
        Campaign delete = fbCampaignService.delete(req.getId());
        CampaignDto campaignDto = CampaignDto.builder()
                .id(Long.parseLong(delete.getFieldId()))
                .build();
        return new ResponseEntity<CampaignDto>(campaignDto, HttpStatus.OK);
    }
}
