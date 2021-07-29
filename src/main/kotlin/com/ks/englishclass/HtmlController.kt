package com.ks.englishclass

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

  @GetMapping("/")
  fun getIndexPage(
    model: Model
  ): String {
    model.addAttribute("s1", "Hello World :)")
    return "index"
  }
}