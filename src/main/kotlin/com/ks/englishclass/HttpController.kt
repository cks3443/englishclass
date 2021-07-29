package com.ks.englishclass

import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.NotNull


@RestController
@Validated
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

  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  fun deleteSentenceWithId(
    @PathVariable("id") @NotNull id: Long
  ) {
    sRepo.deleteById(id)
  }

  @ExceptionHandler(
    value = [
      MethodArgumentNotValidException::class,
      ConstraintViolationException::class
    ]
  )
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  fun processValidationError(e: Exception) {
  }
}