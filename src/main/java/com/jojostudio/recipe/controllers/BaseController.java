package com.jojostudio.recipe.controllers;

import com.jojostudio.recipe.properties.UrlConfigProperties;
import com.jojostudio.recipe.utils.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
  @Autowired
  private UrlConfigProperties urlConfigProperties;

  protected String getBaseUrl() {
    String baseUrl = urlConfigProperties.getBaseUrl();
    if (!StringUtils.hasText(baseUrl)) {
      baseUrl = UrlUtil.getBaseEnvLinkURL();
    }

    return baseUrl;
  }
}
