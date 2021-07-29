package com.ks.englishclass

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest
class HttpControllerTest {


  @Autowired
  lateinit var webApplicationContext: WebApplicationContext
  lateinit var mockMvc: MockMvc

  @Mock
  lateinit var repository: SentenceRepository

  @BeforeEach
  fun setup() {
    mockMvc =
      MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
  }

  @Test
  fun `Save Sentences To H2`() {

    val content = jacksonObjectMapper().writeValueAsString(Sentence("hello world :)", "안녕하세요 !!"))

    mockMvc.perform(
      MockMvcRequestBuilders
        .post("/save")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content)
    )
      .andExpect(MockMvcResultMatchers.status().isOk)

  }

  @Test
  fun `Test When s1 is null`() {

    val content = jacksonObjectMapper().writeValueAsString(Sentence("", "안녕하세요 !!"))

    mockMvc.perform(
      MockMvcRequestBuilders
        .post("/save")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content)
    )
      .andExpect(MockMvcResultMatchers.status().isBadRequest)
      .andDo(MockMvcResultHandlers.print())
  }

  @Test
  fun `Test calling sentence with id`() {

    mockMvc.perform(
      MockMvcRequestBuilders
        .post("/sentence/{id}", 1).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
    )
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect(MockMvcResultMatchers.jsonPath("$.s1").value("hello world :)"))
      .andDo(MockMvcResultHandlers.print())
  }

}
