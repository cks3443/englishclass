package com.ks.englishclass

import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import javax.validation.ConstraintViolationException
import javax.validation.Valid


@RestController
class HttpController {

  @Autowired
  lateinit var sRepo: SentenceRepository

  @PostMapping("/save")
  @ResponseStatus(HttpStatus.OK)
  fun saveSentence(
    @RequestBody @Valid st: Sentence,
    errors: Errors
  ) {
    this.sRepo.save(st)

  }

  @PostMapping("/sentence/{id}")
  fun getSentenceWithId(
    @NotNull @PathVariable("id") id: Long,

    ): Sentence? {

    val ss = sRepo.findByIdOrNull(id)
    return ss
  }

  @ExceptionHandler(value = [MethodArgumentNotValidException::class, ConstraintViolationException::class])
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  fun processValidationError(e: Exception) {
  }
}