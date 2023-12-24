package com.example.engine

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TrieTest {

    @Test
    fun insertAndSearch() {
        val trie = Trie()

        // Insert words
        trie.insert("apple")
        trie.insert("app")
        trie.insert("banana")

        // Test searches
        assertTrue(trie.search("apple"))
        assertTrue(trie.search("app"))
        assertFalse(trie.search("appl"))
        assertTrue(trie.search("banana"))
        assertFalse(trie.search("ban"))
    }
}
