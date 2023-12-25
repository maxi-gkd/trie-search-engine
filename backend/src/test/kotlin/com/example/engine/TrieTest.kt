package com.example.engine

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TrieTest {

    @Test
    fun insertAndSearch() {
        val trie = Trie()

        trie.insert("apple")
        assertTrue(trie.search("apple"))
        assertFalse(trie.search("appl"))

        trie.insert("app")
        assertTrue(trie.search("app"))

        trie.insert("banana")
        assertTrue(trie.search("banana"))
        assertFalse(trie.search("ban"))
    }
}
