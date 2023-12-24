package com.example.engine

class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
    var isEndOfWord = false
}

class Trie {
    private val root = TrieNode()

    fun insert(word: String) {
        var current = root
        for (char in word) {
            current = current.children.computeIfAbsent(char) { TrieNode() }
        }
        current.isEndOfWord = true
    }

    fun search(word: String): Boolean {
        var current = root
        for (char in word) {
            current = current.children[char] ?: return false
        }
        return current.isEndOfWord
    }
}