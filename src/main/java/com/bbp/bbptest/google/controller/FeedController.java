package com.bbp.bbptest.google.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbp.bbptest.google.model.FeedDto;
import com.bbp.bbptest.google.model.FeedReq;
import com.bbp.bbptest.google.model.mapper.FeedMapper;
import com.bbp.bbptest.google.service.FeedService;
import com.google.ads.googleads.v9.resources.Feed;

@RequestMapping(value = "/google/feed")
@Controller
public class FeedController {

    private FeedService ggFeedService;
    private FeedMapper feedMapper;

    public FeedController(FeedService ggFeedService,
                          FeedMapper feedMapper) {
        this.ggFeedService = ggFeedService;
        this.feedMapper = feedMapper;
    }

    @GetMapping(value = "/{customerId}/feeds")
    public @ResponseBody
    ResponseEntity<List<FeedDto>> getFeeds(@PathVariable("customerId") long customerId) {
        List<Feed> feeds = ggFeedService.getFeeds(customerId);
        List<FeedDto> feedDtoList = feeds.stream()
                .map(feedMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<List<FeedDto>>(feedDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/feed/{feedId}")
    public @ResponseBody ResponseEntity<FeedDto> getFeed(
            @PathVariable("customerId") long customerId,
            @PathVariable("feedId") long feedId) {
        Feed feed = ggFeedService.getFeed(customerId, feedId);
        FeedDto feedDto = feedMapper.toDto(feed);
        return new ResponseEntity<FeedDto>(feedDto, HttpStatus.OK);
    }


    @PostMapping(value = "/create")
    public @ResponseBody ResponseEntity<FeedDto> create(@RequestBody FeedReq param) {
        String rn = ggFeedService.create(param);
        Feed feed = ggFeedService.getFeed(rn);
        FeedDto feedDto = feedMapper.toDto(feed);
        return new ResponseEntity<FeedDto>(feedDto, HttpStatus.OK);
    }
}
