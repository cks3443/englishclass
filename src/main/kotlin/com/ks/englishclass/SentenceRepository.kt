package com.ks.englishclass

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SentenceRepository : JpaRepository<Sentence, Long> {

  @Query("SELECT * FROM SENTENCE ORDER BY rand() LIMIT 1", nativeQuery = true)
  fun getRandomOne(): Sentence
}