package com.ks.englishclass

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

  @Autowired
  lateinit var sRepo: SentenceRepository

  @GetMapping("/")
  fun getIndexPage(
    model: Model
  ): String {
    val ss = sRepo.getRandomOne()
    model.addAttribute("s1", ss.s1)
    model.addAttribute("s2", ss.s2)
    model.addAttribute("isEditor", false)
    return "index"
  }

  @GetMapping("/edit")
  fun getEditPage(
    model: Model
  ): String {
    model.addAttribute("isEditor", true)
    return "index"
  }

}